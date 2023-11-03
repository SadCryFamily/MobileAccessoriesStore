package com.shopping.store.entity.listener;

import com.shopping.store.entity.ClientGeneral;
import com.shopping.store.entity.nested.ClientDate;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class ClientAuditListener {

    private final String DEFAULT_PHONE = "UNKNOWN";

    @PrePersist
    public void beforeCreatePersist(ClientGeneral clientGeneral) {
        ClientDate clientDate = new ClientDate(new Date(), new Date());
        clientGeneral.setClientDate(clientDate);
    }

    @PreUpdate
    public void beforeUpdatePersist(ClientGeneral clientGeneral) {
        clientGeneral.getClientDate().setLastUpdateDate(new Date());
    }

}
