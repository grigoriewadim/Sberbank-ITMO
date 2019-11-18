package com.ifmo.lesson6;

import java.util.Iterator;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList implements List, Stack, Queue {
    /**
     * Ссылка на первый элемент списка.
     */
    private Item head;

    private class LinkedListIterator implements Iterator<Object> {
        Item item = head;
        @Override
        public boolean hasNext() {
            return item != null;
        }
        @Override
        public Object next() {
            Object value = item.value;
            item = item.next;
            return value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Object val) {
        // TODO implement.
        if (head == null) {
            head = new Item(val);
        } else {
            Item item = head;
            while (item.next != null) {
                item = item.next;
            }
            item.next = new Item(val);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object take() {
        // TODO implement.
        return remove(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(int i) {
        // TODO implement.
        Item item = find(i);
        return item != null ? item.value : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object remove(int i) {
        // TODO implement.
        Object value = null;
        if (i == 0) {
            if (head != null) {
                value = head.value;
                head = head.next;
            }
        } else if (i > 0) {
            Item item = find(i - 1);
            if (item != null) {
                if (item.next != null) {
                    value = item.next.value;
                    item.next = item.next.next;
                }
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator iterator() {
        // TODO implement.
        return new LinkedListIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(Object value) {
        // TODO implement.
        Item item = head;
        head = new Item(value);
        head.next = item;
    }

    @Override
    public Object pop() {
        return remove(0);
    }

    /**
     * {@inheritDoc}
     */
    public Object pop(int i) {
        // TODO implement.
        if (i == 0) {
            return head;
        } else if (i > 0) {
            Item item = head;
            for (int j = 0; j <= i; j++) {
                if (item == null) return null;
                if (j == i) return item;
                item = item.next;
            }
        }
        return null;
    }

    private Item find(int i) {
        if (i == 0) {
            return head;
        } else if (i > 0) {
            Item item = head;
            for (int j = 0; j <= i; j++) {
                if (item == null) return null;
                if (j == i) return item;
                item = item.next;
            }
        }
        return null;
    }
}