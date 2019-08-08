package com.batch.testspringbatch.Algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 100;
        int begin = 0;
        int end = arr.length - 1;
        int mid = (begin + end) / 2;
        int index = -1;
        while (true) {
            if (begin>=end) {
                break;
            }
            //判断中间元素是不是要找的元素
            if (arr[mid] == target) {
                index = mid;
                break;
            } else {
                //判断中间元素是不是比目标元素大
                if (arr[mid] > target) {
                    //把结束位置调整到中间位置前一个位置
                    end = mid - 1;
                } else {
                    //把结束位置调整到中间位置后一个位置
                    begin = mid + 1;
                }
                //取出新的中间位置
                mid = (begin + end) / 2;
            }
        }
        System.out.println(index);
    }
}
