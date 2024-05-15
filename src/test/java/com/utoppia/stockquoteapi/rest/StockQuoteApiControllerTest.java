package com.utoppia.stockquoteapi.rest;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.service.FinnHubService;
import com.utoppia.stockquoteapi.service.StockQuoteDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebMvcTest(StockQuoteApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StockQuoteApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StockQuoteDataService stockQuoteDataService;

    @MockBean
    FinnHubService finnHubService;

    @Test
    void getStockQuoteData_success_withValidSymbol() throws Exception {
        List<StockQuoteDataDto> stockQuoteDataDtoList = this.getStockQuoteList();

        Mockito.when(stockQuoteDataService.getStockQuoteDataBySymbolFromDb("MSFT"))
                .thenReturn(stockQuoteDataDtoList);

        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/stockquotes?symbol=MSFT")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void getStockQuoteData_fail_withBadRequestHttpStatus() throws Exception {
        Mockito.when(stockQuoteDataService.getStockQuoteDataBySymbolFromDb(""))
                .thenReturn(new ArrayList<StockQuoteDataDto>());

        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/stockquotes?symbol=")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest()) ;
    }

    @Test
    void updateStockQuoteData_success_withValidSymbol() throws Exception {
        StockQuoteDataDto stockQuote01 = new StockQuoteDataDto();

        stockQuote01.setSymbol("MSFT");
        stockQuote01.setStockQuoteDataId(Double.valueOf(1));
        stockQuote01.setCurrentPrice(12.1);
        stockQuote01.setPreviousClosePrice(11.7);
        stockQuote01.setChange(0.8);
        stockQuote01.setPercentChange(0.6);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        Mockito.when(finnHubService.updateStockQuoteData("MSFT"))
                .thenReturn(stockQuote01);

        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/stockquotes?symbol=MSFT")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk()) ;
    }

    @Test
    void updateStockQuoteData_fail_withNotFoundHttpStatus() throws Exception {

        Mockito.doThrow(new ResponseStatusException((HttpStatus.NOT_FOUND)))
                .when(finnHubService).updateStockQuoteData("123");

        ResultActions response = mockMvc.
                perform(MockMvcRequestBuilders.post("/v1/stockquotes?symbol=123")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound()) ;
    }

    private List<StockQuoteDataDto> getStockQuoteList() {
        StockQuoteDataDto stockQuote01 = new StockQuoteDataDto();
        stockQuote01.setSymbol("MSFT");
        stockQuote01.setStockQuoteDataId(Double.valueOf(1));
        stockQuote01.setCurrentPrice(12.1);
        stockQuote01.setPreviousClosePrice(11.7);
        stockQuote01.setChange(0.8);
        stockQuote01.setPercentChange(0.6);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        StockQuoteDataDto stockQuote02 = new StockQuoteDataDto();
        stockQuote02.setSymbol("MSFT");
        stockQuote02.setStockQuoteDataId(Double.valueOf(2));
        stockQuote02.setCurrentPrice(14.1);
        stockQuote02.setPreviousClosePrice(11.7);
        stockQuote02.setChange(0.1);
        stockQuote02.setPercentChange(0.3);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        StockQuoteDataDto stockQuote03 = new StockQuoteDataDto();
        stockQuote03.setSymbol("MSFT");
        stockQuote03.setStockQuoteDataId(Double.valueOf(3));
        stockQuote03.setCurrentPrice(14.1);
        stockQuote03.setPreviousClosePrice(11.7);
        stockQuote03.setChange(0.1);
        stockQuote03.setPercentChange(0.3);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        return List.of(stockQuote01,stockQuote02,stockQuote03);

    }
}
