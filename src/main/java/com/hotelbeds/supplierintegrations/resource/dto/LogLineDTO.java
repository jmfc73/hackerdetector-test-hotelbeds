package com.hotelbeds.supplierintegrations.resource.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder 
@Data
public class LogLineDTO implements Serializable {

    private static final long serialVersionUID = 5211667553004216359L;
    
    @NotBlank
    private String processLine;
}
