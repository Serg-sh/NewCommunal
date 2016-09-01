package ua.serg.impl;

import ua.serg.interfaces.Payable;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by shpak on 01.09.2016.
 */
public abstract class AbstrsctPay implements Payable {
    protected String name;
    protected Date datePay;
    protected String period;
    protected BigDecimal sum;
    protected BigDecimal sumPerMonth;

}
