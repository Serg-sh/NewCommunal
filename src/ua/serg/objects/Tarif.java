package ua.serg.objects;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Created by shpak on 05.09.2016.
 */
public class Tarif {
    private Tarif() {
    }

    public Tarif(String name, LocalDate dateChangeOfTarif, BigDecimal cost) {
        this.name = name;
        this.dateChangeOfTarif = dateChangeOfTarif;
        this.cost = cost;
    }

    private String name;
    private LocalDate dateChangeOfTarif;
    private BigDecimal cost;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateChangeOfTarif() {
        return dateChangeOfTarif;
    }

    public void setDateChangeOfTarif(LocalDate dateChangeOfTarif) {
        this.dateChangeOfTarif = dateChangeOfTarif;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " Стимость: " + cost + " грн." + " Дата изменения: " + dateChangeOfTarif;
    }

    @Override
    public boolean equals(Object trf) {
        Tarif tarif = (Tarif) trf;
        if (tarif.getName().equals(this.getName())
                && tarif.dateChangeOfTarif.equals(this.dateChangeOfTarif)
                && tarif.getCost()==this.getCost()){
            return true;
        }
        return false;
    }
}
