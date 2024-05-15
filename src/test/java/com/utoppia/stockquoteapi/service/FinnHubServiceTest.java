package com.utoppia.stockquoteapi.service;

import com.utoppia.stockquoteapi.repository.StockQuoteDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
public class FinnHubServiceTest {

    @Mock
    private WebClient webClientFinnHubApi;

    @Mock
    private StockQuoteDataRepository stockQuoteDataRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FinnHubService finnHubService;

    @Test
    void test(){

    }
}
