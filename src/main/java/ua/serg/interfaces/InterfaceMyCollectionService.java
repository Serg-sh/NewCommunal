package ua.serg.interfaces;

import ua.serg.impl.AbstrsctPay;
import ua.serg.objects.Pay;


/**
 * Created by shpak on 09.09.2016.
 */
public interface InterfaceMyCollectionService {
    void add(AbstrsctPay service);
    void delete(AbstrsctPay service);
    void clear();
}
