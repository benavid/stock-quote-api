package com.utoppia.stockquoteapi.service;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.entity.StockQuoteData;
import com.utoppia.stockquoteapi.repository.StockQuoteDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StockQuoteDataServiceTest {

    @Mock
    private StockQuoteDataRepository stockQuoteDataRepository;

    @InjectMocks
    private StockQuoteDataService stockQuoteDataService;

    @Test
    void getStockQuoteDataBySymbolFromDb_success_withValidSymbol(){
        List<StockQuoteData> stockQuoteDataList = getStockQuoteList();

        Mockito.when(stockQuoteDataRepository.findBySymbol("MSFT")).thenReturn(stockQuoteDataList);

        List<StockQuoteDataDto> stockQuoteDataDtoList = stockQuoteDataService.getStockQuoteDataBySymbolFromDb("MSFT");

        assertEquals(stockQuoteDataDtoList.size(), 3);
        assertEquals(stockQuoteDataDtoList.getFirst().getSymbol(), "MSFT");
        assertEquals(stockQuoteDataDtoList.getFirst().getStockQuoteDataId(), 1);
        assertEquals(stockQuoteDataDtoList.getFirst().getCurrentPrice(), 12.1);
        assertEquals(stockQuoteDataDtoList.getFirst().getPreviousClosePrice(), 11.7);
        assertEquals(stockQuoteDataDtoList.getFirst().getChange(), 0.8);
        assertEquals(stockQuoteDataDtoList.getFirst().getPercentChange(), 0.6);
        assertEquals(stockQuoteDataDtoList.getFirst().getOpenPriceOfDay(), 1.1);
        assertEquals(stockQuoteDataDtoList.getFirst().getHighPriceOfDay(), 12.6);
    }

    private List<StockQuoteData> getStockQuoteList() {
        StockQuoteData stockQuote01 = new StockQuoteData();
        stockQuote01.setSymbol("MSFT");
        stockQuote01.setStockQuoteDataId(1);
        stockQuote01.setCurrentPrice(12.1);
        stockQuote01.setPreviousClosePrice(11.7);
        stockQuote01.setChange(0.8);
        stockQuote01.setPercentChange(0.6);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        StockQuoteData stockQuote02 = new StockQuoteData();
        stockQuote02.setSymbol("MSFT");
        stockQuote02.setStockQuoteDataId(2);
        stockQuote02.setCurrentPrice(14.1);
        stockQuote02.setPreviousClosePrice(11.7);
        stockQuote02.setChange(0.1);
        stockQuote02.setPercentChange(0.3);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        StockQuoteData stockQuote03 = new StockQuoteData();
        stockQuote03.setSymbol("MSFT");
        stockQuote03.setStockQuoteDataId(3);
        stockQuote03.setCurrentPrice(14.1);
        stockQuote03.setPreviousClosePrice(11.7);
        stockQuote03.setChange(0.1);
        stockQuote03.setPercentChange(0.3);
        stockQuote01.setOpenPriceOfDay(1.1);
        stockQuote01.setHighPriceOfDay(12.6);

        return List.of(stockQuote01,stockQuote02,stockQuote03);

    }
}
