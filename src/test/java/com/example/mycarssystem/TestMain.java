package com.example.mycarssystem;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        int[] arr = {2, 7, 12, 4, 5, 6, 1};
//        sortGui(arr, 0, arr.length - 1);
        sortGui(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区索引
            int pivotIndex = partition(arr, low, high);
            // 递归排序左子数组
            sort(arr, low,  - 1);
            // 递归排序右子数组
            sort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 选择最右边的元素作为基准
        int pivot = arr[high];

        // i 指针记录的是“小于基准区域”的右边界
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // 如果发现当前元素比基准小
            if (arr[j] < pivot) {
                i++;
                // 把这个小的换到前面去
                swap(arr, i, j);
            }
        }

        // 最后把基准元素换到 i+1 的位置
        // 这样 i+1 之前全是小的，i+1 之后全是大的
        swap(arr, i + 1, high);

        // 返回基准的最终位置
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sortGui(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int[] temp = new int[arr.length]; // 提前分配辅助空间，避免频繁创建
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 还是那句话，防止溢出

            // 递归“分”
            mergeSort(arr, left, mid, temp);      // 左半部分有序
            mergeSort(arr, mid + 1, right, temp); // 右半部分有序

            // 执行“治”（合并）
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;      // 左序列指针
        int j = mid + 1;   // 右序列指针
        int t = 0;         // 临时数组指针

        // 核心合并逻辑：两边比大小，谁小谁进 temp
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        // 把左边剩余的元素移入
        while (i <= mid) temp[t++] = arr[i++];
        // 把右边剩余的元素移入
        while (j <= right) temp[t++] = arr[j++];

        // 将 temp 中的内容拷贝回原数组
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
