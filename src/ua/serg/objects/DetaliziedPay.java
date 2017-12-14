package ua.serg.objects;

import ua.serg.impl.CollectionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


public class DetaliziedPay {
    private String nameTarif;
    private LocalDate dateOfPay;
    private String periodOfPay;
    private BigDecimal sumOfPay;
    private BigDecimal sumPerMonth;


    public DetaliziedPay() {
    }

    public DetaliziedPay(String nameTarif, LocalDate dateOfPay, String periodOfPay, BigDecimal sumOfPay, BigDecimal sumPerMonth) {
        this.nameTarif = nameTarif;
        this.dateOfPay = dateOfPay;
        this.periodOfPay = periodOfPay;
        this.sumOfPay = sumOfPay;
        this.sumPerMonth = sumPerMonth;
    }

    public void calcSumPerMonth(){
        if (periodOfPay.length() > 10){
            String d1 = periodOfPay.substring(0, 10);
            String d2 = periodOfPay.substring(13, 23);
            Period p = Period.between(LocalDate.parse(d1), LocalDate.parse(d2));
            int count = p.getMonths() + 1;
            sumPerMonth = sumOfPay.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
        } else {
            sumPerMonth = BigDecimal.ZERO;
            this.periodOfPay = "";
        }

    }

    public void setNameTarif(String nameTarif) {
        this.nameTarif = nameTarif;
    }

    public void setDateOfPay(LocalDate dateOfPay) {
        this.dateOfPay = dateOfPay;
    }

    public void setPeriodOfPay(String periodOfPay) {
        this.periodOfPay = periodOfPay;
    }

    public void setSumOfPay(BigDecimal sumOfPay) {
        this.sumOfPay = sumOfPay;
    }

    public void setSumPerMonth(BigDecimal sumPerMonth) {
        this.sumPerMonth = sumPerMonth;
    }

    public String getNameTarif() {
        return nameTarif;
    }

    public LocalDate getDateOfPay() {
        return dateOfPay;
    }

    public String getPeriodOfPay() {
        return periodOfPay;
    }

    public BigDecimal getSumOfPay() {
        return sumOfPay;
    }

    public BigDecimal getSumPerMonth() {
        return sumPerMonth;
    }
}
