package com.training.rd.java.basic.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private Node top;
    private int size = 0;

    private boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node it = top;

        @Override
        public boolean hasNext() {
            return it != null;
        }

        @Override
        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Object data = this.it.data;
                this.it = this.it.next;
                return data;
            }
        }

    }

    @Override
    public void push(Object element) {
        top = new Node(element, top);
        size++;
    }

    @Override
    public Object pop() {
        if(isEmpty()) return null;
        Node temp = top;
        top = top.next;
        size--;
        return temp.data;

    }

    @Override
    public Object top() {
        if(isEmpty()) return null;
        return top.data;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder("[");
        Node temp = top;

        for(int i = 0; i <= size()-1; i++) {
            if(i == size() - 1){
                res.insert(1, temp.data);
            } else {
                res.insert(1, "," + " " + temp.data);
                temp = temp.next;
            }
        }

        res.append(']');

        return res.toString();
    }

    public static void main(String[] args) {
        Stack myStack = new StackImpl();
        myStack.push(null);
        myStack.push('b');
        myStack.push('c');
        myStack.push('d');
        myStack.push(null);
        System.out.println(myStack.top());
        System.out.println(myStack.toString());


    }

}
