#include <stdio.h>
#define YES 1
#define NO 0
typedef int ElementType;
typedef struct node
{
    ElementType data;
    struct node *left, *right;
}*root; // 定义根节点