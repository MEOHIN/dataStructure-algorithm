package com.mysite.datastructurealgorithm.chap02;

import java.util.Scanner;

public class PhysicalExamination {

    static final int VMAX = 21;

    static class PhysicData {
        String name;
        int height;
        double vision;

        PhysicData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }
    }

    static double averageHeight(PhysicData[] data) {
        double sum = 0;

        for (PhysicData datum : data) {
            sum += datum.height;
        }

        return sum / data.length;
    }

    static void distVision(PhysicData[] data, int[] dist) {
        int i = 0;
        dist[i] = 0;
        for (i = 0; i < data.length; i++) {
            if (data[i].vision >= 0.0 && data[i].vision <= VMAX / 10.0) {
                dist[(int) (data[i].vision * 10)] ++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        PhysicData[] x = {
                new PhysicData("박현규", 162, 0.3),
                new PhysicData("함진아", 172, 0.7),
                new PhysicData("최윤미", 175, 2.0),
                new PhysicData("홍연의", 171, 1.5),
                new PhysicData("이수진", 168, 0.4),
                new PhysicData("김영준", 174, 1.2),
                new PhysicData("박용규", 169, 0.8),
        };

        int[] vdist = new int[VMAX];

        System.out.println("=== 신체검사 리스트 ===");
        System.out.println("이름       키   시력");
        System.out.println("------------------");
        for (PhysicData physicData : x) {
            System.out.printf("%-8s%3d%5.1f\n", physicData.name, physicData.height, physicData.vision);
        }

        System.out.printf("\n평균 키: %5.1fcm\n", averageHeight(x));
        distVision(x, vdist);

        System.out.println("\n시력 분포");
        for (int i = 0; i < VMAX; i++) {
            System.out.printf("%3.1f~ :%2d명\n", i / 10.0, vdist[i]);
        }
    }
}
