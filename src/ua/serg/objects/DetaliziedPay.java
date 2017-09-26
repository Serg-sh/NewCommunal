package ua.serg.objects;

import ua.serg.impl.CollectionService;

import java.math.BigDecimal;
import java.time.LocalDate;



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

}
