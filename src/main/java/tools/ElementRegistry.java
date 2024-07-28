package tools;

import java.util.HashMap;


public class ElementRegistry<T> {
    private final HashMap<String, T> eleMap = new HashMap<>();

    public class Entry {
        private String name;
        private T ele;

        private Entry(String name, T ele) {
            this.name = name;
            this.ele = ele;
        }
    }

    public ElementRegistry() {}

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

    public T get(String name) {
        return elements().get(name);
    }

    public HashMap<String, T> elements() {
        HashMap<String, T> tempMap = new HashMap<>();
        eleMap.forEach(tempMap::putIfAbsent);
        return tempMap;
    }
}
