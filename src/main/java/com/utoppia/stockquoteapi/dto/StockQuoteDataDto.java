package com.utoppia.stockquoteapi.dto;

/**
 * It represents a dto class to stock quote data
 */
public class StockQuoteDataDto {
    private Double stockQuoteDataId;
    private String symbol;
    private Double currentPrice;
    private Double change;
    private Double percentChange;
    private Double highPriceOfDay;
    private Double lowPriceOfDay;
    private Double openPriceOfDay;
    private Double previousClosePrice;
    private Long timestamp;

    public StockQuoteDataDto(){
        //Empty Constructor
    }

    /**
     * StockQuoteDataDto constructor
     * @param stockQuoteDataId
     * @param symbol
     * @param currentPrice
     * @param change
     * @param percentChange
     * @param highPriceOfDay
     * @param lowPriceOfDay
     * @param openPriceOfDay
     * @param previousClosePrice
     * @param timestamp
     */
    public StockQuoteDataDto(Double stockQuoteDataId,
                             String symbol,
                             Double currentPrice,
                             Double change,
                             Double percentChange,
                             Double highPriceOfDay,
                             Double lowPriceOfDay,
                             Double openPriceOfDay,
                             Double previousClosePrice,
                             Long timestamp) {
        this.stockQuoteDataId = stockQuoteDataId;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.change = change;
        this.percentChange = percentChange;
        this.highPriceOfDay = highPriceOfDay;
        this.lowPriceOfDay = lowPriceOfDay;
        this.openPriceOfDay = openPriceOfDay;
        this.previousClosePrice = previousClosePrice;
        this.timestamp = timestamp;
    }

    /**
     * getStockQuoteDataId
     * @return stockQuoteDataId
     */
    public Double getStockQuoteDataId() {
        return stockQuoteDataId;
    }

    /**
     * setStockQuoteDataId
     * @param stockQuoteDataId
     */
    public void setStockQuoteDataId(final Double stockQuoteDataId) {
        this.stockQuoteDataId = stockQuoteDataId;
    }

    /**
     * getSymbol
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * setSymbol
     * @param symbol
     */
    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * getCurrentPrice
     * @return currentPrice
     */
    public Double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * setCurrentPrice
     * @param currentPrice
     */
    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * getChange
     * @return change
     */
    public Double getChange() {
        return change;
    }

    /**
     * setChange
     * @param change
     */
    public void setChange(final Double change) {
        this.change = change;
    }

    /**
     * getPercentChange
     * @return percentChange
     */
    public Double getPercentChange() {
        return percentChange;
    }

    /**
     * setPercentChange
     * @param percentChange
     */
    public void setPercentChange(final Double percentChange) {
        this.percentChange = percentChange;
    }

    /**
     * getHighPriceOfDay
     * @return highPriceOfDay
     */
    public Double getHighPriceOfDay() {
        return highPriceOfDay;
    }

    /**
     * setHighPriceOfDay
     * @param highPriceOfDay
     */
    public void setHighPriceOfDay(final Double highPriceOfDay) {
        this.highPriceOfDay = highPriceOfDay;
    }

    /**
     * getLowPriceOfDay
     * @return lowPriceOfDay
     */
    public Double getLowPriceOfDay() {
        return lowPriceOfDay;
    }

    /**
     * setLowPriceOfDay
     * @param lowPriceOfDay
     */
    public void setLowPriceOfDay(final Double lowPriceOfDay) {
        this.lowPriceOfDay = lowPriceOfDay;
    }

    /**
     * getOpenPriceOfDay
     * @return openPriceOfDay
     */
    public Double getOpenPriceOfDay() {
        return openPriceOfDay;
    }

    /**
     * setOpenPriceOfDay
     * @param openPriceOfDay
     */
    public void setOpenPriceOfDay(final Double openPriceOfDay) {
        this.openPriceOfDay = openPriceOfDay;
    }

    /**
     * getPreviousClosePrice
     * @return previousClosePrice
     */
    public Double getPreviousClosePrice() {
        return previousClosePrice;
    }

    /**
     * setPreviousClosePrice
     * @param previousClosePrice
     */
    public void setPreviousClosePrice(final Double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }

    /**
     * getTimestamp
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * setTimestamp
     * @param timestamp
     */
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }
}
