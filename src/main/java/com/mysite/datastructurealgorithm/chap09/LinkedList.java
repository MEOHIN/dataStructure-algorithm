package com.mysite.datastructurealgorithm.chap09;

import java.util.Comparator;

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

    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head;                         // 현재 스캔 중인 노드

        while (ptr != null) {
            if (c.compare(obj, ptr.data) == 0) {    // 검색 성공
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;                         // 다음 노드를 선택
        }
        return null;                                // 검색 실패
    }

    public void addFirst(E obj) {
        Node<E> ptr = head;                 //삽입 전의 머리 노드
        head = crnt = new Node<E>(obj, ptr);
    }

    public void addLast(E obj) {
        if (head == null) {                 // 리스트가 비어 있으면
            addFirst(obj);                  // 머리에 삽입
        } else {
            Node<E> prt = head;
            while (prt.next != null) {
                prt = prt.next;
            }
            prt.next = crnt = new Node<E>(obj, null);
        }
    }
}
