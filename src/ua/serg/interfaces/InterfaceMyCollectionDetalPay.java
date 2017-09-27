package ua.serg.interfaces;

import ua.serg.objects.DetaliziedPay;


/**
 * Created by shpak on 09.09.2016.
 */
public interface InterfaceMyCollectionDetalPay {
    void add(DetaliziedPay pay);
    void delete(DetaliziedPay pay);
    void clear();
}
