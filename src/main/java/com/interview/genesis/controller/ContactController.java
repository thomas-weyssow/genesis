package com.interview.genesis.controller;

import com.interview.genesis.dto.contact.ContactResponse;
import com.interview.genesis.dto.contact.CreateContactRequest;
import com.interview.genesis.dto.contact.UpdateContactRequest;
import com.interview.genesis.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoints for contacts
 */
@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactResponse> findAll() {

        return contactService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse create(@Valid @RequestBody CreateContactRequest request) {

        return contactService.create(request);
    }

    @PutMapping("/{id}")
    public ContactResponse update(@Valid @RequestBody UpdateContactRequest request, @PathVariable Long id) {

        return contactService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        contactService.delete(id);
    }
}
