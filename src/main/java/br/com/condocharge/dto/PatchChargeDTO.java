package br.com.condocharge.dto;

import java.time.LocalDateTime;

import br.com.condocharge.enums.ChargeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatchChargeDTO {
    private String cnpj;
    private String stationId;
    private Long chargeId;
    private LocalDateTime chargeEndPreview;
    private LocalDateTime chargeEnd;
    private Integer energyConsumption;
    private ChargeStatus status;
}
