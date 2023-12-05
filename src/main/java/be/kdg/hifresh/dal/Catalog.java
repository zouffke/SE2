package be.kdg.hifresh.dal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class Catalog<T> {
    private final List<T> list;

    protected Catalog() {
        this.list = new ArrayList<>();
    }

    public boolean addObjToCatalog(T obj) {
        return this.list.add(obj);
    }

    public T getObjFromCatalog(int index) {
        return this.list.get(index);
    }

    public int getIndexOfObj(T obj){
        return this.list.indexOf(obj);
    }

    protected List<T> getList() {
        return this.list;
    }

    public int getIndexOfObjById(int objId) throws InvocationTargetException, IllegalAccessException {
        for (T t : list) {
            for (Method m : t.getClass().getDeclaredMethods()) {
                if (m.getName().equals("getId")) {
                    if ((int) m.invoke(t) == objId) {
                        return list.indexOf(t);
                    }
                }
            }
        }
        return -1;
    }
}