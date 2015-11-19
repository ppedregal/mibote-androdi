package com.thinknowa.botin.sdk;

/**
 * Created by ppedregal on 19/11/15.
 */
public class Amount {

    private Double value;
    private String currency;

    public Amount(){
        this(0,"EUR");
    }

    public Amount(Number value,String currency){
        this.value=value.doubleValue();
        this.currency=currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
