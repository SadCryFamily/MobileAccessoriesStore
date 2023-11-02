package com.shopping.store.mapper;

import com.shopping.store.dto.CreateClientDto;
import com.shopping.store.dto.ViewCreatedClientDto;
import com.shopping.store.entity.ClientGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "clientBasicInfo.", source = "dto.")
    @Mapping(target = "clientContacts.", source = "dto.")
    ClientGeneral toEntity(CreateClientDto dto);

    ViewCreatedClientDto toViewCreatedDto(ClientGeneral clientGeneral);

}
