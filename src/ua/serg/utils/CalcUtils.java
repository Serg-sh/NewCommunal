package ua.serg.utils;

import javafx.collections.ObservableList;
import ua.serg.objects.MonthOfHeating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by shpak on 20.09.2016.
 */
public class CalcUtils {


    public static BigDecimal calcElectric(LocalDate startDate, LocalDate endDate, Integer startMetr, Integer endMetr) {
        BigDecimal sum = new BigDecimal(0);
        int before100 = 100;
        int use600 = 0;
        int use100 = 0;
        int countMonth = Period.between(startDate, endDate).getMonths() + 1;
        int toUse = endMetr - startMetr;
        if (toUse <= countMonth * before100) {
            use100 = toUse;
            use600 = 0;
        } else {
            use100 = countMonth * before100;
            use600 = toUse - use100;
        }

        sum = DBUtils.getTarifLastDate("Электроэнергия до 100 кВатт").multiply(BigDecimal.valueOf(new Double(use100))).add(
                DBUtils.getTarifLastDate("Электроэнергия до 600 кВатт").multiply(BigDecimal.valueOf(new Double(use600))));

        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcWater(Integer startMetr, Integer endMetr) {

        BigDecimal sum;
        int toUse = endMetr - startMetr;
        sum = DBUtils.getTarifLastDate("Водоснабжение").multiply(BigDecimal.valueOf(new Double(toUse)));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcDwelling(LocalDate startDate, LocalDate endDate, Double area) {
        BigDecimal sum;
        int countMonth = Period.between(startDate, endDate).getMonths() + 1;
        sum = DBUtils.getTarifLastDate("Квартплата").multiply(BigDecimal.valueOf(area)).multiply(BigDecimal.valueOf(new Double(countMonth)));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcGas(LocalDate startDate, LocalDate endDate, Integer countPeople) {
        BigDecimal sum;
        int countMonth = Period.between(startDate, endDate).getMonths() + 1;
        sum = DBUtils.getTarifLastDate("Газоснабжение").multiply(BigDecimal.valueOf(new Double(countPeople))).multiply(BigDecimal.valueOf(new Double(countMonth))).multiply(new BigDecimal(4.5));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcGas(LocalDate startDate, LocalDate endDate, Integer startReadings, Integer endReadings) {
        BigDecimal sum;
        Integer use = endReadings - startReadings;
        sum = DBUtils.getTarifLastDate("Газоснабжение").multiply(new BigDecimal(use));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcElevator(LocalDate startDate, LocalDate endDate, Integer countPeople) {
        BigDecimal sum;
        int countMonth = Period.between(startDate, endDate).getMonths() + 1;
        sum = DBUtils.getTarifLastDate("Лифт").multiply(BigDecimal.valueOf(new Double(countPeople))).multiply(BigDecimal.valueOf(new Double(countMonth)));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcGarbage(LocalDate startDate, LocalDate endDate, Integer countPeople) {
        BigDecimal sum;
        int countMonth = Period.between(startDate, endDate).getMonths() + 1;
        sum = DBUtils.getTarifLastDate("Вывоз мусора").multiply(BigDecimal.valueOf(new Double(countPeople))).multiply(BigDecimal.valueOf(new Double(countMonth)));
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcHeatingSingleMonth(BigDecimal tarif, Double area) {
        return tarif.multiply(BigDecimal.valueOf(area)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcHeatingAllMonth(ObservableList<MonthOfHeating> tmpHeating) {
        BigDecimal sum = new BigDecimal(0);
        for (MonthOfHeating monthOfHeating : tmpHeating) {
            sum = sum.add(monthOfHeating.getSum());
        }
        return sum;
    }
}
