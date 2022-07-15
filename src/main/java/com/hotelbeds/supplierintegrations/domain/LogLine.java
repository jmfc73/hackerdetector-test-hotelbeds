package com.hotelbeds.supplierintegrations.domain;

import java.time.LocalDateTime;

import com.hotelbeds.supplierintegrations.util.SigninState;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Builder
public class LogLine {
    
    private String ip;
    private LocalDateTime localDateTime;
    private SigninState status;

}
