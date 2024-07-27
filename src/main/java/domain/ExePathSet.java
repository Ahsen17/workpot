package domain;

import java.util.ArrayList;
import java.util.Properties;

public class ExePathSet {
    private int length;
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> paths = new ArrayList<>();

    public ExePathSet() throws Exception {
        init();
    }

    private void init() throws Exception {

    }

    public int length() {
        return length;
    }

    public String name(int index) {
        return names.get(index);
    }

    public ArrayList<String> names() {
        return names;
    }

    public String path(int index) {
        return paths.get(index);
    }

    public ArrayList<String> paths() {
        return paths;
    }
}
