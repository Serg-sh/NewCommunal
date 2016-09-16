package ua.serg.objects;

import ua.serg.impl.AbstrsctPay;

import java.math.BigDecimal;

/**
 * Created by shpak on 01.09.2016.
 */
public class Wather extends AbstractPayWithMetrReadings {

    private Integer metrReadings;
    private Integer toUse;


    @Override
    public BigDecimal getSum() {
        return null;
    }

    @Override
    public BigDecimal getSumPerMonth() {
        return null;
    }
}
