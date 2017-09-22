package ua.serg.objects;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by shpak on 15.09.2016.
 */
public class Pay {
    private LocalDate dateOfPay;
    private BigDecimal costElectric;
    private BigDecimal costWather;
    private BigDecimal costHealting;
    private BigDecimal costDwelling;
    private BigDecimal costGas;
    private BigDecimal costElevator;
    private BigDecimal costGarbage;
    private BigDecimal sum;
    private String comment;

    public Pay() {
        this.dateOfPay = LocalDate.now();
        this.costElectric = BigDecimal.ZERO;
        this.costWather = BigDecimal.ZERO;
        this.costHealting = BigDecimal.ZERO;
        this.costDwelling = BigDecimal.ZERO;
        this.costGas = BigDecimal.ZERO;
        this.costElevator = BigDecimal.ZERO;
        this.costGarbage = BigDecimal.ZERO;
    }

    public LocalDate getDateOfPay() {
        return dateOfPay;
    }
    public void setDateOfPay(LocalDate dateOfPay) {
        this.dateOfPay = dateOfPay;
    }

    public BigDecimal getCostElectric() {
        return costElectric;
    }
    public void setCostElectric(BigDecimal costElectric) {
        this.costElectric = costElectric;
    }

    public BigDecimal getCostWather() {
        return costWather;
    }
    public void setCostWather(BigDecimal costWather) {
        this.costWather = costWather;
    }

    public BigDecimal getCostHealting() {
        return costHealting;
    }
    public void setCostHealting(BigDecimal costHealting) {
        this.costHealting = costHealting;
    }

    public BigDecimal getCostDwelling() {
        return costDwelling;
    }
    public void setCostDwelling(BigDecimal costDwelling) {
        this.costDwelling = costDwelling;
    }

    public BigDecimal getCostGas() {
        return costGas;
    }
    public void setCostGas(BigDecimal costGas) {
        this.costGas = costGas;
    }

    public BigDecimal getCostElevator() {
        return costElevator;
    }
    public void setCostElevator(BigDecimal costElevator) {
        this.costElevator = costElevator;
    }

    public BigDecimal getCostGarbage() {
        return costGarbage;
    }
    public void setCostGarbage(BigDecimal costGarbage) {
        this.costGarbage = costGarbage;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum){
        this.sum = sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return "DateOfPay - " + dateOfPay + " {" +
                ", Electric=" + costElectric + " grn." +
                ", Wather=" + costWather + " grn." +
                ", Healting=" + costHealting + " grn." +
                ", Dwelling=" + costDwelling + " grn." +
                ", Gas=" + costGas + " grn." +
                ", Elevator=" + costElevator + " grn." +
                ", Garbage=" + costGarbage + " grn." +
                ", comment='" + comment + '\'' +
                '}';
    }
}
