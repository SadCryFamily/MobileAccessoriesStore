package com.shopping.store.mapper;

import com.shopping.store.dto.CreateAccessoryDto;
import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.dto.ViewCreatedAccessoryDto;
import com.shopping.store.entity.AccessoryGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AccessoryMapper {

    AccessoryMapper INSTANCE = Mappers.getMapper(AccessoryMapper.class);

    @Mapping(target = "accessoryInfo.", source = "dto.")
    @Mapping(target = "accessoryPrice.", source = "dto.")
    AccessoryGeneral toEntity(CreateAccessoryDto dto);

    ViewCreatedAccessoryDto toViewCreatedDto(AccessoryGeneral accessoryGeneral);

    ViewAccessoryDto toViewDto(AccessoryGeneral accessoryGeneral);

}
