package com.shopping.store.entity.nested;

import com.shopping.store.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryPrice implements Serializable {

    @Column(name = "accessory_price")
    private BigDecimal accessoryPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "accessory_currency")
    private Currency accessoryCurrency;

}
