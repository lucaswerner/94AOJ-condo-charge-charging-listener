package br.com.condocharge;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.reactive.messaging.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.condocharge.api.StationAPI;
import br.com.condocharge.dto.StationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.Set;
import java.util.stream.Stream;

@ApplicationScoped
public class MyMessagingApplication {

    @Inject
    @Channel("words-out")
    Emitter<String> emitter;

    @RestClient
    StationAPI stationApi;

    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        Set<StationDTO> all = stationApi.getAll();
        for (StationDTO station : all) {
            System.out.println(station);
        }
        Stream.of("Hello", "with", "Quarkus", "Messaging", "message").forEach(string -> emitter.send(string));
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("words-in")
    @Outgoing("uppercase")
    public Message<String> toUpperCase(Message<String> message) {
        return message.withPayload(message.getPayload().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("uppercase")
    public void sink(String word) {
        System.out.println(">> " + word);
    }
}
