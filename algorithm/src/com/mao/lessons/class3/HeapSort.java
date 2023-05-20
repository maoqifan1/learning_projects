package com.mao.lessons.class3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort {
    public static void heapSort(int[] arr) {
        // 不能构成堆
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 建立大根堆
        buildHeap(arr);
        // 把堆顶和堆底元素交换，然后重建堆
        int end = arr.length - 1;
        while (end > 0) {
            // 交换
            swap(arr, 0, end);
            // end-- 即最后一个现在最大了，应为大顶堆的根永远是最大的
            // 而现在根变成了最后一个元素，所以现在最后一个元素最大了
            end--;
            // 继续调整成大根堆
            adjustHeap(arr, 0, end);
        }

    }

    public static void buildHeap(int[] arr) {
        // 数组中最后一个结点的父节点
        int parent = (arr.length - 1 - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            // 自底向上调整
            adjustHeap(arr, i, arr.length - 1);
        }
    }

    public static void adjustHeap(int[] arr, int parent, int end) {
        int child = parent * 2 + 1; // 左子节点
        int temp = arr[parent]; // 保存父节点的值
        while (child <= end) {
            // 当右子节点存在且左子节点小于右子节点的值
            if (child + 1 <= end && arr[child] < arr[child + 1]) {
                child++; // child变为右节点
            }
            // 如果右节点比parent大，交换
            if (temp < arr[child]) {
                arr[parent] = arr[child];
                // 父节点变为当前的右节点
                parent = child;
                // 子节点更新
                child = 2 * parent + 1;
            } else {
                break;
            }

        }
        // 循环结束，需要把一开始的堆顶位置放置在子节点的位置处,即
        arr[parent] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * <p>
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
     * 并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。
     * </p>
     * 
     * @param arr 等待排序的数组
     * @param k   每个元素可移动的最大距离
     */
    public static void sortAlmostSortedArray(int[] arr, int k) {
        // 在java中，PriorityQueue是由小根堆实现的
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2)-> {
           return o1 - o2;
        });
            

        for (int i = 0; i < arr.length; ++i) {
            // 将数组元素添加到priorityQueue中
            heap.offer(arr[i]);

            // 如果超过k了，则放置到arr数组中
            if (heap.size() > k) {
                arr[i - k] = heap.poll(); // 弹出元素
            }
        }
        // 如果heap里面还有值，则全部替换到arr里面去
        while (!heap.isEmpty()) {
            arr[arr.length - heap.size()] = heap.poll();
        }
    }

    public static void main(String[] args) {
        // int[] arr = { 3, 5, 1, 7, 8, 9 };
        // HeapSort.heapSort(arr);
        // System.out.println(Arrays.toString(arr));
        int[] arr = { 6, 5, 3, 2, 8, 10, 9 };
        int k = 3;
        System.out.println("Before sorting -> " + Arrays.toString(arr));
        HeapSort.sortAlmostSortedArray(arr, k);
        System.out.println("After sorting -> " + Arrays.toString(arr));
    }
}