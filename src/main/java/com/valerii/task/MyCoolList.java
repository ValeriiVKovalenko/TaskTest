package com.valerii.task;


public interface MyCoolList<E extends Number> {
    void add(E o);

    int size();

    boolean isEmpty();


    E get(int index);

    boolean remove(E o);

    Object[] toArray();

    boolean contains(E o);
}
