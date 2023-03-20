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
            Node<E> ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = crnt = new Node<E>(obj, null);
        }
    }

    public void removeFirst() {
        if (head != null) {             // 리스트가 비어 있지 않으면
            head = crnt = head.next;
        }
    }


    public void removeLast() {
        if (head != null) {
            if (head.next == null) {    // 노드가 하나만 있으면
                removeFirst();          // 머리 노드를 삭제
            } else {
                Node<E> ptr = head;     // 스캔 중인 노드
                Node<E> pre = head;     // 스캔 중이 노드의 앞 노드

                while (ptr.next != null) {
                    pre = ptr;
                    ptr = ptr.next;
                }
                pre.next = null;        // pre는 삭제 후의 꼬리 노드
                crnt = pre;
            }
        }
    }

    public void remove(Node p) {
        if (head != null) {
            if (p == head) {            // p가 머리 노드면
                removeFirst();          // 머리 노드를 삭제
            } else {
                Node<E> ptr = head;

                while (ptr.next != p) {
                    ptr = ptr.next;
                    if (ptr == null) {
                        return;         // p가 리스트에 없습니다.
                    }
                }
                ptr.next = p.next;
                crnt = ptr;
            }
        }
    }

    public void removeCurrentNode() {
        remove(crnt);
    }

    public void clear() {
        while (head != null) {      // 노드에 아무것도 없을때까지
            removeFirst();          // 머리 노드를 삭제
        }
        crnt = null;
    }

    public boolean next() {
        if (crnt == null || crnt.next == null) {
            return false;           // 이동할 수 없음
        }
        crnt = crnt.next;
        return true;
    }
}
