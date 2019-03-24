package com.company;

import org.omg.CORBA.Any;

import java.lang.reflect.Array;
import java.util.Iterator;

public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFSULT_CAPACITY = 10;

    private int theSize;
    private AnyType[] theItems;

    /**
     * initialize
     */
    public MyArrayList(){
        doClear();
    }

    /**
     * clear up the array
     */
    public void clear(){
        doClear();
    }

    /**
     * the implementation of clear
     */
    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFSULT_CAPACITY);
    }

    /**
     * size
     * @return the size i don't know why not we use theSzie???
     */
    public int size(){
        return theSize;
    }

    /**
     * if it's empty
     * @return
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * get the item at the certain index
     * @param index
     * @return
     */
    public AnyType get(int index){
        if (index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    /**
     * set
     * @param index the index
     * @param newItem the item you wanna set
     * @return the old item in the array
     */
    public AnyType set(int index, AnyType newItem){
        if (index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldItem = theItems[index];
        theItems[index] = newItem;
        return oldItem;
    }

    /**
     * ensure if Capacity is enough
     * @param newCapacity the new capacity
     */
    public void ensureCapacity(int newCapacity){
        if (newCapacity < theSize){
            return;
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++){
            theItems[i] = old[i];
        }
    }

    /**
     * add item after the index element
     * @param index
     * @param item
     */
    public void add(int index, AnyType item){
        if (theItems.length == size()){
            ensureCapacity(2*size()+1);
            //expand the size
        }
        for (int i = theSize; i > index; i--){
            theItems[i] = theItems[i-1];
        }
        theItems[index] = item;
        theSize++;
    }

    /**
     * add an item at the tail
     * @param newItem
     * @return true
     */
    public boolean add(AnyType newItem){
        add(size(), newItem);
        return true;
    }

    /**
     * remove
     * @param index
     * @return the removed item
     */
    public AnyType remove(int index){
        AnyType removeItem = theItems[index];
        for (int i = index; i < size(); i++){
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return removeItem;
    }

    public java.util.Iterator<AnyType> iterator(){
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType>{
        private int current = 0;

        public boolean hasNext(){
            return current < size();
        }

        public AnyType next(){
            if (!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            return theItems[current++];
            //must be current++
        }

        public void remove(){
            MyArrayList.this.remove(--current);
        }
    }
}
