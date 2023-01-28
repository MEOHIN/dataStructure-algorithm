package com.mysite.datastructurealgorithm.chap02;

public class intArrayInit {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        int[] b = {1, 2, 3, 4, 5};

        for (int i = 0; i <a.length; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
        }

        for (int i = 0; i <a.length; i++) {
            System.out.println("b[" + i + "] = " + b[i]);
        }
    }
}
