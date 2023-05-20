package com.mao.myCollection;


@SuppressWarnings("all")
public class MyArrayList<AnyType> implements Iterable<AnyType> {
    // 默认的初始化容量
    private static final int DEFAULT_CAPACITY = 10;

    // 存储数组大小的变量
    private int theSize;

    // 存储数组
    private AnyType[] theItems;


    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    /**
     * 用于将当前数组清空并将数组初始化为默认指定大小
     */
    private void doClear() {
        theSize = 0;
        ensureCapcacity(DEFAULT_CAPACITY);
    }

    // 返回当前数组大小
    public int size() {
        return theSize;
    }

    // 判断当前数组是否为空
    public Boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapcacity(size());
    }

    /**
     * @param idx 为需要获取的元素的索引
     * @return 当前索引的元素值
     */
    public AnyType get(int idx) {
        // 如果元素索引小于0或者和数组边界值相同，则抛出数组越界异常
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    /**
     * @param idx    需要修改元素的索引
     * @param newVal 为该索引修改的新值
     * @return 为修改之前该索引位置的元素值
     */
    public AnyType set(int idx, AnyType newVal) {
        // 如果元素索引小于0或者和数组边界值相同，则抛出数组越界异常
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();

        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }


    @SuppressWarnings("unchecked")
    public void ensureCapcacity(int newCapacity) {
        // 如果新的容量小于旧的容量，则不执行接下来的语句
        if (newCapacity < theSize)
            return;

        // 新建数组并将原来的数组复制给它
        AnyType[] old = theItems;
        // 新建一个指定大小的新数组
        theItems = (AnyType[]) new Object[newCapacity];
        // 通过for循环将原来数组中的元素值赋值给新数组
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public Boolean add(AnyType x) {
        // 添加新元素到数组末尾
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        // 如果数组已满，则将数组大小扩大两倍
        if (theItems.length == size())
            ensureCapcacity(size() * 2 + 1);

        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];
        theItems[idx] = x;

        // 由于添加了一个元素，theSize自增1
        theSize++;
    }

    public AnyType remove(int idx) {
        if (idx < 0 || idx >= size())
            throw new IndexOutOfBoundsException();

        AnyType removeItem = theItems[idx];
        for (int i = idx;i<size() -1;i++)
            theItems[i] = theItems[i+1];

        theSize--;
        return removeItem;
    }

    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current<size();
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
