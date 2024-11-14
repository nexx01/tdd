package com.example.tdd.algoritm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomTest {

    @Test
    void name() {
        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        Assertions.assertThat(check(intervals)).isEqualTo(false);

        int[][] intervals2 = new int[][]{{7, 10}, {2, 5}};
        Assertions.assertThat(check(intervals2)).isEqualTo(true);


    }

    boolean check(int[][] intervals){

        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));
        int[] prev = intervals[0];
        for(int i=1;i<intervals.length;i++){
            if(isOver(prev,intervals[i])){
                return false;
            }
        }

        return true;
    }

    boolean isOver(int[] ar1,int[] ar2) {
        return ar1[1] > ar2[0];
    }


}
