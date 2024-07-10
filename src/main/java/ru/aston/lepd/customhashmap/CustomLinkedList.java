package ru.aston.lepd.customhashmap;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * CustomLinkedList represents a singly linked list implementation.
 *
 * <p>This class provides basic operations such as adding elements at the end,
 * removing elements and iterating through the elements.</p>
 *
 * <p>The list maintains references only to the next node in the sequence, making it efficient
 * for sequential traversal but less efficient for random access operations.</p>
 *
 * @param <T> the type of elements stored in the list
 */
public class CustomLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method creates a new node containing the specified element and appends it
     * to the tail of the list. If the list is empty, the new element becomes the head
     * of the list.</p>
     *
     * @param data the element to be appended to this list
     */
    public void add(T data) {
        Node<T> node = new Node<>(data, null);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }


    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * <p>This method iterates through the list to find the first node containing the specified
     * element and removes it.</p>
     * @param data the element to be removed from this list, if present
     */
    public void remove(T data) {
        if (first == null) return;
        if (first.data.equals(data)) {
            first = first.next;
            size--;
            return;
        }
        Node<T> current = first;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next == null) return;
        current.next = current.next.next;
        size--;
    }


    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>This method returns an iterator that allows sequential access to each element
     * in the list.</p>
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) throw new NoSuchElementException();
                Node<T> temp = current;
                current = current.next;
                return temp.data;
            }
        };
    }

    public int size() {
        return size;
    }



}
