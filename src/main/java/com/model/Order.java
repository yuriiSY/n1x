package com.model;

import java.util.Date;

public class Order  {
    private Node first;
    private Node last;
    private int size = 0;

    public Product get(int version) {

        return getNode(version).value;
    }

    public String getDateOfVersion(int version) {

        return getNode(version).value +" was added "+ getNode(version).DATE;
    }

    public String getDateOfLastVersion() {
        return getNode(size-1).value +" was added "+ getNode(size-1).DATE;
    }

    public String getDateOFirstVersion() {
        return getNode(0).value +" was added "+ getNode(0).DATE;
    }

    public void add(Product product) {
        if(size == 0) {
            Node node = new Node(null,product, null);
            first = node;
            last = node;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, product , null);
            secondLast.next = last;
        }
        size++;
    }

    public void add(Product product,int version) {
        if(version < 0 || version > size) {
            throw new IndexOutOfBoundsException();
        }
        if (version == size) {
            add(product);
            return;
        }
        Node nodeNext = getNode(version);
        Node nodePrevious = nodeNext.previous;
        Node newNode = new Node(nodePrevious,product,nodeNext);
        nodeNext.previous = newNode;
        if(nodePrevious!=null) {
            nodePrevious.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    public boolean remove(Product product) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(product)) {
                return removeAt(i);
            }
        }
        return false;
    }

    public boolean removeAt(int version){
        Node node = getNode(version);
        Node nodeNext = node.next;
        Node nodePrevious = node.previous;
        if (nodeNext != null){
            nodeNext.previous = nodePrevious;
        }else {
            last = nodePrevious;
        }

        if(nodePrevious != null) {
            nodePrevious.next = nodeNext;
        }else {
            first = nodeNext;
        }
        size--;
        return true;
    }

    public int size(){
        return size;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node getNode(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node {
    private Node previous;
    private Product value;

    String DATE ;
    private Node next;
        public Node(Node previous, Product value, Node next) {
            DATE = new Date().toString();
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
