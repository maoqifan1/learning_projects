#include <iostream>
typedef int Rank;           // 向量的秩
#define DEFAULT_CAPACITY 10 // 数组默认空间大小
using namespace std;

template <typename T>
struct increase
{
    virtual void operator()(T &e) { e++; }
};
template <typename T>
class myVector
{
private:
    int _size;     // 数组实际存储元素的长度
    int _capacity; // 数组容量
    T *_elem;      // 数据区
protected:
    /*-----  复制数组元素到向量中 -----*/
    void copyFrom(T *const A, Rank low, Rank high)
    {
        _elem = new T[_capacity = (high - low) << 1]; // 分配空间
        _size = 0;                                    // 规模清零
        while (low < high)                            // A[low,high) 的元素逐一复制到 _elem[0,high - low]中去
        {
            _elem[_size++] = A[low++];
        }
    }

    /*----- 扩容算法 -----*/
    void expand() // 向量空间不足时进行扩容
    {
        if (size() < _capacity)
            return;                                   // 内存不满时不需要扩容
        _capacity = max(_capacity, DEFAULT_CAPACITY); // 不低于最小容量
        T *oldElem = _elem;                           // 保存原有数据
        _elem = new T[_capacity << 1];                // 容量扩充两倍
        for (int i = 0; i < size(); i++)
        {
            _elem[i] = oldElem[i]; // 通过循环将旧数组的值复制到新数组当中
        }
        delete[] oldElem; // 删除旧数组
    }

    /*----- 缩容算法 -----*/
    void shrink() // 向量空间过多时进行缩容
    {
        if (_capacity <= size()) // 满了就不能缩了
            return;
        _capacity = size();
        printf("%d", size());
        T *oldElem = _elem;       // 保存原有数组
        _elem = new T[_capacity]; // 收容后的新数组
        for (int i = 0; i < size(); i++)
        {
            _elem[i] = oldElem[i]; // 通过循环将旧数组的值拷贝到新数组中
        }
        delete[] oldElem; // 删除旧数组
    }

    /* 冒泡(升序)排序子程序 */
    Rank bubble(Rank low, Rank high)
    {
        Rank last = low;     // 最右侧的逆序对初始化为[low -1 ,low]
        while (++low < high) // 自左向右逐一检查各对相邻元素
        {
            if (_elem[low - 1] > _elem[low]) // 如果存在逆序的情况
            {
                last = low;                       // 更新最右侧的逆序对位置记录
                swap(_elem[low - 1], _elem[low]); // 交换位置
            }
        }
        return last; // 返回最右侧的逆序对的位置
    }

public:
    /*冒泡排序(升序)主程序*/
    void bubbleSort(Rank low, Rank high)
    {
        while (low < (high = bubble(low, high)))
            ; // 逐趟扫描，直至全序
    }

    /*----- 针对有序向量的二分查找算法（优化后的二分查找） -----*/
    Rank bianarySearch(T const &e, Rank low, Rank high)
    {
        while (high - low > 1)
        {
            Rank mid = (high + low) >> 1; // 得到中心点
            e < _elem[mid] ? (high = mid) : (low = mid);
        }
        return (e == _elem[low] ? low : -1);
    }

    /*----- 有序向量的唯一化 -----*/
    int unquify()
    {
        Rank i = 0, j = 0; // 记录 i,j 的秩
        while (++j != size() + 1)
        {
            if (_elem[i] != _elem[j]) // 如果两个元素不等，则把后面的元素移动到左边元素右后面的位置
                _elem[++i] = _elem[j];
        }
        _size = ++i;  // 最后不重复的元素的数量就是i的秩加一
        shrink();     // 收容
        return j - i; // 向量规模的变化量，即被删除元素的总数
    }

    /*----- 判断有序向量中逆序相邻元素对的总数 -----*/
    int disordered()
    {
        int n = 0;
        for (int i = 1; i < size(); i++)
        {
            n += (_elem[i - 1] > _elem[i]); // 逆序则计数
        }
        return n;
    }

    /*----- 元素插入操作 -----*/
    Rank insert(Rank r, T const &e)
    {
        //由于可能会导致容量不够的情况存在，所以先调用扩容函数
        expand();
        for (int i = size(); i > r; i--) // 元素后移一位
        {
            _elem[i] = _elem[i - 1];
        }
        _elem[r] = e; // 插入
        _size++;      // 大小加一
        return r;     // 返回秩
    }

    /*----- 区间删除 [low,high) -----*/
    int remove(Rank low, Rank high)
    {
        if (low == high) // 平凡情况
            return 0;
        while (high < size())
        {
            _elem[low++] = _elem[high++]; // [high,_size] 顺次前移 high - low 位,相当于把数组前移后直接占掉需要删除的区间
        }
        _size = low;       // 更新规模
        return high - low; // 返回被删除的元素个数
    }

    /*----- 在指定区间进行查找 0 <= low < high < _size -----*/
    Rank find(T const &e, Rank low, Rank high) const
    {
        // 在命中多个元素时，返回秩最大者
        while ((low < high--) && e != _elem[high])
            ;                          // 逆向查找
        return low < high ? high : -1; // high < low时查找失败 返回 -1，否则high即命中元素的秩,并将其返回
    }

    /*----- 单元素删除 -----*/
    T remove(Rank r)
    {
        T item = _elem[r]; // 保存待删除元素的数据
        remove(r, r + 1);  // 调用区间删除完成单元素删除
        return item;       // 返回被删除元素的值
    }

    /*----- 去重操作（无序向量） ----*/
    int deduplicate()
    {
        int old_size = size(); // 保存未去重前的数组长度
        Rank i = 1;            // 从第一个元素开始
        while (i < size())
        {
            (find(_elem[i], 0, i) < 0) ? ++i : remove(i); // 若find函数返回 小于1 则表示没有该元素，也就意味者该区间无重复，所以指针前移
        }
        shrink();                 // 收缩数组容量
        return old_size - size(); // 返回去重后的数组长度
    }

    /*----- 提供遍历向量的接口  -----*/
    template <typename VST>
    void traverse(VST &visit)
    {
        for (int i = 0; i < size(); i++)
            visit(_elem[i]);
    }

    /*----- 返回数组实际长度 -----*/
    int size()
    {
        return _size;
    }

    void increases()
    {
        traverse(increase<T>());
    }

    /*----- 重载下标运算符 -----*/
    T &operator[](Rank r) const
    {
        return _elem[r];
    }

    /*----- 构造函数及其重载形式 -----*/
    myVector(int c = DEFAULT_CAPACITY) // 默认构造
    {
        _elem = new T[_capacity = c]; // 根据用户输入的具体容量大小构造数组空间
        _size = 0;
    }
    myVector(T *const A, Rank low, Rank high) // 数组区间复制
    {
        copyFrom(A, low, high);
    }
    myVector(myVector<T> const &v, int low, int high) // 向量区间复制
    {
        copyFrom(v._elem, low, high);
    }
    myVector(myVector<T> const &v) // 向量整体复制
    {
        copyFrom(v._elem, 0, v.size());
    }

    /*----- 析构函数 -----*/
    ~myVector()
    {
        delete[] _elem;
    }
};