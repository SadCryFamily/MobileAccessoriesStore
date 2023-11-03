package com.shopping.store.entity;

import com.shopping.store.entity.listener.ClientAuditListener;
import com.shopping.store.entity.nested.ClientBasicInfo;
import com.shopping.store.entity.nested.ClientContacts;
import com.shopping.store.entity.nested.ClientDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client_general")
@EntityListeners({AuditingEntityListener.class, ClientAuditListener.class})
public class ClientGeneral {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "client_id")
    private UUID clientId;

    @Embedded
    private ClientBasicInfo clientBasicInfo;

    @Embedded
    private ClientContacts clientContacts;

    @Embedded
    private ClientDate clientDate;

}
