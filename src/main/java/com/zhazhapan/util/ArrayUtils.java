package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pantao
 */
public class ArrayUtils {

    private ArrayUtils() {}

    /**
     * 合并多个Short数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static short[] concatArrays(short[] array, short[]... arrays) {
        short[] res = array;
        for (short[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Long数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static long[] concatArrays(long[] array, long[]... arrays) {
        long[] res = array;
        for (long[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Float数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static float[] concatArrays(float[] array, float[]... arrays) {
        float[] res = array;
        for (float[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Double数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static double[] concatArrays(double[] array, double[]... arrays) {
        double[] res = array;
        for (double[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Character数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static char[] concatArrays(char[] array, char[]... arrays) {
        char[] res = array;
        for (char[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Byte数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static byte[] concatArrays(byte[] array, byte[]... arrays) {
        byte[] res = array;
        for (byte[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Boolean数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static boolean[] concatArrays(boolean[] array, boolean[]... arrays) {
        boolean[] res = array;
        for (boolean[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个String数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static String[] concatArrays(String[] array, String[]... arrays) {
        String[] res = array;
        for (String[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Integer数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    public static int[] concatArrays(int[] array, int[]... arrays) {
        int[] res = array;
        for (int[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个T数组
     *
     * @param array 数组
     * @param arrays 数组
     * @param <T> T类型
     *
     * @return 数组
     */
    @SafeVarargs
    public static <T> T[] concatArrays(T[] array, T[]... arrays) {
        T[] res = array;
        for (T[] ele : arrays) {
            res = org.apache.commons.lang3.ArrayUtils.addAll(res, ele);
        }
        return res;
    }


    /**
     * 合并两个升序数组
     *
     * @param nums1 数组
     * @param nums2 数组
     *
     * @return 数组
     */
    public static int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        return mergeSortedArrays(nums1, nums2, false);
    }

    /**
     * 将两个有序数组（同序）合并成一个有序数组
     *
     * @param nums1 数组
     * @param nums2 数组
     * @param desc 是否为降序
     *
     * @return 数组
     */
    public static int[] mergeSortedArrays(int[] nums1, int[] nums2, boolean desc) {
        int len = nums1.length + nums2.length;
        int[] nums = new int[len];
        int m = 0;
        int n = 0;
        for (int i = 0; i < len; i++) {
            boolean inNums1 = n == nums2.length || (m != nums1.length && ((nums1[m] < nums2[n]) ^ desc));
            if (inNums1) {
                nums[i] = nums1[m++];
            } else {
                nums[i] = nums2[n++];
            }
        }
        return nums;
    }

    /**
     * 去重
     *
     * @param arrays 数组
     *
     * @return 去重后的数组
     */
    public static String[] unique(String[] arrays) {
        List<String> list = new ArrayList<>(arrays.length);
        for (String arr : arrays) {
            if (!list.contains(arr)) {
                list.add(arr);
            }
        }
        String[] res = new String[list.size()];
        int i = 0;
        for (String arr : list) {
            res[i++] = arr;
        }
        return res;
    }

    /**
     * 去重
     *
     * @param arrays 数组
     *
     * @return 去重后的数组
     */
    public static int[] unique(int[] arrays) {
        return unique(arrays, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 去重
     *
     * @param arrays 数组
     * @param min 最小值（小于该值的将被忽略）
     * @param max 最大值（大于该值的将被忽略）
     *
     * @return 去重后的数组
     */
    public static int[] unique(int[] arrays, int min, int max) {
        List<Integer> list = new ArrayList<>(arrays.length);
        for (int arr : arrays) {
            if (!list.contains(arr) && arr >= min && arr <= max) {
                list.add(arr);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int arr : list) {
            res[i++] = arr;
        }
        return res;
    }

    /**
     * 去重
     *
     * @param arrays 数组
     *
     * @return 去重后的数组
     */
    public static long[] unique(long[] arrays) {
        return unique(arrays, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 去重
     *
     * @param arrays 数组
     * @param min 最小值（小于该值的将被忽略）
     * @param max 最大值（大于该值的将被忽略）
     *
     * @return 去重后的数组
     */
    public static long[] unique(long[] arrays, long min, long max) {
        List<Long> list = new ArrayList<>(arrays.length);
        for (long arr : arrays) {
            if (!list.contains(arr) && arr >= min && arr <= max) {
                list.add(arr);
            }
        }
        long[] res = new long[list.size()];
        int i = 0;
        for (long arr : list) {
            res[i++] = arr;
        }
        return res;
    }

    /**
     * 堆排序
     *
     * @param arrays 数组
     */
    public static void heapSort(int[] arrays) {
        // 将待排序的序列构建成一个大顶堆
        for (int i = arrays.length / ValueConsts.TWO_INT; i >= 0; i--) {
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
        for (father = arrays[i]; ValueConsts.TWO_INT * i + 1 < n; i = child) {
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
        System.arraycopy(temp, ValueConsts.ZERO_INT, arrays, low, temp.length);
    }

    /**
     * 希尔排序
     *
     * @param arrays 数组
     */
    public static void shellSort(int[] arrays) {
        int h = 1;
        while (h <= arrays.length / ValueConsts.THREE_INT) {
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
