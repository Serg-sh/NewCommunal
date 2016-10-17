package ua.serg.objects;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.math.BigDecimal;
import java.time.Month;

/**
 * Created by shpak on 05.10.2016.
 */
public class MonthOfHeating {
    private String name;
    private BigDecimal tarif = new BigDecimal(0.);
    private BigDecimal sum = new BigDecimal(0.);
    private Month month;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public BigDecimal getTarif() {
        return tarif;
    }
    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public Month getMonth() {
        return month;
    }
    public void setMonth(Month month) {
        this.month = month;
    }

    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
