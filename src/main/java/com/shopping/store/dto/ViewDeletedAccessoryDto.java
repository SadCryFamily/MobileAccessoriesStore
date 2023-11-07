package com.shopping.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDeletedAccessoryDto implements Serializable {

    private UUID accessoryId;

    private Integer deletionStatus;

}
