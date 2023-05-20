#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define MAX_SIZE 30
typedef int Status; // Status是函数的类型，其值是根据函数结果状态代码，如ok等
typedef int ElemType;

typedef struct
{
    ElemType data[MAX_SIZE];
    int front; // 头指针
    int rear;  // 尾指针 若队列不为空，指向队列尾元素的下一个位置
} SqQueue;

// 初始化一个空队列
Status InitQueue(SqQueue *Q)
{
    Q->front = 0;
    Q->rear = 0;
    return OK;
}

// 返回队列的元素个数，也就是队列的当前长度
int QueueLength(SqQueue Q)
{
    return (Q.rear - Q.front + MAX_SIZE) % MAX_SIZE;
}

// 若队列未满，则插入元素e为队列新的队尾元素
Status EnQueue(SqQueue *Q, ElemType e)
{
    // 判断队列是否为满
    if ((Q->rear + 1) % MAX_SIZE == Q->front)
        return ERROR;
    // 若不满，在队尾位置插入元素
    Q->data[Q->rear++] = e;
    // 队尾向后移，若到最后则转到数组头部
    Q->rear = (Q->rear + 1) % MAX_SIZE;
    return OK;
}
// 若队列不空，则删除队首元素，并用e返回其值
Status DeQueue(SqQueue *Q, ElemType *e)
{
    // 判断队列是否为空
    if (Q->rear == Q->front)
        return ERROR;
    // 将队首元素赋值给e
    e = &Q->data[Q->front];
    // 队首后移，若到最后则转到数组头部
    Q->front = (Q->front + 1) % MAX_SIZE;
    return OK;
}
int main()
{
    SqQueue *Q;
    ElemType *e;
    QueueLength(*Q);
    InitQueue(Q);
    EnQueue(Q, 1);
    DeQueue(Q, e);

    return 0;
}