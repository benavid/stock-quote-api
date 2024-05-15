package com.utoppia.stockquoteapi.service;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.entity.StockQuoteResponse;
import com.utoppia.stockquoteapi.repository.StockQuoteDataRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
public class FinnHubServiceTest {

    @Mock
    private WebClient webClientFinnHubApi;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private StockQuoteDataRepository stockQuoteDataRepository;

    @InjectMocks
    private FinnHubService finnHubService;

    @Test
    void getStockQuote_success_withValidSymbol(){
        StockQuoteResponse expectedStockQuoteResponse = this.getStockQuoteResponse();

        doReturn(requestHeadersUriSpec).when(webClientFinnHubApi).get();

        // Captor for uri function
        ArgumentCaptor<Function<UriBuilder, URI>> uriFunctionCaptor = ArgumentCaptor.forClass(Function.class);
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(uriFunctionCaptor.capture());

        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(expectedStockQuoteResponse)).when(responseSpec).bodyToMono(any(ParameterizedTypeReference.class));

        StockQuoteResponse stockQuoteResponse = finnHubService.getStockQuote("AAPL");

        Assertions.assertEquals(stockQuoteResponse.getCurrentPrice(),Double.valueOf(189.72));
        Assertions.assertEquals(stockQuoteResponse.getChange(), Double.valueOf(2.29));
        Assertions.assertEquals(stockQuoteResponse.getPercentChange(),Double.valueOf(1.2218));
        Assertions.assertEquals(stockQuoteResponse.getHighPriceOfDay(),Double.valueOf(190.65));
        Assertions.assertEquals(stockQuoteResponse.getLowPriceOfDay(),Double.valueOf(187.38));
        Assertions.assertEquals(stockQuoteResponse.getOpenPriceOfDay(),Double.valueOf(187.98));
        Assertions.assertEquals(stockQuoteResponse.getPreviousClosePrice(),Double.valueOf(187.43));
        Assertions.assertEquals(stockQuoteResponse.getTimestamp(),Long.valueOf(1715803201));
    }

    @Test
    void updateStockQuoteData_success_withValidSymbol(){
        StockQuoteResponse expectedStockQuoteResponse = this.getStockQuoteResponse();

        doReturn(requestHeadersUriSpec).when(webClientFinnHubApi).get();

        // Captor for uri function
        ArgumentCaptor<Function<UriBuilder, URI>> uriFunctionCaptor = ArgumentCaptor.forClass(Function.class);
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(uriFunctionCaptor.capture());

        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(expectedStockQuoteResponse)).when(responseSpec).bodyToMono(any(ParameterizedTypeReference.class));

        StockQuoteDataDto stockQuoteDataDto = finnHubService.updateStockQuoteData("AAPL");

        Assertions.assertEquals(stockQuoteDataDto.getCurrentPrice(),Double.valueOf(189.72));
        Assertions.assertEquals(stockQuoteDataDto.getChange(), Double.valueOf(2.29));
        Assertions.assertEquals(stockQuoteDataDto.getPercentChange(),Double.valueOf(1.2218));
        Assertions.assertEquals(stockQuoteDataDto.getHighPriceOfDay(),Double.valueOf(190.65));
        Assertions.assertEquals(stockQuoteDataDto.getLowPriceOfDay(),Double.valueOf(187.38));
        Assertions.assertEquals(stockQuoteDataDto.getOpenPriceOfDay(),Double.valueOf(187.98));
        Assertions.assertEquals(stockQuoteDataDto.getPreviousClosePrice(),Double.valueOf(187.43));
        Assertions.assertEquals(stockQuoteDataDto.getTimestamp(),Long.valueOf(1715803201));

    }

    @Test
    void updateStockQuoteData_fail_withResponseStatusException(){
        StockQuoteResponse stockQuoteResponse = new StockQuoteResponse();
        stockQuoteResponse.setCurrentPrice(Double.valueOf(0));
        stockQuoteResponse.setTimestamp(Long.valueOf(0));


        doReturn(requestHeadersUriSpec).when(webClientFinnHubApi).get();

        // Captor for uri function
        ArgumentCaptor<Function<UriBuilder, URI>> uriFunctionCaptor = ArgumentCaptor.forClass(Function.class);
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(uriFunctionCaptor.capture());

        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(stockQuoteResponse)).when(responseSpec).bodyToMono(any(ParameterizedTypeReference.class));

        assertThrows(ResponseStatusException.class, () -> {
            finnHubService.updateStockQuoteData("AAPL");
        });
    }

    private StockQuoteResponse getStockQuoteResponse(){
        StockQuoteResponse stockQuoteResponse = new StockQuoteResponse();
        stockQuoteResponse.setCurrentPrice(189.72);
        stockQuoteResponse.setChange(2.29);
        stockQuoteResponse.setPercentChange(1.2218);
        stockQuoteResponse.setHighPriceOfDay(190.65);
        stockQuoteResponse.setLowPriceOfDay(187.38);
        stockQuoteResponse.setOpenPriceOfDay(187.98);
        stockQuoteResponse.setPreviousClosePrice(187.43);
        stockQuoteResponse.setTimestamp(Long.valueOf(1715803201));

        return stockQuoteResponse;
    }
}
