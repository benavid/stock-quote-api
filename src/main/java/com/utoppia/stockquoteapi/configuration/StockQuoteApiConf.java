package com.utoppia.stockquoteapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class StockQuoteApiConf {

    @Value("${finnhub.base.url}")
    private String finnHubBaseUrl;

    private final static Logger logger = LoggerFactory.getLogger(StockQuoteApiConf.class);

    /**
     * Log method and url
     * @param request
     */
    private static void logMethodAndUrl(ClientRequest request) {
        StringBuilder methodAndUrl = new StringBuilder();
        methodAndUrl.append(request.method().name())
                .append(" to ")
                .append(request.url());
        logger.debug(methodAndUrl.toString());
    }

    /**
     * Log headers
     * @param request
     */
    private static void logHeaders(ClientRequest request) {
        request.headers().forEach((name, values) -> {
            values.forEach(value -> {
                logNameAndValuePair(name, value);
            });
        });
    }

    private static void logNameAndValuePair(String name, String value) {
        logger.debug("{}={}", name, value);
    }

    /**
     * Log the response
     * @return ExchangeFilterFunction
     */
    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            HttpStatusCode status = clientResponse.statusCode();
            logger.debug("Returned status code {} ({})", status.value(), status.toString());
            if (clientResponse.statusCode() != null && (clientResponse.statusCode().is4xxClientError()
                    || clientResponse.statusCode().is5xxServerError())) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.debug("Body is {}", body);
                            return Mono.just(clientResponse);
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    /**
     * Log the request
     * @return ExchangeFilterFunction
     */
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            logMethodAndUrl(request);
            logHeaders(request);
            return Mono.just(request);
        });
    }

    /**
     * error handler
     * @return
     */
    private static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if(clientResponse.statusCode().value() == HttpStatus.UNAUTHORIZED.value() ) {
                return clientResponse.bodyToMono(String.class)
                        .defaultIfEmpty(clientResponse.statusCode().toString())
                        .flatMap(errorBody -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    @Bean
    public WebClient webClientFinnHubApi() {
        return WebClient.builder()
                .baseUrl(finnHubBaseUrl)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(errorHandler());
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .build();
    }

}
