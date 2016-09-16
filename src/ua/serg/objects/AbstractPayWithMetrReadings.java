package ua.serg.objects;

import ua.serg.impl.AbstrsctPay;



/**
 * Created by shpak on 01.09.2016.
 */
public abstract class AbstractPayWithMetrReadings extends AbstrsctPay {

    private Integer metrReadingsStart;
    private Integer metrReadingsEnd;
    private Integer toUse;

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
    public void setToUse() {
        this.toUse = metrReadingsEnd - metrReadingsStart;
    }
}
