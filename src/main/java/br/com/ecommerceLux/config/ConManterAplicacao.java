package br.com.ecommerceLux.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;


@Component
public class ConManterAplicacao {
    private Logger log = LoggerFactory.getLogger(ConManterAplicacao.class);

    RestTemplate restTemplate = new RestTemplate();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {

        ResponseEntity<String> result
                = restTemplate.getForEntity("https://protipo-ecommerce-api.onrender.com/produtos/", String.class);

        log.info("Response status: {}", result.getStatusCode());
        log.info("Response headers: {}", result.getHeaders());
        log.info("Contents: {}", result.getBody());

        log.info("Realizando request para manter a aplicação no ar");
    }

}
