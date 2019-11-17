package ua.nure.leonov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] arr;
    private static final int INITIAL_SIZE = 16;
    private int currentSize;

    ArrayImpl() {
        arr = new Object[INITIAL_SIZE];
    }

    @Override
    public void add(Object e) {
        if (currentSize != arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[currentSize++] = e;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(arr, 0, newArray, 0, currentSize);
        arr = newArray;
    }

    @Override
    public void set(int index, Object element) {
        arr[index] = element;
    }

    @Override
    public Object get(int index) {
        return arr[index];
    }

    @Override
    public int indexOf(Object element) {
        Object[] es = arr;
        if (element == null) {
            for (int i = 0; i < currentSize; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (element.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        arr[index] = null;
        System.arraycopy(arr, index + 1, arr, index, currentSize - 1);
        arr[--currentSize] = null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (currentSize != 0) {
            for (Object elem : arr) {
                if (elem != null) {
                    sb.append(elem).append(", ");
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(" "));
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return currentIndex != currentSize;
        }

        @Override
        public Object next() {
            int i = currentIndex;
            if (i >= currentSize){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayImpl.this.arr;
            currentIndex++;
            lastRet = i;
            return elementData[lastRet];
        }

        @Override
        public void remove() {
            if (lastRet < 0){
                throw new IllegalStateException();
            }
            ArrayImpl.this.remove(lastRet);
            currentIndex = lastRet;
            lastRet = -1;
        }
    }
}
