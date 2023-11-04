package com.shopping.store.dto;

import com.shopping.store.entity.nested.AccessoryDate;
import com.shopping.store.entity.nested.AccessoryInfo;
import com.shopping.store.entity.nested.AccessoryPrice;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ViewAccessoryDto {

    private UUID accessoryId;

    private AccessoryInfo accessoryInfo;

    private AccessoryPrice accessoryPrice;

    private AccessoryDate accessoryDate;

}
