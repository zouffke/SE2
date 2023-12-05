package be.kdg.hifresh.dal;

public interface IManager {
    <T> boolean addTtoCatalog(T object, Catalog<T> catalog);

    <T> T getObjFromCatalog(int index, Catalog<T> catalog);
}
