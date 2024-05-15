package com.utoppia.stockquoteapi.service;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.entity.StockQuoteData;
import com.utoppia.stockquoteapi.repository.StockQuoteDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * StockQuoteData Service
 */
@Service
public class StockQuoteDataService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockQuoteDataRepository stockQuoteDataRepository;

    /**
     * Get Stock Quote Data By Symbol from DB
     * @param symbol
     * @return List<StockQuoteDataDto>
     */
    public List<StockQuoteDataDto> getStockQuoteDataBySymbolFromDb(final String symbol) {

        List<StockQuoteData> stockQuoteDataList = stockQuoteDataRepository.findBySymbol(symbol);

        return stockQuoteDataList.stream()
                .map(stockQuoteData -> modelMapper.map(stockQuoteData, StockQuoteDataDto.class))
                .collect(Collectors.toList());
    }
}
