# Sujet

Développement d'une WEB API d'une gestion de contacts dans une entreprise.

## Fonctionnalités

- Création d'un contact
- Mise à jour d'un contact
- Suppression d'un contact
- Consultation de tous les contacts
- Création d'une entreprise
- Mise à jour d'une entreprise
- Recherche d'une entreprise à l'aide de son numéro de TVA
- Consultation des toutes les entreprises
- Ajout d'un contact à une entreprise

## Contraintes Business

- Un contact doit avoir une adresse, un nom et un prénom
- Un contact travaille dans une ou plusieurs entreprises
- Une entreprise a une adresse et un numéro de TVA
- Un contact peut être employé ou freelance
- S'il est freelance, il doit avoir un numéro de TVA

## Contraintes techniques

- WEB API REST JSON synchrone
- Swagger UI
- JPA
- Spring Boot
- In-memory DB (HSQL ou H2)
- Garder le projet le plus simple possible
- Tests unitaires non requis
- A défaut de Swagger UI, joindre un export du projet Postman.
- Publier son projet sur une repo de sources public (genre Github ou Gitlab)
