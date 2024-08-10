package tools;

import java.util.*;


public class ElementRegistry <T> {
    private Object container;

    public ElementRegistry(Class<?> cClass) {
        if (Map.class.isAssignableFrom(cClass)) {
            container = new HashMap<String, T>();
        } else if (List.class.isAssignableFrom(cClass)) {
            container = new LinkedList<T>();
        }
    }

    public class Entry {
        private String name;
        private T ele;

        private Entry(String name, T ele) {
            this.name = name;
            this.ele = ele;
        }
    }

    public Entry newEntry(String name, T ele) {
        return new Entry(name, ele);
    }

    public void register(String name, T ele) {
        if (isMap()) ((Map<String, T>) container).putIfAbsent(name, ele);
    }

    public void register(Entry... kvs) {
        if (isMap()) Arrays.stream(kvs).forEach(kv -> ((Map<String, T>) container).putIfAbsent(kv.name, kv.ele));
    }

    public void register(T... eles) {
        if (isList()) ((List<T>) container).addAll(Arrays.asList(eles));
    }

    public void register(int index, T ele) {
        if (isList()) ((List<T>) container).add(index, ele);
    }

    public T get(String name) {
        if (isMap()) return ((Map<String, T>) container).get(name);
        return null;
    }

    public T get(int index) {
        if (isList()) return ((List<T>) container).get(index);
        return null;
    }

    public void remove(String name) {
        if (isMap()) ((Map<String, T>) container).remove(name);
    }

    public void remove(int index) {
        if (isList()) ((List<T>) container).remove(index);
    }

    public HashMap<String, T> elements() {
        HashMap<String, T> tempMap = null;
        if (isMap()) {
            tempMap = new HashMap<>();
            ((Map<String, T>) container).forEach(tempMap::putIfAbsent);
        }
        return tempMap;
    }

    public List<T> array() {
        if (isList()) return new ArrayList<>(((List<T>) container));
        return null;
    }

    public int size() {
        if (isList()) return ((List<T>) container).size();
        if (isMap()) return ((Map<String, T>) container).size();
        return 0;
    }

    private boolean isList() {
        return container instanceof List;
    }

    private boolean isMap() {
        return container instanceof Map;
    }
}
