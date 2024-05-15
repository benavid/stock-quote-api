package com.utoppia.stockquoteapi.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It represents the stock quote date in db
 */
@Entity
@Table(name = "stock_quote_data")
public class StockQuoteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_quote_data_id")
    private Integer stockQuoteDataId;
    @Column(name="symbol")
    private String symbol;
    @Column(name="current_price")
    private Double currentPrice;

    @Column(name="change")
    private Double change;
    @Column(name="percent_change")
    private Double percentChange;

    @Column(name="high_price_of_day")
    private Double highPriceOfDay;
    @Column(name="low_price_of_day")
    private Double lowPriceOfDay;
    @Column(name="open_price_of_day")
    private Double openPriceOfDay;
    @Column(name="previous_close_price")
    private Double previousClosePrice;
    @Column(name="timestamp")
    private Long timestamp;

    private final static Logger logger = LoggerFactory.getLogger(StockQuoteData.class);

    public StockQuoteData(){
        //Empty Constructor
    }

    public Integer getStockQuoteDataId() {
        return stockQuoteDataId;
    }

    public void setStockQuoteDataId(final Integer stockQuoteDataId) {
        this.stockQuoteDataId = stockQuoteDataId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(final Double change) {
        this.change = change;
    }

    public Double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(final Double percentChange) {
        this.percentChange = percentChange;
    }

    public Double getHighPriceOfDay() {
        return highPriceOfDay;
    }

    public void setHighPriceOfDay(final Double highPriceOfDay) {
        this.highPriceOfDay = highPriceOfDay;
    }

    public Double getLowPriceOfDay() {
        return lowPriceOfDay;
    }

    public void setLowPriceOfDay(final Double lowPriceOfDay) {
        this.lowPriceOfDay = lowPriceOfDay;
    }

    public Double getOpenPriceOfDay() {
        return openPriceOfDay;
    }

    public void setOpenPriceOfDay(final Double openPriceOfDay) {
        this.openPriceOfDay = openPriceOfDay;
    }

    public Double getPreviousClosePrice() {
        return previousClosePrice;
    }

    public void setPreviousClosePrice(final Double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.debug("Error converting to JSON {}",e.getMessage());
            return "Error converting to JSON";
        }
    }
}
