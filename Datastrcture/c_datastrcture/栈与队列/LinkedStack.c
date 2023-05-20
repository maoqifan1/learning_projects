#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define MAX_SIZE 30
typedef int Status;
typedef int ElemType;

typedef struct StackNode
{
    ElemType data;
    struct StackNode *next;
} StackNode, *LinkStackPtr; // LinkStackPtr为栈顶指针

typedef struct LinkedStack
{
    LinkStackPtr top;
    int count;
} LinkedStack;

Status push(LinkedStack *S, ElemType e)
{
    LinkStackPtr node = (StackNode *)malloc(sizeof(StackNode)); // 新建一个节点
    node->data = e;                                             // 为节点赋值
    node->next = S->top;                                        // 把当前的栈顶元素赋值给新节点的直接后继
    S->top = node;                                              // 使栈顶指针指向新加入的节点
    S->count++;
    return OK;
}
Status pop(LinkedStack *S, ElemType *e)
{
    LinkStackPtr ptr;
    *e = S->top->data;     // 获得弹出节点的数据
    ptr = S->top;          // 使指针指向栈顶节点
    S->top = S->top->next; // 指向栈顶节点的指针下移
    free(ptr);             // 释放栈顶节点的空间
    S->count--;            // 栈长-1
    return OK;
}
int main(){  
}