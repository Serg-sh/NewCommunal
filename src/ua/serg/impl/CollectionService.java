package ua.serg.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ua.serg.interfaces.InterfaceMyCollectionService;


/**
 * Created by shpak on 09.09.2016.
 */
public class CollectionService implements InterfaceMyCollectionService {

    private ObservableList<AbstrsctPay> serviceList = FXCollections.observableArrayList();


    @Override
    public void add(AbstrsctPay service) {
        serviceList.add(service);
    }
    public void add(ObservableList<AbstrsctPay> listService) {
        serviceList.addAll(listService);
    }
    public void add(int index, AbstrsctPay service) {
        serviceList.add(index, service);
    }

    @Override
    public void delete(AbstrsctPay service) {
        serviceList.remove(service);
    }

    @Override
    public void clear() {
        serviceList.clear();
    }

    public ObservableList<AbstrsctPay> getServiceList() {
        return serviceList;
    }
}
