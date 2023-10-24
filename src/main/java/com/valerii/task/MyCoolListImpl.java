package com.valerii.task;

import java.util.*;

public class MyCoolListImpl<T extends Number> implements MyCoolList<T> {
    private static final int GROW_MULTIPLIER = 2;
    private T[] array;
    private int size;

    public MyCoolListImpl() {
        array = createArray(0);
    }

    /*
    @SuppressWarnings("unckeked") - подавление компилятора, при неконтролируемом кастинге
     */

    public MyCoolListImpl(int capacity) {
        array = createArray(capacity);
    }

    /**
     * Inner class используем так как мы обращаемся к полям Outer class.
     * Если использовали Nested class не было бы доступа к полям внешнего класса и методам. Поля и методы должны быть
     * статическими, так как они связаны с классом, а не объектом.
     */
    public class MyCoolListIterator implements Iterator<T> {
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex < size) {
                return array[currentIndex++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (currentIndex > 0 && currentIndex <= size) {
                int removeIndex = currentIndex - 1;
                System.arraycopy(array, currentIndex, array, removeIndex, size - currentIndex);
                array[size - 1] = null;
                size--;
                currentIndex--;
            } else {
                throw new IllegalStateException("No element to remove.");
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T o) {
        if (isFull()) {
            grow();
        }

        array[size++] = o;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < array.length) {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean remove(T o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(o)) {
                if (i < size - 1) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }
                size--;
                array[size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public boolean contains(T o) {
        for (T element : array) {
            if (element != null && element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (T t : array) {
            result.append(t).append(", ");
        }

        if (array.length > 0) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }

        MyCoolListImpl<?> otherObj = (MyCoolListImpl<?>) obj;
        return size == otherObj.size && Arrays.equals(array, otherObj.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    private boolean isFull() {
        return size == array.length;
    }

    private void grow() {
        T[] newArr = createArray(size == 0 ? 1 : array.length * GROW_MULTIPLIER);
        System.arraycopy(array, 0, newArr, 0, size);
        array = newArr;
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int length) {
        return (T[]) new Number[length];
    }
}