package ua.serg.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.serg.interfaces.InterfaceMyCollectionTarif;
import ua.serg.objects.Tarif;

/**
 * Created by shpak on 09.09.2016.
 */
public class CollectionTarifs implements InterfaceMyCollectionTarif {

    private ObservableList<Tarif> tarifsList = FXCollections.observableArrayList();

    @Override
    public void add(Tarif tarif) {
        tarifsList.add(tarif);
    }
    public void add(ObservableList<Tarif> listTarif) {
        tarifsList.addAll(listTarif);
    }

    @Override
    public void update(Tarif tarif) {

    }

    @Override
    public void delete(Tarif tarif) {

    }

    @Override
    public void clear() {
        tarifsList.clear();
    }

    public ObservableList<Tarif> getTarifsList() {
        return tarifsList;
    }
}
