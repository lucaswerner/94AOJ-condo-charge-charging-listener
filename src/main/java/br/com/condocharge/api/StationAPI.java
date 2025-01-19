package br.com.condocharge.api;

import java.util.Set;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.condocharge.dto.StationDTO;
import jakarta.ws.rs.GET;

@RegisterRestClient(configKey="station-api")
public interface StationAPI {

    @GET
    Set<StationDTO> getAll();

}
