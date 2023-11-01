package com.shopping.store.service;

import com.shopping.store.dto.CreateAccessoryDto;
import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.dto.ViewCreatedAccessoryDto;
import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.mapper.AccessoryMapper;
import com.shopping.store.repository.AccessoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;

    private final AccessoryMapper accessoryMapper;

    @Override
    @Transactional
    public ViewCreatedAccessoryDto createAccessory(CreateAccessoryDto accessoryDto) {
        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(accessoryDto);
        AccessoryGeneral complete = accessoryRepository.save(accessoryGeneral);

        return accessoryMapper.toViewCreatedDto(complete);
    }

    @Override
    @Transactional
    public ViewAccessoryDto viewAccessoryByArticle(UUID article) {
        return accessoryMapper.toViewDto(accessoryRepository.findByAccessoryId(article));
    }
}
