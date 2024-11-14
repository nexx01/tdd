package com.example.tdd.algoritm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTwoNumbers {

    @Test
    void name() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{4, 5, 6};
        int[] exp = new int[]{ 5, 7, 9};

//        assertThat(Arrays.equals(exp, addTwo(arr1, arr2))).isTrue();
        assertThat(exp).isEqualTo(addTwo(arr1, arr2));
    }

    @Test
    void name2() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{9, 5, 6};
        int[] exp = new int[]{1, 0, 7, 9};


//        assertThat(Arrays.equals(exp, addTwo(arr1, arr2))).isTrue();
        assertThat(exp).isEqualTo(addTwo(arr1, arr2));
    }

    @Test
    void name3() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{4,9, 5, 6};
        int[] exp = new int[]{5, 0, 7, 9};

//        assertThat(Arrays.equals(exp, addTwo(arr1, arr2))).isTrue();
        assertThat(exp).isEqualTo(addTwo(arr1, arr2));
    }


    int[] addTwo(int[] arr1, int[] arr2) {

        int l = Math.max(arr1.length, arr2.length);
        if(arr1.length==arr2.length && (arr1[0] + arr2[0])>=10){
            l=l+1;
        }


        int[] mLA = arr1.length>=arr2.length?arr1 : arr2 ;
        int[] minLA = arr1.length<arr2.length?arr1 : arr2 ;

        int[] ress = new int[l];
        int inM=0;
        int iRes=ress.length-1;
        int iMin=minLA.length-1;
        int iMax=mLA.length-1;
        while(iMin>=0 && iMax>=0) {
            int setV=mLA[iMax]+minLA[iMin]+inM;
            if(setV>=10) {
                setV = setV%10;
                inM = 1;
            }
            ress[iRes] = setV;
            iMin--;iMax--;
            iRes--;
        }

        if(inM>=0 && iRes>=0 &&iMax<0){
            ress[iRes] = inM;
            inM = 0;
        } else if (iMax>=0) {
            while(iMax>=0){
                int setV = mLA[iMax] + inM;
                if(setV>=10) {
                    setV = setV%10;
                    inM = 1;
                }
                ress[iRes] = setV;
                iMax--; iRes--;
            }
        }


        return ress;
    }

}


