package com.zhazhapan.util;

import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 */
public class ArraySort {

    /**
     * 堆排序
     *
     * @param arrays 数组
     */
    public static void heapSort(int[] arrays) {
        // 将待排序的序列构建成一个大顶堆
        for (int i = arrays.length / Values.TWO_INT; i >= 0; i--) {
            heapAdjust(arrays, i, arrays.length);
        }
        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int i = arrays.length - 1; i > 0; i--) {
            // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            switchNumber(arrays, 0, i);
            // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
            heapAdjust(arrays, 0, i);
        }
    }

    private static void heapAdjust(int[] arrays, int i, int n) {
        int child;
        int father;
        for (father = arrays[i]; Values.TWO_INT * i + 1 < n; i = child) {
            child = 2 * i + 1;
            // 如果左子树小于右子树，则需要比较右子树和父节点
            if (child != n - 1 && arrays[child] < arrays[child + 1]) {
                // 序号增1，指向右子树
                child++;
            }
            // 如果父节点小于孩子结点，则需要交换
            if (father < arrays[child]) {
                arrays[i] = arrays[child];
            } else {
                // 大顶堆结构未被破坏，不需要调整
                break;
            }
        }
        arrays[i] = father;
    }

    /**
     * 归并排序
     *
     * @param arrays 数组
     */
    public static void mergeSort(int[] arrays) {
        mergeSort(arrays, 0, arrays.length - 1);
    }

    private static void mergeSort(int[] arrays, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(arrays, low, mid);
            mergeSort(arrays, mid + 1, high);
            mergeSort(arrays, low, mid, high);
        }
    }

    private static void mergeSort(int[] arrays, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (arrays[i] < arrays[j]) {
                temp[k++] = arrays[i++];
            } else {
                temp[k++] = arrays[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arrays[i++];
        }
        while (j <= high) {
            temp[k++] = arrays[j++];
        }
        for (int k2 = 0; k2 < temp.length; k2++) {
            arrays[k2 + low] = temp[k2];
        }
    }

    /**
     * 希尔排序
     *
     * @param arrays 数组
     */
    public static void shellSort(int[] arrays) {
        int h = 1;
        while (h <= arrays.length / Values.THREE_INT) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < arrays.length; i += h) {
                if (arrays[i] < arrays[i - h]) {
                    int tmp = arrays[i];
                    int j = i - h;
                    while (j >= 0 && arrays[j] > tmp) {
                        arrays[j + h] = arrays[j];
                        j -= h;
                    }
                    arrays[j + h] = tmp;
                }
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 选择排序
     *
     * @param arrays 数组
     */
    public static void selectSort(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            int lowIndex = i;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[lowIndex]) {
                    lowIndex = j;
                }
            }
            if (lowIndex != i) {
                switchNumber(arrays, i, lowIndex);
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arrays 数组
     */
    public static void quickSort(int[] arrays) {
        quickSort(arrays, 0, arrays.length - 1);
    }

    private static void quickSort(int[] arrays, int start, int end) {
        int i = start;
        int j = end;
        if (start >= end) {
            return;
        }
        if ((end - start) == 1) {
            if (arrays[start] > arrays[end]) {
                switchNumber(arrays, start, end);
            }
        }
        while (i < j) {
            while (i < j && arrays[j] >= arrays[i]) {
                j--;
            }
            if (i < j) {
                switchNumber(arrays, i, j);
            }
            while (i < j && arrays[i] < arrays[j]) {
                i++;
            }
            if (i < j) {
                switchNumber(arrays, i, j);
            }
            if (i - start > 1) {
                quickSort(arrays, start, i - 1);
            }
            if (end - i > 1) {
                quickSort(arrays, i + 1, end);
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arrays 数组
     */
    public static void insertSort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int currentNumber = arrays[i];
            int j = i - 1;
            while (j >= 0 && arrays[j] > currentNumber) {
                arrays[j + 1] = arrays[j--];
            }
            arrays[j + 1] = currentNumber;
        }
    }

    /**
     * 冒泡排序
     *
     * @param arrays 数组
     */
    public static void bubbleSort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = 0; j < arrays.length - 1 - i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    switchNumber(arrays, j, j + 1);
                }
            }
        }
    }

    private static void switchNumber(int[] arrays, int i, int j) {
        int temp = arrays[j];
        arrays[j] = arrays[i];
        arrays[i] = temp;
    }
}
