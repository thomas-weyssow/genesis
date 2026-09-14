# Genesis: API de gestion de contacts

API REST/JSON synchrone de gestion des contacts d'une entreprise, réalisée dans le cadre d'un
assessment technique.

## Stack

- Java 21, Spring Boot 4.1
- Spring Web MVC, Spring Data JPA, Bean Validation
- Base de données H2 en mémoire
- springdoc-openapi (Swagger UI)
- Lombok

## Démarrage

```bash
mvn spring-boot:run
```

| | URL |
|---|---|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Document OpenAPI | http://localhost:8080/v3/api-docs |
| Console H2 | http://localhost:8080/h2-console (URL JDBC `jdbc:h2:mem:genesis`, utilisateur `sa`, pas de mot de passe) |

Le schéma est créé au démarrage et supprimé à l'arrêt : la base est vide à chaque exécution.

## Endpoints

Les neuf fonctionnalités demandées :

| Fonctionnalité | Endpoint |
|---|---|
| Création d'un contact | `POST /contact` |
| Mise à jour d'un contact | `PUT /contact/{id}` |
| Suppression d'un contact | `DELETE /contact/{id}` |
| Consultation de tous les contacts | `GET /contact` |
| Création d'une entreprise | `POST /company` |
| Mise à jour d'une entreprise | `PUT /company/{id}` |
| Recherche d'une entreprise par TVA | `GET /company/vat/{vat}` |
| Consultation de toutes les entreprises | `GET /company` |
| Ajout d'un contact à une entreprise | `POST /company/{companyId}/contact/{contactId}` |

`DELETE /company/{id}` a été ajouté par symétrie ; il n'est pas demandé par l'énoncé.

### Création d'un contact

Le payload est polymorphe, discriminé par `type` :

```json
{
  "type": "EMPLOYEE",
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main street, 1000 Brussels",
  "companyIds": [1]
}
```

```json
{
  "type": "FREELANCE",
  "firstName": "Marie",
  "lastName": "Dupont",
  "address": "12 rue Haute, 1000 Brussels",
  "vat": "BE0123456789",
  "companyIds": [1, 2]
}
```

## Modèle de domaine

`Contact` est une entité abstraite avec deux types concrets, `Employee` et `Freelance`, mappés en
héritage `SINGLE_TABLE`. Un contact et une entreprise sont liés par une association many-to-many dont
le côté propriétaire est `Contact`.

## Choix de conception

**Héritage plutôt qu'une colonne de type.** L'énoncé décrit « un contact peut être employé ou
freelance » avec une contrainte propre à un sous-type, ce qui correspond à une hiérarchie de types.
`SINGLE_TABLE` évite une jointure à chaque lecture. En contrepartie, la colonne TVA est partagée avec
les lignes des employés et ne peut donc pas être `NOT NULL` : la règle est garantie par le
constructeur de `Freelance` et par une contrainte de vérification au niveau de la table
(`type <> 'FREELANCE' or vat is not null`). L'héritage `JOINED` permettrait un vrai `NOT NULL`, au
prix d'une jointure à chaque lecture.

**Le type d'un contact ne peut pas être modifié.** En JPA, le type est la classe Java : une conversion
impliquerait de supprimer puis recréer la ligne avec un nouvel identifiant. Le payload de mise à jour
ne contient donc pas de champ `type`.

**Un contact peut exister sans entreprise.** `companyIds` est facultatif à la création, et
« ajout d'un contact à une entreprise » dispose de son propre endpoint. En mise à jour, un
`companyIds` absent laisse les liens inchangés, `[]` les supprime tous, et une liste les remplace.

**Une seule constante partagée par type de contact.** `Employee.TYPE` et `Freelance.TYPE` sont
l'unique source du discriminant JPA, du discriminant JSON, de la contrainte de vérification et de la
réponse de l'API. Les membres d'annotation Java n'acceptent que des expressions constantes : une
constante d'énumération ne peut donc pas être utilisée dans `@JsonSubTypes` ni dans
`@DiscriminatorValue`, alors qu'un `public static final String` déclaré sur chaque sous-type le peut.
Cela supprime la duplication qu'une énumération aurait introduite.

## Réponses d'erreur

Toutes les erreurs utilisent le format RFC 9457 `application/problem+json`.

| Situation | Statut |
|---|---|
| Identifiant inconnu dans l'URL | 404 |
| Corps invalide (champ vide, champ obligatoire manquant) | 400 |
| Corps illisible ou `type` inconnu | 400 |
| Identifiants d'entreprise inconnus dans le corps | 422 |
| Numéro de TVA en doublon | 409 |