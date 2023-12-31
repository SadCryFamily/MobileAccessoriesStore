package com.shopping.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAccessoryDto implements Serializable {

    @NotNull(message = "Id can't be null")
    private UUID accessoryId;

}
