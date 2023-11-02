package com.shopping.store.service;

import com.shopping.store.dto.CreateClientDto;
import com.shopping.store.dto.ViewCreatedClientDto;

public interface ClientService {

    ViewCreatedClientDto createClient(CreateClientDto createClientDto);

}
