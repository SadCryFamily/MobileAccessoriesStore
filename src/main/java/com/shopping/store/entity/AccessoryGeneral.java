package com.shopping.store.entity;

import com.shopping.store.entity.nested.AccessoryDate;
import com.shopping.store.entity.nested.AccessoryInfo;
import com.shopping.store.entity.nested.AccessoryPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "accessory_general")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, AccessoryAuditListener.class})
public class AccessoryGeneral {

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
