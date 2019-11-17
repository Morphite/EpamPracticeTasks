package ua.nure.leonov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Object[] queue;
    private static final int MAX_SIZE = 50;
    private int currentSize;
    private int first;
    private int last;

    public QueueImpl() {
        queue = new Object[MAX_SIZE];
        this.currentSize = 0;
        this.first = 0;
        this.last = -1;
    }

    @Override
    public void enqueue(Object element) {
        queue[++last] = element;
        currentSize++;
    }

    @Override
    public Object dequeue() {
        Object temp = queue[first];
        remove(first);

        return temp;
    }

    private void remove(int index) {
        queue[index] = null;
        System.arraycopy(queue, index + 1, queue, index, currentSize - 1);
        queue[--currentSize] = null;
    }

    @Override
    public Object top() {
        return queue[first];
    }

    @Override
    public void clear() {
        queue = new Object[MAX_SIZE];
        first = 0;
        last = -1;
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
            for (Object elem : queue) {
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
        private int cursor;
        private int lastRet = -1;

        public IteratorImpl() {
            // Prevent create default constr
        }

        @Override
        public boolean hasNext() {
            return cursor < currentSize;
        }

        @Override
        public Object next() {
            if (cursor < currentSize) {
                lastRet = cursor;
                return queue[cursor++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            QueueImpl.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}
