package com.example.tdd.algoritm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoom2Test {

    @Test
    void name() {
        int[][] interval = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        Assertions.assertThat(countRoomWithPoints(interval)).isEqualTo(2);
        Assertions.assertThat(countRoom(interval)).isEqualTo(2);
        int[][] interval2 = new int[][]{{1, 18}, {18, 23}, {15, 29}, {4, 15}, {2, 11}, {5, 13}};
        Assertions.assertThat(countRoom(interval2)).isEqualTo(4);
        Assertions.assertThat(countRoomWithPoints(interval2)).isEqualTo(4);

    }

    int countRoomWithPoints(int[][]intervals){
        int[][] points =new int[intervals.length*2][2];

        int p = 0;
        for (int[] i :intervals){
            points[p]= new int[]{i[0],1};
            p=p+1;
            points[p]= new int[]{i[1],-1};
            p=p+1;
        }

        System.out.println(Arrays.deepToString(points));
        Arrays.sort(points, Comparator.comparing(i -> i[0]));

        int maxRoom = 0;
        int currentRoom = 0;

        for (int i = 0; i < points.length; i++) {
            currentRoom=currentRoom+points[i][1];
            maxRoom = Math.max(maxRoom, currentRoom);
        }

        return maxRoom;
    }

    int countRoom(int[][] interval2) {
        Arrays.sort(interval2, Comparator.comparing(i->i[0]));
        int count = 1;
        int currentCount = 1;
        int[] prev = interval2[0];
        for (int i = 1; i < interval2.length; i++) {
            if(isOverlap(prev, interval2[i]) && currentCount>=count) {
                count++;
                prev = interval2[i];  //todo need sort
                currentCount++;
            } else{
                if (currentCount > 1) {
                    currentCount--;
                }
            }
        }

        return count;
    }

    boolean isOverlap(int[] i1, int[] i2) {
        return i1[1]>i2[0];
    }

    //  12345   1 and 5
    //    3456  3 and


    /*  2  2
      12345    1 1
        34567  2 2
               89.10.11  2 1
                  10.11.13  2 2
                             14.15 2 1
                                     16 17 2 1
                                             18.19 2

     */
}
