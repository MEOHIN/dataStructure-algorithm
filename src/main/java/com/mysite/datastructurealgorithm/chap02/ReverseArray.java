package com.mysite.datastructurealgorithm.chap02;

import java.util.Scanner;

public class ReverseArray {
    static void swap(int[] a, int indx1, int indx2) {
        int t = a[indx1];
        a[indx1] = a[indx2];
        a[indx2] = t;
    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length/2; i++) {
            swap(a, i, a.length-i-1);
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("요솟수: ");
        int num = stdIn.nextInt();

        int[] x = new int[num];

        for (int i = 0; i < num; i++) {
            System.out.println("x[" + i + "]: ");
            x[i] = stdIn.nextInt();
        }

        reverse(x);

        for (int i = 0; i < num; i++) {
            System.out.println("x[" + i + "] = " +x[i]);
        }
    }
}
