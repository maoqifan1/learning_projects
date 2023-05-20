#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 30 // 存储空间初始分配量
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;      // Status是函数的类型，其值是根据函数结果状态代码，如ok等
typedef int ElementType; // 线性表类型

typedef struct
{
    ElementType data[MAX_SIZE]; // 数组存储数据元素，最大值为MAX_SIZE
    int length;                 // 线性表当前长度
} SqList;
/**
 * 用e返回线性表L中第i个数据元素的值
 * Status是函数的类型，其值是根据函数结果状态代码，如ok等
*/
Status GetEleme(SqList L, int i, ElementType *e)
{
    if (L.length == 0 || i < 1 || i > L.length)
        return ERROR;
    *e = L.data[i - 1];
    return OK;
}
/**
 * 在线性表的第i个位置之前插入新的数据元素e，L的长度+1
*/
Status ListInsert(SqList *L, int i, ElementType e)
{
    if (L->length == MAX_SIZE) // 线性表已满
        return ERROR;
    if (i > L->length + 1 || i < 1)  // 下表越界
        return ERROR;
    // 当插入的元素不在队尾时
    if (i != L->length)
    {
        for (int j = L->length - 1; j > i - 1; j++) // 将要插入位置后数据元素向前移动一位
        {
            L->data[j + 1] = L->data[j];
        }
    }
    L->data[i - 1] = e; //  插入新的元素
    L->length++;        // 线性表长度+1
    return OK;
}
Status ListDelete(SqList *L, int i, ElementType *e)
{
    if (L->length == 0) // 线性表为空
        return ERROR;
    if (i > L->length || i < 1) // 下标越界
        return ERROR;
    *e = L->data[i - 1]; // 获取要删除元素的数据
    if (i != L->length)  // 如果要删除的元素不在数组的最后
    {
        for (int j = i; j < L->length; j++) // 将删除位置后继元素前移
            L->data[j - 1] = L->data[j];
    }
    L->length--; // 线性表长度-1
    return OK;
}

int main()
{
    SqList *list = (SqList *)malloc(sizeof(SqList));
    printf("%ld\n", sizeof(list->data) / 4);
    printf("%D", ListInsert(list, 1, 3));
}
