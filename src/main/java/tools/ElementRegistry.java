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

    public boolean register(String name, T ele) {
        if (!isMap()) return false;
        ((Map<String, T>) container).putIfAbsent(name, ele);
        return true;
    }

    public boolean register(Entry... kvs) {
        if (!isMap()) return false;
        Arrays.stream(kvs).forEach(kv -> ((Map<String, T>) container).putIfAbsent(kv.name, kv.ele));
        return true;
    }

    public boolean register(T... eles) {
        if (!isList()) return false;
        ((List<T>) container).addAll(Arrays.asList(eles));
        return true;
    }

    public boolean register(int index, T ele) {
        if (!isList()) return false;
        ((List<T>) container).add(index, ele);
        return true;
    }

    public T get(String name) {
        if (isMap()) return ((Map<String, T>) container).get(name);
        return null;
    }

    public T get(int index) {
        if (isList()) return ((List<T>) container).get(index);
        return null;
    }

    public boolean remove(String name) {
        if (!isMap()) return false;
        ((Map<String, T>) container).remove(name);
        return true;
    }

    public boolean remove(int index) {
        if (!isList()) return false;
        ((List<T>) container).remove(index);
        return true;
    }

    public HashMap<String, T> map() {
        HashMap<String, T> tempMap = null;
        if (isMap()) {
            tempMap = new HashMap<>();
            ((Map<String, T>) container).forEach(tempMap::putIfAbsent);
        }
        return tempMap;
    }

    public List<T> list() {
        if (isList()) return new ArrayList<>(((List<T>) container));
        return null;
    }

    public int size() {
        if (isList()) return ((List<T>) container).size();
        if (isMap()) return ((Map<String, T>) container).size();
        return 0;
    }

    public boolean isList() {
        return container instanceof List;
    }

    public boolean isMap() {
        return container instanceof Map;
    }
}
