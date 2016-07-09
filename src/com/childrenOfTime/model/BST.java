package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 05/28/2016.
 */
public class BST<E extends Comparable<E>> {
    private Node godFather;
    private int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        this.size = 0;
        godFather = null;
    }

    public boolean isEmpty() {
        return godFather == null;
    }


    public boolean contains(E var) {
        if (getNode(var, godFather) != null) return true;
        return false;
    }


    public Object getVar(E var) {
        Node returnedNode = getNode(var, godFather);
        if (returnedNode == null) return null;
        return returnedNode.getValue();
    }

    public Node<E> getNode(E var, Node<E> node) {
        if (node == null) return null;
        if (node.getValue().equals(var)) return node;
        else {
            return var.compareTo(node.getValue()) == 1 ? getNode(var, node.getRightSon()) : getNode(var, node.getLeftSon());
        }
    }

    public void add(E var) {
        if (godFather == null) godFather = new Node<E>(var, null, null, null);
        Node<E> dad = godFather;
        while (true) {
            if (var.compareTo(dad.getValue()) == 1) {
                if (dad.getRightSon() == null) {
                    dad.setRightSon(new Node(var, dad, null, null));
                    size++;
                    break;
                }
                dad = dad.getRightSon();
            } else if (var.compareTo(dad.getValue()) == -1) {
                if (dad.getLeftSon() == null) {
                    dad.setLeftSon(new Node(var, dad, null, null));
                    size++;
                    break;
                }
                dad = dad.getLeftSon();
            } else break;
        }
    }

    public E getMinElement() {
        Node<E> node = godFather;
        if (godFather == null) return null;
        while (node.getLeftSon() != null) {
            node = node.getLeftSon();
        }
        return node.getValue();
    }


    public E getMaxElement() {
        Node<E> node = godFather;
        if (godFather == null) return null;
        while (node.getRightSon() != null) {
            node = node.getRightSon();
        }
        return node.getValue();
    }


    public E getGodFatherElement() {
        Node<E> node = godFather;
        return node.getValue();
    }

/*
    public Node<E> addRecursive (E var , Node<E> node){
        if ( node.getValue() . equals(var) )  return node;
        else{
            return var.compareTo(node.getValue())==1  ?  addRecursive(var , node.getRightSon()):addRecursive(var, node.getLeftSon()) ;
        }
    }

*/

    public Node getGodFather() {
        return godFather;
    }
}


class Node<E> {
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
