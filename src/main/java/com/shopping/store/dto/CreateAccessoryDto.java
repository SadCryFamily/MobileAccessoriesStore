package com.shopping.store.dto;

import com.shopping.store.enums.AccessoryType;
import com.shopping.store.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccessoryDto {

    @NotNull(message = "Name can't be null")
    @Size(min = 5, max = 30, message = "Accessory name must be between 5 and 30 symbols")
    private String accessoryName;

    @NotNull(message = "Description can't be null")
    @Size(min = 5, max = 100, message = "Accessory name must be between 5 and 100 symbols")
    private String accessoryDescription;

    @NotNull(message = "Type can't be null")
    private AccessoryType accessoryType;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.1")
    private BigDecimal accessoryPrice;

    @NotNull(message = "Currency can't be null")
    private Currency accessoryCurrency;

}
