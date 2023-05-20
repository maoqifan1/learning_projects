#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status; // Status是函数的类型，其值是根据函数结果状态代码，如ok等
typedef int ElemType;

typedef struct Node
{
    ElemType data;     // 当前节点的数据部分
    struct Node *next; // 指向下一个节点的指针
} Node, *LinkList;     // 定义链表的头指针，指向链表的头节点，借代整个链表

void reverse(LinkList *l) // 反转链表，采用原地算法
{
    LinkList r;
    LinkList p = (*l)->next;
    (*l)->next = NULL;
    while (p)  
    {
        r = p->next;
        p->next = (*l)->next;
        (*l)->next = p;
        p = r;
    }
}

// 获取链表中的元素
Status getEleme(LinkList L, int i, ElemType *e)
{
    LinkList p = L->next; // 指针指向链表的第一个节点（因为默认第一个为头指针）
    int j = 1;            // j用于计数
    while (p && j < i)    // 当p不为空且j小于查找元素的索引时，退出循环
    {
        p = p->next; // 不断迭代到下一个节点
        ++j;         // 计数加1
    }
    if (!p || j > i) // 若没找到则返回错误信息
        return ERROR;
    *e = p->data; // 若找到则使指针指向这个节点值的部分
    return OK;    //返回成功信息
}
// 进行链表元素的插入
Status ListInsert(LinkList *L, int i, ElemType e)
{
    LinkList p = *L;
    int j = 0;
    while (p && j < i)
    {
        p = p->next;
        ++j;
    }
    if (!p || j > i)
        return ERROR;
    LinkList n = (LinkList)malloc(sizeof(Node));
    n->data = e;
    n->next = p->next;
    p->next = n;
    return OK;
}
// 链表元素的删除
Status ListDelete(LinkList *L, int i, ElemType *e)
{
    LinkList p = *L;         //指向链表
    int j = 1;               // 用于计数
    while (p->next && j < i) // 迭代第i个元素前的那个元素
    {
        p = p->next;
        ++j;
    }
    if (!(p->next) || j > i) // 当节点不存在时
        return ERROR;
    LinkList q = p->next; // 保存第i个节点
    p->next = q->next;    // 使第i-1个节点的指针域指向第i个节点的下一个节点，以删除第i个节点
    *e = q->data;         // 保存删除掉的节点的数据部分
    free(q);              // 释放存储空间
    return OK;
}
/* 头插法 */
void CreateListHead(LinkList *L, int n)
{
    LinkList p;
    srand(time(0)); // 初始化随机数种子
    *L = (LinkList)malloc(sizeof(Node));
    (*L)->next = NULL; // 先建立一个带头节点的单链表
    for (int i = 0; i < n; i++)
    {
        p = (LinkList)malloc(sizeof(Node)); // 生成新节点
        p->data = rand() % 100 + 1;         // 随机生成100以内的数字
        p->next = (*L)->next;
        (*L)->next = p; // 插入到表头
    }
}
/* 尾插法 */
void CreateListTail(LinkList *L, int n)
{
    LinkList p, t;                       // p指向新插入的节点，t指向尾节点
    srand(time(0));                      // 初始化随机数种子
    *L = (LinkList)malloc(sizeof(Node)); // 创建链表
    t = *L;                              // 指向链表的尾部
    for (int i = 0; i < n; i++)
    {
        p = (LinkList)malloc(sizeof(Node)); // 生成新节点
        p->data = rand() % 100 + 1;         // 随机生成100以内的数字
        t->next = p;                        // 当前新插入的节点p为尾节点
        t = p;                              // 尾节点指针t指向尾节点
    }
    // 插入结束后，尾节点后无节点
    t->next = NULL;
}
Status clearList(LinkList *L)
{
    LinkList p, q;
    p = (*L)->next; // p指向第一个节点
    while (p)       // 没到表尾时继续迭代
    {
        q = p->next; // q指向下一个节点
        free(p);     // 释放当前节点的空间
        p = q;       // p指向下一个节点(q指向的节点)
    }
    (*L)->next = NULL; // 头节点的指针域为空;
    return OK;
}

int main()
{
    LinkList l = (LinkList)malloc(sizeof(Node));
    l->data = 2;
    l->next = NULL;
    ListInsert(&l, 1, 3);
    printf("%d", l->data);
    reverse(&l);
    printf("%d",l->data);
    return 0;
}