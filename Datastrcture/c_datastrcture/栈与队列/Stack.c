#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define MAX_SIZE 30
typedef int Status;
typedef int ElemType;
// 基础栈结构
typedef struct
{
    ElemType data[MAX_SIZE];
    int top; // 用于栈顶指针
} SqStack;           

Status push(SqStack *S, ElemType e)
{
    if (S->top == MAX_SIZE - 1) // 栈满
        return ERROR;
    S->top++;            //  栈顶指针后移，用于赋值
    S->data[S->top] = e; // 为当前元素赋值
    return OK;
}
Status pop(SqStack *S, ElemType *e)
{
    if (S->top == -1) // 栈空
        return ERROR;
    *e = S->data[S->top]; // 将要删除的栈顶与纳素赋值给e
    S->top--;             // 栈顶指针 -1
    return OK;
}
int main()
{
    int number, *e;
    SqStack *sq = (SqStack *)malloc(sizeof(SqStack));
    while (1)
    { 
        printf("请输入将要压入的数(输入0以退出,输入1弹出栈顶元素):\n");
        scanf("%d", &number);
        if (number == 0)
        {
            free(sq);
            break;
        }
        else if (number == 1)
        {
            pop(sq, e);
            printf("弹出了 :%d\n", *e);
        }
        else
        {
            push(sq, number);
            printf("压入了 :%d\n", number);
        }
    }
}
