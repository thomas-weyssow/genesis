package com.interview.genesis.controller;

import com.interview.genesis.dto.contact.ContactResponse;
import com.interview.genesis.dto.contact.CreateContactRequest;
import com.interview.genesis.dto.contact.UpdateContactRequest;
import com.interview.genesis.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> create(@RequestBody CreateContactRequest request) {

        ContactResponse response = contactService.create(request);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri()
        ;

        return ResponseEntity
            .created(location)
            .body(response)
        ;
    }

    @PutMapping("/{id}")
    public ContactResponse update(@RequestBody UpdateContactRequest request, @PathVariable Long id) {

        return contactService.update(request, id);
    }
}
