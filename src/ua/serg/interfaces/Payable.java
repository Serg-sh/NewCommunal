package ua.serg.interfaces;

import java.math.BigDecimal;

/**
 * Created by shpak on 01.09.2016.
 */
public interface Payable {
    BigDecimal getSum();
    BigDecimal getSumPerMonth();
}
