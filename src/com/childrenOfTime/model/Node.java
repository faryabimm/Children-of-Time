package com.childrenOfTime.model;

import java.io.Serializable;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class Node<E> implements Serializable {
    private E value;
    private Node dad;
    private Node rightSon;
    private Node leftSon;

    public Node(E value, Node dad, Node rightSon, Node leftSon) {
        this.value = value;
        this.dad = dad;
        this.rightSon = rightSon;
        this.leftSon = leftSon;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node getDad() {
        return dad;
    }

    public void setDad(Node dad) {
        this.dad = dad;
    }

    public Node getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }
}
