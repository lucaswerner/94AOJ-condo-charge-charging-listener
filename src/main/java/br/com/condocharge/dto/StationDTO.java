package br.com.condocharge.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StationDTO {
private String id;
    private Integer condominiumId;
    private Integer number;
    private String status;
    private LocalDateTime creationDt;
    private LocalDateTime lastUpdate;
}
