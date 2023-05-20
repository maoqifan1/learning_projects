#include <stdio.h>
#include <stdlib.h>
typedef int ElemeType;

typedef struct Node
{
    ElemeType data;              // 节点数据
    struct Node *lchild, *rchild; // 左右孩子节点
} BTNode, *BitTree;

BTNode *BST_Search(BitTree T, ElemeType key, BTNode *p) // 搜索
{
    p = NULL;
    while (T != NULL && T->data != key)
    {
        p = T;             // 指向双亲节点
        if (key < T->data) // 左小右大
            T = T->lchild;
        else
            T = T->rchild;
    }
    return T;
}

int BST_Insert(BitTree T, ElemeType e) // 插入节点
{
    if (T == NULL) // 根节点为空直接插入
    {
        T = (BitTree)malloc(sizeof(BTNode)); // 申请内存空间
        T->data = e;
        T->lchild = T->rchild = NULL;
        return 1;
    }
    else if (e == T->data) // 有相同节点不插入
        return 0;
    else if (e < T->data)
        return BST_Insert(T->lchild, e);
    else
        return BST_Insert(T->rchild, e);
}

void BST_Create(BitTree T,ElemeType data[],int n){ // 构造树
    T = NULL;
    int i = 0;
    while(i<n){
        BST_Insert(T,data[i]);
        i++;
    }
}

int main(){
    BitTree t;
    ElemeType data[] = {11,22,3,4,98,482,23,40};
    BST_Create(t,data,sizeof(data)/sizeof(ElemeType)); // 构造BST
    BTNode *p;
    BST_Search(t,98,p);
    printf("%d",p->data);
    return 0;
}
