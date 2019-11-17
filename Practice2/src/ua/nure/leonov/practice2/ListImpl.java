package ua.nure.leonov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private int currentSize;
    private Node first;
    private Node last;

    public ListImpl() {
        // Prevent create default constr
    }

    @Override
    public void addFirst(Object element) {
        final Node f = first;
        final Node newNode = new Node(null, element, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        currentSize++;
    }

    @Override
    public void addLast(Object element) {
        final Node l = last;
        final Node newNode = new Node(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        currentSize++;
    }

    @Override
    public void removeFirst() {
        final Node f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }

        final Node next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        currentSize--;
    }

    @Override
    public void removeLast() {
        final Node l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        final Node prev = l.prev;
        l.item = null;
        l.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        currentSize--;
    }

    @Override
    public Object getFirst() {
        final Node f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    @Override
    public Object getLast() {
        final Node l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    @Override
    public Object search(Object element) {
        Node current = first;
        Object elem = null;
        while (current != null) {
            if (current.item.equals(element)) {
                elem = current.item;
            }
            current = current.next;
        }
        return elem;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node x) {
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        currentSize--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node current = first;
        if (currentSize != 0) {
            while (current != null) {
                sb.append(current.item).append(", ");
                current = current.next;
            }
            sb.deleteCharAt(sb.lastIndexOf(" "));
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public void clear() {
        for (Node x = first; x != null; x = x.next) {
            x.item = null;
            x.next = null;
            x.prev = null;
        }
        first = last = null;
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<Object> iterator() {
        return new ListImpl.IteratorImpl(0);
    }

    static class Node {
        private Object item;
        private Node next;
        private Node prev;

        public Node(Node prev, Object item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    Node node(int index) {
        if (index < (currentSize >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = last;
            for (int i = currentSize - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        IteratorImpl(int index) {
            next = (index == currentSize) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < currentSize;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }
    }
}
