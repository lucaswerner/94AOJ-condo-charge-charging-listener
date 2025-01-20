package br.com.condocharge.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.dto.PatchChargeDTO;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient(configKey = "charge-api")
public interface ChargeAPI {

    @Path("/condominiums/{cnpj}/stations/{stationId}/charges/{chargeId}")
    @PATCH
    ChargeDTO patchChargeInfo(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId,
            @PathParam("chargeId") Long chargeId,
            PatchChargeDTO patchCharge);

}
