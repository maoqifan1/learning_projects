package com.mao.myCollection;


import java.util.ConcurrentModificationException;
import java.util.Iterator;

@SuppressWarnings("all")
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.add("第一个节点");
        myLinkedList.add("第二个节点");
        myLinkedList.add("第三个节点");

        Iterator iterator = myLinkedList.iterator();

        while (iterator.hasNext()) {
            String data = (String) iterator.next();
            System.out.println(data);
        }

    }

    private int theSize;
    private int modCount;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> previous;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.previous = prev;
            this.next = next;
        }
    }

    public void clear() {
        doClear();
    }

    public MyLinkedList() {
        doClear();
    }

    public int size() {
        return this.theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void doClear() {
        this.beginMarker = new Node<>(null, null, null);
        this.endMarker = new Node<>(null, beginMarker, null);

        this.beginMarker.next = endMarker;
        this.theSize = 0;
        this.modCount++;
    }

    public Boolean addFirst() {
        return null;
    }

    public Boolean addLast() {
        return null;
    }

    public AnyType removeFirst() {
        return null;
    }

    public AnyType removeLast() {
        return null;
    }

    public AnyType getFirst() {
        return null;
    }

    public AnyType getLast() {
        return null;
    }

    public Boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> oldNode = getNode(idx);
        AnyType oldVal = oldNode.data;
        oldNode.data = newVal;

        return oldVal;
    }

    private Node<AnyType> getNode(int idx, int lowest, int highest) {
        Node<AnyType> node;

        if (idx < lowest || idx > highest)
            throw new IndexOutOfBoundsException();

        if (idx < size() / 2) {
            node = this.beginMarker.next;
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
        } else {
            node = this.endMarker;
            for (int i = size(); i > idx; i--) {
                node = node.previous;
            }
        }
        return node;

    }

    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private void addBefore(Node<AnyType> prevNode, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, prevNode.previous, prevNode);
        newNode.previous.next = newNode;
        prevNode.previous = newNode;
        this.theSize++;
        this.modCount++;
    }


    private AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    private AnyType remove(Node<AnyType> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;

        this.theSize--;
        this.modCount++;

        return node.data;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkListIterator();
    }

    private class LinkListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private Boolean okToRemove = false;

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.previous);
            expectedModCount++;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new IllegalStateException();

            AnyType element = current.data;
            current = current.next;
            okToRemove = true;
            return element;
        }
    }
}
