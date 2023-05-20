package com.mao.lessons.class2;

public class SmallSum {
    public static void mergeSort(int[] arr) {
        // 边界条件
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    public static int process(int[] arr, int l, int r) {
        // 边界条件
        if (l == r) {
            return 0;
        }
        //  找中点
        int mid = l + ((r - l) >> 1);
        // 处理中点左边的数组
        return process(arr, l, mid) +
                // 处理中点右边的数组
                process(arr, mid + 1, r) +
                // 归并
                merge(arr, l, mid, r);

    }

    /**
     * @param arr 数组
     * @param l   数组左边界
     * @param m   数组中心
     * @param r   数组右边界
     */
    public static int merge(int[] arr, int l, int m, int r) {
        // 存放归并结果
        int[] help = new int[r - l + 1];
        int i = 0; // 记录help的索引
        int p1 = l; // 指向l
        int p2 = m + 1; // 指向中点右边的区域
        int res = 0;
        while (p1 <= m && p2 <= r) {
            // 求小和, 如果大小一样则优先复制右侧的，且不计入小和
            // 如果右边大，则 r-p2+1 代表右侧有多少比他大的数
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 如果左半部分还有剩余
        while (p1 <= m) {
            // 放入help数组
            help[i++] = arr[p1++];
        }
        // 如果右半部分还有剩余
        while (p2 <= r) {
            // 放入help数组
            help[i++] = arr[p2++];
        }
        // 将原数组的部分(无序)用help代替（部分有序）
        for (i = 0; i < help.length; ++i) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2,  5};
        mergeSort(arr);
        for (int x : arr) {
            System.out.printf("%d\t", x);
        }
    }
}
