package com.shopping.store.controller;

import com.shopping.store.dto.CreateClientDto;
import com.shopping.store.dto.ViewCreatedClientDto;
import com.shopping.store.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create-client")
    public ViewCreatedClientDto createClient(@RequestBody CreateClientDto createClientDto) {
        return clientService.createClient(createClientDto);
    }

}
