package com.shopping.store.entity.nested;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientBasicInfo {

    @Column(name = "client_full_name")
    private String clientFullName;

    @Column(name = "client_name")
    private String clientName;

}
