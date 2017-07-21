package ua.serg.objects;

import ua.serg.impl.AbstrsctPay;

import java.math.BigDecimal;

/**
 * Created by shpak on 01.09.2016.
 */
public class Dwelling extends AbstrsctPay {


    @Override
    public BigDecimal getSum() {
        return sum;
    }

    @Override
    public BigDecimal getSumPerMonth() {
        return sumPerMonth;
    }
}
