package ru.aston.lepd.customhashmap;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class CustomLinkedListTest {

    private final CustomLinkedList<String> list = new CustomLinkedList<>();


    @Test
    public void add() {
        final int expectedResult = 3;

        list.add("one");
        list.add("two");
        list.add("three");

        assertEquals(expectedResult, list.size());
    }


    @Test
    public void remove_whenValidData_thenSuccess() {
        final int expectedResult = 2;
        list.add("one");
        list.add("two");
        list.add("three");

        list.remove("two");

        assertEquals(expectedResult, list.size());
    }

    @Test
    public void remove_whenInvalidData_thenNothing() {
        final int expectedResult = 3;
        list.add("one");
        list.add("two");
        list.add("three");

        list.remove("dummy");

        assertEquals(expectedResult, list.size());
    }



    @Test
    public void iterator_whenHasNextElement_thenTrue() {
        list.add("one");
        list.add("two");
        list.add("three");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    public void iterator_whenDoesNotHaveNextElement_thenFalse() {
        list.add("one");

        Iterator<String> iterator = list.iterator();
        iterator.next();

        assertFalse(iterator.hasNext());
    }


    @Test
    public void iterator_whenExistNextElement_thenReturnElement() {
        final String expectedResult = "one";
        list.add("one");
        list.add("two");
        list.add("three");

        Iterator<String> iterator = list.iterator();

        assertEquals(expectedResult, iterator.next());
    }

    @Test
    public void iterator_whenNotExistNextElement_thenThrowException() {
        list.add("one");

        Iterator<String> iterator = list.iterator();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }



    @Test
    public void size_whenExistElements_thenReturnSize() {
        final int expectedResult = 3;
        list.add("one");
        list.add("two");
        list.add("three");

        int actualResult = list.size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void size_whenNotExistElements_thenReturnZero() {
        final int expectedResult = 0;

        int actualResult = list.size();

        assertEquals(expectedResult, actualResult);
    }


}
