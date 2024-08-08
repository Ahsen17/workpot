package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ElementRegistry<T> {
    private HashMap<String, T> eleMap;

    private ArrayList<T> eleArray = new ArrayList<>();

    public class Entry {
        private String name;
        private T ele;

        private Entry(String name, T ele) {
            this.name = name;
            this.ele = ele;
        }
    }

    public ElementRegistry() {
        eleMap = new HashMap<>();
    }

    public ElementRegistry(Class<?> cClass) {
        if (cClass.equals(HashMap.class)) {
            eleMap = new HashMap<>();
        } else if (cClass.equals(ArrayList.class)) {
            eleArray = new ArrayList<>();
        }
    }

    public Entry newEntry(String name, T ele) {
        return new Entry(name, ele);
    }

    public void register(String name, T ele) {
        eleMap.putIfAbsent(name, ele);
    }

    @SafeVarargs
    public final void register(Entry... kvs) {
        for (Entry kv : kvs) {
            if (kv.ele != null) {
                eleMap.putIfAbsent(kv.name, kv.ele);
            }
        }
    }

    public void register(T ele) {
        eleArray.add(ele);
    }

    public void register(T[] eles) {
        eleArray.addAll(List.of(eles));
    }

    public T get(String name) {
        return elements().get(name);
    }

    public T get(int index) {
        return eleArray.get(index);
    }

    public HashMap<String, T> elements() {
        HashMap<String, T> tempMap = new HashMap<>();
        eleMap.forEach(tempMap::putIfAbsent);
        return tempMap;
    }

    public ArrayList<T> array() {
        return new ArrayList<>(eleArray);
    }

    public int size() {
        if (eleMap != null) return eleMap.size();
        else return eleArray.size();
    }
}
