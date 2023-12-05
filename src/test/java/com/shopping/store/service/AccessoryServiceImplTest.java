package com.shopping.store.service;

import com.shopping.store.dto.*;
import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.entity.nested.AccessoryDate;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.enums.Currency;
import com.shopping.store.mapper.AccessoryMapper;
import com.shopping.store.repository.AccessoryRepository;
import com.shopping.store.repository.hibernate.AccessoryHibernateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AccessoryServiceImplTest {

    @Autowired
    private AccessoryService accessoryService;

    @MockBean
    private AccessoryRepository accessoryRepository;

    @MockBean
    private AccessoryHibernateRepository accessoryHibernateRepository;

    @Autowired
    private AccessoryMapper accessoryMapper;

    @Test
    void createAccessory() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());

        Mockito.when(accessoryRepository.save(Mockito.any(AccessoryGeneral.class)))
                .thenReturn(accessoryGeneral);

        ViewCreatedAccessoryDto expectedDto = accessoryMapper.toViewCreatedDto(accessoryGeneral);
        ViewCreatedAccessoryDto actualDto = accessoryService.createAccessory(mockDto);

        assertEquals(expectedDto.getAccessoryInfo(), actualDto.getAccessoryInfo());
    }

    @Test
    void viewAccessoryByArticle() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());

        Mockito.when(accessoryRepository.findByAccessoryId(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(accessoryGeneral));

        ViewAccessoryDto expectedDto = accessoryMapper.toViewDto(accessoryGeneral);
        ViewAccessoryDto actualDto = accessoryService.viewAccessoryByArticle(UUID.randomUUID());

        assertEquals(expectedDto.getAccessoryInfo(), actualDto.getAccessoryInfo());
    }

    @Test
    void viewAllAccessories() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());
        accessoryGeneral.setAccessoryDate(new AccessoryDate(new Date(), new Date()));

        List<AccessoryGeneral> mockListDto = new ArrayList<>();
        mockListDto.add(accessoryGeneral);

        Mockito.when(accessoryRepository.findAll(Mockito.any(Sort.class)))
                .thenReturn(mockListDto);

        List<ViewAccessoryDto> expectedViewDtoList = mockListDto.stream()
                .map(accessory -> accessoryMapper.toViewDto(accessory))
                .collect(Collectors.toList());
        List<ViewAccessoryDto> actualViewDtoList = accessoryService.viewAllAccessories();

        assertEquals(expectedViewDtoList.get(0).getAccessoryInfo(), actualViewDtoList.get(0).getAccessoryInfo());

    }

    @Test
    void viewAllAccessoriesFilteredBy() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());

        List<AccessoryGeneral> mockListDto = new ArrayList<>();
        mockListDto.add(accessoryGeneral);

        Mockito.when(accessoryHibernateRepository.findAllAccessoriesByType(Mockito.any(AccessoryType.class)))
                .thenReturn(mockListDto);

        List<ViewAccessoryDto> expectedViewDto = mockListDto.stream()
                .map(accessory -> accessoryMapper.toViewDto(accessory))
                .collect(Collectors.toList());
        List<ViewAccessoryDto> actualViewDto =
                accessoryService.viewAllAccessoriesFilteredBy(AccessoryType.CHARGING, null, null);

        assertEquals(expectedViewDto.get(0).getAccessoryInfo().getAccessoryName(),
                actualViewDto.get(0).getAccessoryInfo().getAccessoryName());
    }

    @Test
    void updateAccessoryByArticle() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());

        Mockito.when(accessoryRepository.findAccessoryBeforeManipulationByArticle(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(accessoryGeneral));

        UpdateAccessoryDto updateDto = new UpdateAccessoryDto("Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD);

        ViewUpdatedAccessoryDto expectedViewDto = accessoryMapper.toViewUpdatedDto(accessoryGeneral);
        ViewUpdatedAccessoryDto actualViewDto =
                accessoryService.updateAccessoryByArticle(UUID.randomUUID(), updateDto);

        assertEquals(expectedViewDto.getAccessoryInfo().getAccessoryName(),
                actualViewDto.getAccessoryInfo().getAccessoryName());
    }

    @Test
    void deleteAccessoryByArticle() {

        CreateAccessoryDto mockDto = new CreateAccessoryDto(
                "Test",
                "Test description",
                AccessoryType.CHARGING,
                BigDecimal.ONE,
                Currency.USD
        );

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(mockDto);
        accessoryGeneral.setAccessoryId(UUID.randomUUID());

        Mockito.when(accessoryRepository.findAccessoryBeforeManipulationByArticle(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(accessoryGeneral));

        DeleteAccessoryDto deleteDto = new DeleteAccessoryDto(UUID.randomUUID());

        ViewDeletedAccessoryDto expectedDto = new ViewDeletedAccessoryDto(UUID.randomUUID(), 1);
        ViewDeletedAccessoryDto actualDto = accessoryService.deleteAccessoryByArticle(deleteDto);

    }
}