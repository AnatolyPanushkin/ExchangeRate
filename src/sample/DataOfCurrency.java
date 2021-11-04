package sample;

import java.math.BigDecimal;

public class DataOfCurrency {
    public String name;
    public BigDecimal rate;

    public DataOfCurrency(String name, BigDecimal rate){
        this.name=name;
        this.rate=rate;
    }
    public DataOfCurrency(){}
}
