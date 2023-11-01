package com.shopping.store.dto;

import com.shopping.store.entity.AccessoryInfo;
import com.shopping.store.entity.AccessoryPrice;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ViewAccessoryDto {

    private UUID accessoryId;

    private AccessoryInfo accessoryInfo;

    private AccessoryPrice accessoryPrice;

    private Date creationDate;

}
