package com.shopping.store.dto;

import com.shopping.store.entity.nested.ClientBasicInfo;
import com.shopping.store.entity.nested.ClientContacts;
import com.shopping.store.entity.nested.ClientDate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ViewCreatedClientDto {

    private UUID clientId;

    private ClientBasicInfo clientBasicInfo;

    private ClientContacts clientContacts;

    private ClientDate clientDate;

}
