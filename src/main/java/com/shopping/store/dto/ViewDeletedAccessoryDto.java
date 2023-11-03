package com.shopping.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDeletedAccessoryDto {

    private UUID accessoryId;

    private Integer deletionStatus;

}
