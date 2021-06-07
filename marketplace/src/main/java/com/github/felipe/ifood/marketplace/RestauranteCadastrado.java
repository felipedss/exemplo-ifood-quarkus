package com.github.felipe.ifood.marketplace;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RestauranteCadastrado {

    @Incoming("restaurantes")
    public void receber(JsonObject json) {
        System.out.println("Recebendo restaurante");
        System.out.println(json);
    }

}
