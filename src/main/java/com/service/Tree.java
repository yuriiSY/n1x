package com.service;

import com.model.Product;
import com.model.comparator.ProductComporator;

public class Tree {

    private Node root;

    private final ProductComporator productComporator;

    public Tree(ProductComporator productComporator) {
        this.productComporator = productComporator;
    }

    public void add(Product product) {
        root = insert(root, product);
    }

    public int sumOfPricesLeftBranch() {
        if (root.leftBranch == null) {
            return 0;
        }
        return sum(root.leftBranch);
    }
    public int sumOfPricesRightBranch() {
        if (root.rightBranch == null) {
            return 0;
        }
        return sum(root.rightBranch);
    }

    private int sum(Node node) {
        if (node == null) {
            return 0;
        }
        return (int) (node.product.getPrice() + sum(node.leftBranch) + sum(node.rightBranch));
    }
    private Node insert(Node current, Product product) {
        if (current == null) {
            return new Node(product, null, null);
        }

        if (productComporator.compare(current.product, product) < 0) {
            current.leftBranch = insert(current.leftBranch, product);
            System.out.println("Inserted " + product + " to left of " + current.product);
        }
        else if (productComporator.compare(current.product, product) > 0) {
            current.rightBranch = insert(current.rightBranch, product);
            System.out.println("Inserted " + product + " to right of " + current.product);
        }
        return current;
    }

    private class Node {

        public Product product;
        public Node leftBranch;
        public Node rightBranch;
        public Node(Product product, Node leftBranch, Node rightBranch) {
            this.product = product;
            this.leftBranch = leftBranch;
            this.rightBranch = rightBranch;
        }
    }
}
