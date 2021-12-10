package com.training.rd.java.basic.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Node front;
    private Node rear;
    private int size = 0;

    private boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
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

        private int count = 0;
        private Node it = front;

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public Object next() {
            if(hasNext()) {
                Object data = this.it.data;
                this.it = this.it.next;
                this.count++;
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public void enqueue(Object element) {
        if(isEmpty()) {
            front = rear = new Node(element, null);
        } else {
            rear.next = new Node(element, null);
            rear = rear.next;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()) return null;
        Node prev = front;
        front = front.next;
        size--;
        return prev.data;
    }

    @Override
    public Object top() {
        if(isEmpty()) return null;
        return front.data;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder("[");
        Node temp;

        for(temp = front; temp != null; temp = temp.next) {
            if(temp.next == null){
                res.append(temp.data);
                break;
            } else {
                res.append(temp.data).append(',').append(' ');
            }
        }

        res.append(']');

        return res.toString();
    }

    public static void main(String[] args) {
        Queue myQueue = new QueueImpl();
        myQueue.enqueue('a');
        myQueue.enqueue('b');
        myQueue.enqueue('c');
        myQueue.enqueue('d');
        System.out.println(myQueue.toString());

    }

}
