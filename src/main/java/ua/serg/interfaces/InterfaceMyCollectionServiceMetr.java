package ua.serg.interfaces;


import ua.serg.objects.AbstractPayWithMetrReadings;


/**
 * Created by shpak on 09.09.2016.
 */
public interface InterfaceMyCollectionServiceMetr {
    void add(AbstractPayWithMetrReadings service);
    void delete(AbstractPayWithMetrReadings service);
    void clear();
}
