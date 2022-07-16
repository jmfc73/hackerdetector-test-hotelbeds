package com.hotelbeds.supplierintegrations.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDTO {

    private String message;
}
