package com.hotelbeds.supplierintegrations.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseResultDTO<T> {

    private T result;

}
