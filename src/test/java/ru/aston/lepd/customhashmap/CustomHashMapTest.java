package ru.aston.lepd.customhashmap;

import org.junit.Test;

import static org.junit.Assert.*;


public class CustomHashMapTest {

    private final CustomHashMap<Integer, String> map = new CustomHashMap<>();


    @Test
    public void put_whenValidData_thenSuccess() {
        final int expectedSize = 3;
        final String expectedValue = "two";

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        assertEquals(expectedSize, map.size());
        assertEquals(expectedValue, map.get(2));
    }

    @Test
    public void put_whenKeyIsNull_thenThrowException() {
        assertThrows(NullPointerException.class, () -> map.put(null, "dummy"));
    }



    @Test
    public void get_whenValidKey_thenReturnValue() {
        final String expectedResult = "two";
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        String actualResult = map.get(2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void get_whenInvalidKey_thenNull() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        assertNull(map.get(99));
    }

    @Test
    public void get_whenKeyIsNull_thenThrowException() {
        assertThrows(NullPointerException.class, () -> map.get(null));
    }



    @Test
    public void remove_whenValidKey_thenSuccess() {
        final int expectedResult = 2;
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        map.remove(2);

        assertEquals(expectedResult, map.size());
        assertNull(map.get(2));
    }

    @Test
    public void remove_whenInvalidKey_thenNothing() {
        final int expectedResult = 3;
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        map.remove(99);

        assertEquals(expectedResult, map.size());
    }

    @Test
    public void remove_whenKeyIsNull_thenThrowException() {
        assertThrows(NullPointerException.class, () -> map.remove(null));
    }


    @Test
    public void size_whenExistElements_thenReturnSize() {
        final int expectedResult = 3;
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        int actualResult = map.size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void size_whenNotExistElements_thenReturnZero() {
        final int expectedResult = 0;

        int actualResult = map.size();

        assertEquals(expectedResult, actualResult);
    }


}
