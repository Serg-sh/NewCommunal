package ua.serg.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.interfaces.InterfaceMyCollectionDetalPay;
import ua.serg.interfaces.InterfaceMyCollectionPay;
import ua.serg.objects.DetaliziedPay;
import ua.serg.objects.Pay;

/**
 * Created by shpak on 09.09.2016.
 */
public class CollectionDetalPays implements InterfaceMyCollectionDetalPay {

    private ObservableList<DetaliziedPay> paysList = FXCollections.observableArrayList();


    @Override
    public void add(DetaliziedPay pay) {
        paysList.add(pay);
    }
    public void add(ObservableList<DetaliziedPay> listPay){
        paysList.addAll(listPay);
    }

    @Override
    public void delete(DetaliziedPay pay) {
        paysList.remove(pay);
    }

    @Override
    public void clear() {
        paysList.clear();
    }

    public ObservableList<DetaliziedPay> getPaysList() {
        return paysList;
    }
}
