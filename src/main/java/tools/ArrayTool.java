package tools;

import java.util.ArrayList;

public class ArrayTool<T> {
    public ArrayTool() {}

    public T lastElement(ArrayList<T> arr) {
        if (arr == null || arr.size() == 0) {
            return null;
        }
        return arr.get(arr.size() - 1);
    }

    public T lastElement(T[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }

        return arr[arr.length - 1];
    }
}
