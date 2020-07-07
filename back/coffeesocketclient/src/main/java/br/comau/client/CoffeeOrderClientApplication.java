package br.comau.client;

import br.comau.client.dto.CoffeeOrder;
import br.comau.client.rsocket.CoffeeServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class CoffeeOrderClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeOrderClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CoffeeServiceClient coffeeServiceClient) {
        return args ->
                coffeeServiceClient.receiveCoffeeOrders()
                        .flatMap(this::handleOrder)
                        .subscribe();
    }

    private Mono<Boolean> handleOrder(CoffeeOrder coffeeOrder) {
        System.out.println(coffeeOrder);
        return Mono.just(true);
    }
}


