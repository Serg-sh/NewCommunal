package ua.serg.objects;

import ua.serg.impl.AbstrsctPay;

import java.time.LocalDate;


/**
 * Created by shpak on 01.09.2016.
 */
public abstract class AbstractPayWithMetrReadings extends AbstrsctPay {

    protected String name;
    protected Integer metrReadingsStart;
    protected Integer metrReadingsEnd;
    protected Integer toUse;

    public Integer getMetrReadingsStart() {
        return metrReadingsStart;
    }
    public void setMetrReadingsStart(Integer metrReadingsStart) {
        this.metrReadingsStart = metrReadingsStart;
    }

    public Integer getMetrReadingsEnd() {
        return metrReadingsEnd;
    }
    public void setMetrReadingsEnd(Integer metrReadingsEnd) {
        this.metrReadingsEnd = metrReadingsEnd;
    }

    public Integer getToUse() {
        return toUse;
    }
    public void setToUse(Integer toUse) {
        this.toUse = toUse;
    }


}
