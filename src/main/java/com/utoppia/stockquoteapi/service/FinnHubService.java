package com.utoppia.stockquoteapi.service;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.entity.StockQuoteData;
import com.utoppia.stockquoteapi.entity.StockQuoteResponse;
import com.utoppia.stockquoteapi.repository.StockQuoteDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * FinnHub Service
 */
@Service
public class FinnHubService {
    @Value("${finnhub.token}")
    private String finnHubToken;

    @Autowired
    private WebClient webClientFinnHubApi;

    @Autowired
    private StockQuoteDataRepository stockQuoteDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final static Logger logger = LoggerFactory.getLogger(FinnHubService.class);
    private final static String QP_NAME_SYMBOL = "symbol";
    private final static String QP_NAME_TOKEN = "token";

    private void validateStockQuoteResponse(StockQuoteResponse stockQuoteResponse){
        if (Optional.ofNullable(stockQuoteResponse.getCurrentPrice())
                .map(price -> Double.compare(price, 0) == 0)
                .orElse(false)
                && stockQuoteResponse.getTimestamp() == 0) {
            logger.debug("Stock quote not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get Stock Quote By Symbol
     * @param symbol
     * @return StockQuoteResponse
     */
    public StockQuoteResponse getStockQuote(final String symbol){
        return webClientFinnHubApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam(QP_NAME_SYMBOL, String.format("%s", symbol))
                        .queryParam(QP_NAME_TOKEN, finnHubToken)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<StockQuoteResponse>() {})
                .block();
    }

    /**
     * Update Stock Quote Data with last quote information provided by FinnHub
     * @param symbol
     */
    public StockQuoteDataDto updateStockQuoteData(final String symbol){

        StockQuoteResponse stockQuoteResponse =  this.getStockQuote(symbol);
        logger.debug("Stock Quote Response is {}",stockQuoteResponse.toString());

        this.validateStockQuoteResponse(stockQuoteResponse);
        StockQuoteData stockQuoteData = modelMapper.map(stockQuoteResponse, StockQuoteData.class);
        stockQuoteData.setSymbol(symbol);

        logger.debug("Stock Quote Data is {}", stockQuoteData.toString());

        stockQuoteDataRepository.save(stockQuoteData);

        return modelMapper.map(stockQuoteData, StockQuoteDataDto.class);

    }
}
