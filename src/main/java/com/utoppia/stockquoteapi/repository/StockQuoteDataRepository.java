package com.utoppia.stockquoteapi.repository;

import com.utoppia.stockquoteapi.entity.StockQuoteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * It represents the stock quote date repository
 */
@Repository
public interface StockQuoteDataRepository extends JpaRepository <StockQuoteData,Integer>{
    List<StockQuoteData> findBySymbol(String symbol);
}
