package ua.nure.leonov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Object[] stack;
    private static final int SIZE = 50;
    private int currentSize;
    private int top;

    public StackImpl() {
        stack = new Object[SIZE];
        this.currentSize = 0;
        this.top = -1;
    }

    @Override
    public void push(Object element) {
        stack[++top] = element;
        currentSize++;
    }

    @Override
    public Object pop() {
        if (top < 0) {
            return null;
        }
        Object temp = stack[top];
        remove(top);
        return temp;
    }

    private void remove(int index) {
        stack[index] = null;
        System.arraycopy(stack, index + 1, stack, index, currentSize - 1);
        stack[--currentSize] = null;
        top--;
    }

    @Override
    public Object top() {
        return stack[top];
    }

    @Override
    public void clear() {
        for (int i = 0; i < stack.length; i++) {
            stack[i] = null;
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
            for (Object elem : stack) {
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
        private int cursor = currentSize - 1;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor < currentSize && cursor > -1;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (hasNext()) {
                cursor--;
                lastRet = i;
                return stack[lastRet];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            StackImpl.this.remove(lastRet);
            lastRet = -1;
        }
    }
}
