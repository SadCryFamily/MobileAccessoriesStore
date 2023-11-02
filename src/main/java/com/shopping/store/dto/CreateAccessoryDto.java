package com.shopping.store.dto;

import com.shopping.store.enums.AccessoryType;
import com.shopping.store.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccessoryDto {

    private String accessoryName;

    private String accessoryDescription;

    private AccessoryType accessoryType;

    private BigDecimal accessoryPrice;

    private Currency accessoryCurrency;

}
