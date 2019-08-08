package com.batch.testspringbatch.Algorithm;

public class MyArray {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5};
        int[] afterArray = insertNumToArray(3, 2, array);
        for (int i : afterArray) {
            System.out.println(i);
        }
    }

    private static int[] insertNumToArray(int num, int index, int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int j = 0; j < array.length; j++) {
            if (j < index) {
                newArray[j] = array[j];
            } else {
                newArray[j+1] = array[j];
            }
        }
        newArray[index] = num;
        return newArray;
    }


}
