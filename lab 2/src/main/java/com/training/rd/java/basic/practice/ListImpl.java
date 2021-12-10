package com.training.rd.java.basic.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node head;
    private int size = 0;

    private boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }



    private boolean equalsWith(Object element, Object data) {
        if(element == null) {
            return (data == null);
        } else {
            return (element.equals(data));
        }
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node it = head;

        @Override
        public boolean hasNext() {
            return it != null;
        }

        @Override
        public Object next() {
            if(hasNext()) {
                Object data = this.it.data;
                this.it = this.it.next;
                return data;
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public void addFirst(Object element) {
        head = new Node(element, head);
        size++;
    }

    @Override
    public void addLast(Object element) {
        if(head != null) {
            Node temp = head;
            while(temp.next != null) temp = temp.next;
            temp.next = new Node(element, null);
        } else {
            addFirst(element);
            return;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        head = head.next;
        size--;
    }

    @Override
    public void removeLast() {
        if(head == null || head.next == null) throw new NoSuchElementException();
        Node temp = head;
        while (temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
        size--;
    }

    @Override
    public Object getFirst() {
        if(isEmpty()) return null;
        return head.data;
    }

    @Override
    public Object getLast() {
        if(isEmpty()) return null;
        Node temp = head;
        while(temp.next != null) temp = temp.next;
        return temp.data;
    }

    @Override
    public Object search(Object element) {

        Node temp = head;
        while (temp != null) {
            if (equalsWith(element, temp.data)) return temp.data;
            temp = temp.next;
        }
        return null;

    }

    @Override
    public boolean remove(Object element) {

        Node temp = head;
        Node prev = null;
        boolean result = false;

        if (temp != null && equalsWith(element, temp.data)) {
            removeFirst();
            return true;
        }
        while(temp != null){
            if(equalsWith(element, temp.data)){
                assert prev != null;
                prev.next = temp.next;
                result = true;
                size--;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return result;

    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder("[");
        Node temp;

        for(temp = head; temp != null; temp = temp.next) {
            if(temp.next == null){
                res.append(temp.data);
            } else {
                res.append(temp.data).append(',').append(' ');
            }
        }

        res.append(']');
        return res.toString();

    }

    public static void main(String[] args) {

        List myList = new ListImpl();
        myList.addLast(null);
        myList.addLast('a');
        myList.addLast('c');
        myList.addLast('d');
        myList.addLast('e');
        System.out.println(myList.toString());
        Iterator it = myList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println(myList.remove('c'));
        System.out.println(myList.search('f'));
        System.out.println(myList.toString());
        System.out.println(myList.size());

    }
}
