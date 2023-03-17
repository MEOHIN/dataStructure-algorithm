package com.mysite.datastructurealgorithm.chap09;

public class LinkedList<E> {
    class Node<E> {
        private E data;             // 데이터
        private Node<E> next;       // 뒤쪽 포인터(다음 노드 참조)

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;           // 머리 노드
    private Node<E> crnt;           // 선택 노드

    public LinkedList() {
        head = crnt = null;
    }
}
