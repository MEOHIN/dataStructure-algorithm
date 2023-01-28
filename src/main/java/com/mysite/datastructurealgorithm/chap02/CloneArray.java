package com.mysite.datastructurealgorithm.chap02;

public class CloneArray {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = a.clone();

        b[3] = 0;

        System.out.println("a = ");
        for (int j : a) {
            System.out.println(" " + j);
        }

        System.out.println("b = ");
        for (int j : b) {
            System.out.println(" " + j);
        }
    }
}
