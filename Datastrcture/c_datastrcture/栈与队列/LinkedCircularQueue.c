#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status; // Status是函数的类型，其值是根据函数结果状态代码，如ok等
typedef int ElemType;

typedef struct Node
{
    ElemType data;       // 数据部分
    struct Node *next;   // 指向的下一个节点
} Node, *LinkedQueueptr; // LinkedQueue指向链表的首节点

typedef struct
{
    LinkedQueueptr front; // 队头指针(头节点)
    LinkedQueueptr rear;  // 队尾指针
} LinkQueue;
Status InitQueue(LinkQueue *Q)
{
    Q->front = Q->rear;
    return OK;
}
// 插入元素e为队列的新队尾元素
Status EnQueue(LinkQueue *Q, ElemType e)
{
    LinkedQueueptr s = (LinkedQueueptr)malloc(sizeof(Node)); // 创建一个节点
    if (!s)
        exit(0);
    s->data = e;       // 为节点设置数据
    s->next = NULL;    // 设置该节点无后继节点
    Q->rear->next = s; // 将该节点加入队尾
    Q->rear = s;       // 队尾指针指向该节点
    return OK;
}
// 若队列不为空，删除队列的队头元素，用e返回其值，并返回OK，否则返回ERROR
Status DeQueue(LinkQueue *Q, ElemType *e)
{
    if (Q->front == Q->rear) // 队列为空
        return ERROR;
    LinkedQueueptr p;
    p = Q->front->next;       // 指向将要删除的节点
    *e = p->data;             // 获得将要删除节点的值
    Q->front->next = p->next; // 使该节点从链表中移出
    if (Q->rear == p)          // 若队头也是队尾，则删除该节点后将尾指针指向头节点
        Q->rear = Q->front;
    free(p); // 释放该节点的内存
    return OK;
}
int main()
{
    LinkQueue *Q;
    ElemType *e;
    EnQueue(Q, 1);
    DeQueue(Q, e);
}