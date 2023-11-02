package com.shopping.store.entity;

import com.shopping.store.entity.nested.AccessoryDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Slf4j
public class AccessoryAuditListener {

    @PrePersist
    public void beforeCreatePersist(AccessoryGeneral accessoryGeneral) {
        AccessoryDate accessoryDate = new AccessoryDate(new Date(), new Date());
        accessoryGeneral.setAccessoryDate(accessoryDate);
    }

    @PreUpdate
    public void beforeUpdatePersist(AccessoryGeneral accessoryGeneral) {
        accessoryGeneral.getAccessoryDate().setLastUpdateDate(new Date());
    }

}
