package com.ley.springboot.commons.base;

import org.junit.Test;

public class Algorithm {

    private int binarySearchWithoutRecursion(int a[], int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > key)
                high = mid - 1;
            else if (a[mid] < key)
                low = mid + 1;
            else
                //找到直接返回
                return mid;
        }
        return -1;
    }

    private int binarySearch(int array[], int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (array[mid] > target)
            return binarySearch(array, low, mid - 1, target);
        if (array[mid] < target)
            return binarySearch(array, mid + 1, high, target);
        return mid;
    }


    @Test
    public void test1() {
        int[] numbers = {1, 2, 3, 4, 5};
        int target = 2;
        System.out.println(binarySearchWithoutRecursion(numbers, target));
        System.out.println(binarySearch(numbers, 0, 4, 2));
    }
}
