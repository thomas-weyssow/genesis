package com.interview.genesis.controller;

import com.interview.genesis.dto.CreateContactRequest;
import com.interview.genesis.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public void create(@RequestBody CreateContactRequest request) throws Exception {

        contactService.create(request);
    }
}
