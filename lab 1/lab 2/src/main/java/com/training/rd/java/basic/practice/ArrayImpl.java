package com.training.rd.java.basic.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayImpl implements Array {

    private int size = 0;
    private int capacity;
    private Object[] arr;

    private void boundsStretching() {

        Object[] temp = new Object[++capacity];
        System.arraycopy(arr, 0, temp, 0, size+1);
        arr = temp;

    }

    public ArrayImpl(int capacity){
        this.capacity = capacity;
        arr = new Object[capacity];
    }

	@Override
    public void clear() {
        for(int i = 0; i < size(); i++){
            arr[i] = null;
        }
        size = 0;
    }

	@Override
    public int size() { return size; }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public Object next() {
            if(!hasNext()) throw new NoSuchElementException();
            return arr[count++];
        }

    }
	
	@Override
    public void add(Object element) {

        if(size == capacity-1) boundsStretching();
        arr[size++] = element;

    }

	@Override
    public void set(int index, Object element) {
        if(index >= size()) throw new NoSuchElementException();
        arr[index] = element;
    }

	@Override
    public Object get(int index) {
        if(index >= size()) throw new NoSuchElementException();
        return arr[index];
    }

	@Override
    public int indexOf(Object element) {

        int index = -1;

        for(int i = 0; i <= size()-1; i++) {
            if(element.equals(arr[i])) {
                index = i;
                break;
            }
        }

        return index;

    }

	@Override
    public void remove(int index) {
        System.arraycopy(arr, index + 1, arr, index, size() - 1 - index);
        size--;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder("[");

        for(int i = 0; i <= size() - 1; i++) {
            if(i == size() - 1) {
                res.append(arr[i]);
            } else {
                res.append(arr[i]).append(',').append(' ');
            }
        }

        res.append(']');

        return res.toString();
    }

    public static void main(String[] args) {
        Array myArr = new ArrayImpl(1);
        myArr.add('a');
        myArr.add('b');
        myArr.add('c');
        myArr.add('d');
        Iterator it = myArr.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
        }
        System.out.println("\n" + myArr.toString());
        System.out.println(myArr.size());
        myArr.set(2, 'd');
        System.out.println(myArr.toString());
        System.out.println(myArr.indexOf('b'));
        System.out.println(myArr.indexOf('c'));
        System.out.println(myArr.get(2));
        myArr.remove(2);
        System.out.println(myArr.toString());
    }

}
