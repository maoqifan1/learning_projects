#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define MAX_SIZE 30
typedef int Status;
typedef int ElemType;
// 两栈共享空间结构
typedef struct
{
    ElemType data[MAX_SIZE];
    int top1; // 栈1的栈顶指针
    int top2; // 栈2的栈顶指针
} SqDoubleStack;

// 插入元素e为新的栈顶元素
Status Push(SqDoubleStack *S, ElemType e, int StackNumber)
{
    if (S->top1 + 1 == S->top2) // 栈满，无法压栈
        return ERROR;
    if (StackNumber == 1) // 若栈1有元素进栈
    {
        S->top1++;
        S->data[S->top1] = e;
    }
    else if (StackNumber == 2) // 若栈2有元素进栈
    {
        S->data[--S->top2] = e;
    }
    return OK;
}

// 若栈不为空，则删除栈顶元素，并用e返回其值，并返回OK；否则返回ERROR
Status Pop(SqDoubleStack *S, ElemType *e, int StackNumber)
{
    if (StackNumber == 1) // 若删除栈1的栈顶元素
    {
        if (S->top1 == -1) // 若栈1为空
            return ERROR;
        *e = S->data[S->top1];
        S->top1--;
    }
    if (StackNumber == 2) // 若删除栈2的元素
    {
        if (S->top2 == MAX_SIZE) //若栈2为空
            return ERROR;
        *e = S->data[S->top2++];
    }
}
