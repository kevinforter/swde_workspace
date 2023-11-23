package ch.hslu.swde.wda.persister;

import java.util.List;

public interface GenericDAO<T> {

    void speichern(T obj);
    void loeschen(int id);
    void aktualisieren(T obj);
    T findById(int id);
    List<T> alle();
}
