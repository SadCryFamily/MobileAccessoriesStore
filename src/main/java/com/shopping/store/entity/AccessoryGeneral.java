package com.shopping.store.entity;

import com.shopping.store.entity.listener.AccessoryAuditListener;
import com.shopping.store.entity.nested.AccessoryDate;
import com.shopping.store.entity.nested.AccessoryInfo;
import com.shopping.store.entity.nested.AccessoryPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "accessory_general")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, AccessoryAuditListener.class})
@FilterDefs(value = {
        @FilterDef(name = "accessory_filter_type", parameters = {
                @ParamDef(name = "type", type = "string")
        }),
        @FilterDef(name = "accessory_filter_type_and_cost_from", parameters = {
                @ParamDef(name = "type", type = "string"),
                @ParamDef(name = "cost_from", type = "big_decimal")
        }),
        @FilterDef(name = "accessory_filter_type_and_cost_to", parameters = {
                @ParamDef(name = "type", type = "string"),
                @ParamDef(name = "cost_to", type = "big_decimal")
        }),
        @FilterDef(name = "accessory_filter_type_and_cost_from_and_cost_to", parameters = {
                @ParamDef(name = "type", type = "string"),
                @ParamDef(name = "cost_from", type = "big_decimal"),
                @ParamDef(name = "cost_to", type = "big_decimal")
        })
})
@Filters(value = {
        @Filter(name = "accessory_filter_type", condition = "accessory_type = :type"),
        @Filter(name = "accessory_filter_type_and_cost_from", condition = "accessory_type = :type AND accessory_price >= :cost_from"),
        @Filter(name = "accessory_filter_type_and_cost_to", condition = "accessory_type = :type AND accessory_price <= :cost_to"),
        @Filter(name = "accessory_filter_type_and_cost_from_and_cost_to", condition = "accessory_type = :type AND accessory_price >= :cost_from AND accessory_price <= :cost_to")
})
public class AccessoryGeneral implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "accessory_id")
    private UUID accessoryId;

    @Embedded
    private AccessoryInfo accessoryInfo;

    @Embedded
    private AccessoryPrice accessoryPrice;

    @Embedded
    private AccessoryDate accessoryDate;
}
