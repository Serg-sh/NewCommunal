package ua.serg.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.interfaces.InterfaceMyCollectionPay;
import ua.serg.interfaces.InterfaceMyCollectionTarif;
import ua.serg.objects.Pay;
import ua.serg.objects.Tarif;

/**
 * Created by shpak on 09.09.2016.
 */
public class CollectionPays implements InterfaceMyCollectionPay {

    private ObservableList<Pay> paysList = FXCollections.observableArrayList();


    @Override
    public void add(Pay pay) {
        paysList.add(pay);
    }
    public void add(ObservableList<Pay> listPay){
        paysList.addAll(listPay);
    }

    @Override
    public void delete(Pay pay) {
        paysList.remove(pay);
    }

    @Override
    public void clear() {
        paysList.clear();
    }

    public ObservableList<Pay> getPaysList() {
        return paysList;
    }
}
