package com.utoppia.stockquoteapi.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utoppia.stockquoteapi.service.FinnHubService;
import jakarta.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It represents the json stock quote response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "c",
        "d",
        "dp",
        "h",
        "l",
        "o",
        "pc",
        "t"
})
@Generated("jsonschema2pojo")
public class StockQuoteResponse {

    @JsonProperty("c")
    private Double currentPrice;
    @JsonProperty("d")
    private Double change;
    @JsonProperty("dp")
    private Double percentChange;
    @JsonProperty("h")
    private Double highPriceOfDay;
    @JsonProperty("l")
    private Double lowPriceOfDay;
    @JsonProperty("o")
    private Double openPriceOfDay;
    @JsonProperty("pc")
    private Double previousClosePrice;
    @JsonProperty("t")
    private Long timestamp;

    private final static Logger logger = LoggerFactory.getLogger(StockQuoteResponse.class);

    @JsonProperty("c")
    public Double getCurrentPrice() {
        return currentPrice;
    }

    @JsonProperty("c")
    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @JsonProperty("d")
    public Double getChange() {
        return change;
    }

    @JsonProperty("d")
    public void setChange(final Double change) {
        this.change = change;
    }

    @JsonProperty("dp")
    public Double getPercentChange() {
        return percentChange;
    }

    @JsonProperty("dp")
    public void setPercentChange(final Double percentChange) {
        this.percentChange = percentChange;
    }

    @JsonProperty("h")
    public Double getHighPriceOfDay() {
        return highPriceOfDay;
    }

    @JsonProperty("h")
    public void setHighPriceOfDay(final Double highPriceOfDay) {
        this.highPriceOfDay = highPriceOfDay;
    }

    @JsonProperty("l")
    public Double getLowPriceOfDay() {
        return lowPriceOfDay;
    }

    @JsonProperty("l")
    public void setLowPriceOfDay(final Double lowPriceOfDay) {
        this.lowPriceOfDay = lowPriceOfDay;
    }

    @JsonProperty("o")
    public Double getOpenPriceOfDay() {
        return openPriceOfDay;
    }

    @JsonProperty("o")
    public void setOpenPriceOfDay(final Double openPriceOfDay) {
        this.openPriceOfDay = openPriceOfDay;
    }

    @JsonProperty("pc")
    public Double getPreviousClosePrice() {
        return previousClosePrice;
    }

    @JsonProperty("pc")
    public void setPreviousClosePrice(final Double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }

    @JsonProperty("t")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("t")
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
