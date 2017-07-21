package ua.serg.impl;

import ua.serg.interfaces.Payable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


/**
 * Created by shpak on 01.09.2016.
 */
public abstract class AbstrsctPay implements Payable {
    protected String name;
    protected LocalDate datePay;
    protected String period;
    protected BigDecimal sum;
    protected BigDecimal sumPerMonth;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDatePay() {
        return datePay;
    }
    public void setDatePay(LocalDate datePay) {
        this.datePay = datePay;
    }

    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setSumPerMonth(BigDecimal sumPerMonth) {
        this.sumPerMonth = sumPerMonth.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
