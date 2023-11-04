package com.shopping.store.entity.nested;

import com.shopping.store.enums.AccessoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.loader.plan.spi.ScalarReturn;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccessoryInfo implements Serializable {

    @Column(name = "accessory_name")
    private String accessoryName;

    @Column(name = "accessory_description")
    private String accessoryDescription;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "accessory_type")
    private AccessoryType accessoryType;

}
