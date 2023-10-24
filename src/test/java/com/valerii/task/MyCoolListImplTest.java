package com.valerii.task;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


class MyCoolListImplTest {
    private MyCoolList<Integer> myCoolList = new MyCoolListImpl<>();

    @Test
    void returnListSize_WhenOk() {
        //before
        myCoolList.add(1);
        myCoolList.add(2);

        //when
        int actual = myCoolList.size();

        //then
        Assertions.assertEquals(2, actual);
    }

    @Test
    void returnListSize_whenSizeIncorrect() {
        //before
        myCoolList.add(1);

        //when
        int actual = myCoolList.size();

        //then
        Assertions.assertNotEquals(2, actual);
    }

    @Test
    void returnTrue_whenEmptyList() {

        //when
        boolean actual = myCoolList.isEmpty();

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void returnFalse_whenEmptyList() {
        //given
        myCoolList.add(1);

        //when
        boolean actual = myCoolList.isEmpty();

        //then
        Assertions.assertFalse(actual);
    }


    @Test
    void addElementWhenListHasPlace() {
        //given
        myCoolList = new MyCoolListImpl<>(5);

        //when
        myCoolList.add(1);
        myCoolList.add(2);
        myCoolList.add(3);
        int actual = myCoolList.size();

        //then
        Assertions.assertEquals(3, actual);
        Assertions.assertEquals(1, myCoolList.get(0));
        Assertions.assertEquals(2, myCoolList.get(1));
        Assertions.assertEquals(3, myCoolList.get(2));
    }

    @Test
    void addElementWhenListGrow() {
        //given
        myCoolList = new MyCoolListImpl<>(3);
        myCoolList.add(1);
        myCoolList.add(2);
        myCoolList.add(3);
        int expected = myCoolList.size();

        //when
        myCoolList.add(4);
        int actual = myCoolList.size();

        //then
        Assertions.assertEquals(expected + 1, actual);
    }

    @Test
    void getCorrectElement() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);
        myCoolList.add(3);

        //when
        Integer actual = myCoolList.get(2);

        //then
        Assertions.assertEquals(3, actual);
    }

    @Test
    void throwExceptionIndexOutOfBand_whenGetValue() {
        //given
        myCoolList = new MyCoolListImpl<>(3);
        myCoolList.add(1);
        myCoolList.add(2);
        myCoolList.add(3);

        //then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myCoolList.get(3));
    }

    @Test
    void removeElement_ok() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);

        //when
        boolean actual = myCoolList.remove(1);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void canNotRemoveElementInList() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);

        //when
        boolean actual = myCoolList.remove(3);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void returnArrayWithElements() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);
        myCoolList.add(3);

        //when
        Object[] actual = myCoolList.toArray();

        //then
        Assertions.assertEquals(1, actual[0]);
        Assertions.assertEquals(2, actual[1]);
        Assertions.assertEquals(3, actual[2]);
    }

    @Test
    void listContainElement() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);

        //when
        boolean actual = myCoolList.contains(2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void listDoseNotContainElement() {
        //given
        myCoolList.add(1);
        myCoolList.add(2);

        //when
        boolean actual = myCoolList.contains(3);

        //then
        Assertions.assertFalse(actual);
    }

    @Nested
    class MyCoolListIteratorTest {

        @Test
        void hasElementInList() {
            //given
            MyCoolListImpl<Integer> list = new MyCoolListImpl<>();
            list.add(1);
            list.add(2);
            list.add(3);
            MyCoolListImpl<Integer>.MyCoolListIterator iterator = list.new MyCoolListIterator();

            //when
            boolean actual = iterator.hasNext();

            //then
            Assertions.assertTrue(actual);
        }

        @Test
        void existElementInList() {
            //given
            MyCoolListImpl<Integer> list = new MyCoolListImpl<>();
            list.add(1);
            list.add(2);
            list.add(3);
            MyCoolListImpl<Integer>.MyCoolListIterator iterator = list.new MyCoolListIterator();

            //when
            Integer actual = iterator.next();

            //then
            Assertions.assertEquals(1, actual);
        }

        @Test
        void throwExceptionWhenElementDoesNotExist() {
            //given
            MyCoolListImpl<Integer> list = new MyCoolListImpl<>();
            MyCoolListImpl<Integer>.MyCoolListIterator iterator = list.new MyCoolListIterator();

            //when
            Assertions.assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void removeElementFromList_ok() {
            //given
            MyCoolListImpl<Integer> list = new MyCoolListImpl<>();
            list.add(1);
            list.add(2);
            list.add(3);

            MyCoolListImpl<Integer>.MyCoolListIterator iterator = list.new MyCoolListIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            //when
            iterator.remove();
            int actual = list.size();

            //then
            Assertions.assertEquals(2, actual);
            Assertions.assertEquals(1, list.get(0));
            Assertions.assertEquals(2, list.get(1));
        }

        @Test
        void removeElementFromList_whenThrowException() {
            //given
            MyCoolListImpl<Integer> list = new MyCoolListImpl<>();

            MyCoolListImpl<Integer>.MyCoolListIterator iterator = list.new MyCoolListIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            //when
            Assertions.assertThrows(IllegalStateException.class, iterator::remove);
        }
    }

}