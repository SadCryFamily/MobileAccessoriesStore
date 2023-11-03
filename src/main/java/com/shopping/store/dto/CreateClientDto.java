package com.shopping.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto {

    private String clientFullName;

    private String clientName;

    private String clientEmail;

    private String clientPhone;

}
