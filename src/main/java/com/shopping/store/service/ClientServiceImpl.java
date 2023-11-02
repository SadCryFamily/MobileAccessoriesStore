package com.shopping.store.service;

import com.shopping.store.dto.CreateClientDto;
import com.shopping.store.dto.ViewCreatedClientDto;
import com.shopping.store.entity.ClientGeneral;
import com.shopping.store.mapper.ClientMapper;
import com.shopping.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ViewCreatedClientDto createClient(CreateClientDto createClientDto) {
        ClientGeneral clientGeneral = clientMapper.toEntity(createClientDto);
        ClientGeneral complete = clientRepository.save(clientGeneral);

        return clientMapper.toViewCreatedDto(complete);
    }
}
