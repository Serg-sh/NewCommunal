package ua.serg.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.interfaces.InterfaceMyCollectionService;
import ua.serg.interfaces.InterfaceMyCollectionServiceMetr;
import ua.serg.objects.AbstractPayWithMetrReadings;


/**
 * Created by shpak on 09.09.2016.
 */
public class CollectionServiceMetr implements InterfaceMyCollectionServiceMetr {

    private ObservableList<AbstractPayWithMetrReadings> serviceList = FXCollections.observableArrayList();


    @Override
    public void add(AbstractPayWithMetrReadings service) {
        serviceList.add(service);
    }
    public void add(ObservableList<AbstractPayWithMetrReadings> list) {
        serviceList.addAll(list);
    }
    public void add(int index, AbstractPayWithMetrReadings service) {
        serviceList.add(index, service);
    }

    @Override
    public void delete(AbstractPayWithMetrReadings service) {
        serviceList.remove(service);
    }

    @Override
    public void clear() {
        serviceList.clear();
    }

    public ObservableList<AbstractPayWithMetrReadings> getServiceList() {
        return serviceList;
    }
}
