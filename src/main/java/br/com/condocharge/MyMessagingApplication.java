package br.com.condocharge;

import java.time.LocalDateTime;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.condocharge.api.ChargeAPI;
import br.com.condocharge.dto.PatchChargeDTO;
import br.com.condocharge.enums.ChargeStatus;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class MyMessagingApplication {

    @Inject
    @Channel("update-charge-out")
    Emitter<PatchChargeDTO> emitter;

    @RestClient
    ChargeAPI chargeAPI;

    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource
     * or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        // Set<StationDTO> all = stationApi.getAll();
        // for (StationDTO station : all) {
        // System.out.println(station);
        // }
        // Stream.of("Hello", "with", "Quarkus", "Messaging", "message").forEach(string
        // -> emitter.send(string));

        // "http://localhost:8090/api/v1/condominiums/12312312300012/stations/6cc501c3-6a23-4bb4-a5c1-de6d44ef66fd/charges"
        // final PatchChargeDTO patchChargeDTO = new PatchChargeDTO();
        // patchChargeDTO.setCnpj("12312312300012");
        // patchChargeDTO.setStationId("6cc501c3-6a23-4bb4-a5c1-de6d44ef66fd");
        // patchChargeDTO.setChargeId(1L);

        // patchChargeDTO.setChargeEndPreview(LocalDateTime.now().plusHours(2L));
        // patchChargeDTO.setChargeEnd(null);
        // patchChargeDTO.setEnergyConsumption(20);
        // patchChargeDTO.setStatus(ChargeStatus.CHARGING);

        // emitter.send(patchChargeDTO);
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to
     * the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("update-charge-in")
    public void consume(PatchChargeDTO payload) {
        System.out.println("Updating charge...");
        chargeAPI.patchChargeInfo(payload.getCnpj(), payload.getStationId(), payload.getChargeId(), payload);
    }

}
