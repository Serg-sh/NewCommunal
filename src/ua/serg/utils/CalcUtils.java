package ua.serg.utils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by shpak on 20.09.2016.
 */
public class CalcUtils {



    public static void calcElectric(LocalDate startDate, LocalDate endDate, Integer startMetr, Integer endMetr) {
        int before100 = 100;
        int use600 = 0;
        int use100 = 0;
        int countMonth = Period.between(endDate, startDate).getMonths()+1;
        int toUse = endMetr - startMetr;
        if (toUse <= countMonth * before100){
            use100=toUse;
            use600=0;
        }else{
            use100 = countMonth * before100;
            use600 = toUse - use100;
        }
        System.out.println(use100 +"  -  "+ use600 + "   :" + toUse + " : " + countMonth);

    }





}
