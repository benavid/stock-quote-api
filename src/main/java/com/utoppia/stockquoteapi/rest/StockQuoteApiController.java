package com.utoppia.stockquoteapi.rest;

import com.utoppia.stockquoteapi.dto.StockQuoteDataDto;
import com.utoppia.stockquoteapi.service.FinnHubService;
import com.utoppia.stockquoteapi.service.StockQuoteDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/stockquotes")
public class StockQuoteApiController {
    @Autowired
    private StockQuoteDataService stockQuoteDataService;

    @Autowired
    private FinnHubService finnHubService;

    private void validateSymbol(final String symbol){
        if(symbol.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Retrieve stock quote data")
    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<StockQuoteDataDto>> getStockQuoteData(
            @RequestParam(required = true) String symbol ) {

        this.validateSymbol(symbol);
        return ResponseEntity.ok(stockQuoteDataService.getStockQuoteDataBySymbolFromDb(symbol));
    }

    @Operation(summary = "Update stock quote data")
    @PostMapping()
    @ResponseBody
    public ResponseEntity<StockQuoteDataDto> updateStockQuoteData(
            @RequestParam(required = true) String symbol) {

        this.validateSymbol(symbol);
        return ResponseEntity.ok(finnHubService.updateStockQuoteData(symbol));
    }
}
