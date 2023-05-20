#include <stdio.h>
#include <stdlib.h>
#define MaxVertexNum 100
typedef char VertexType; // 节点存储的值的类型
typedef int EdgeType;    // 边的类型

typedef struct ArcNode // 边表节点
{
    int adjvex;
    struct ArcNode *next; // 下一个节点的指针
} ArcNode;

typedef struct VNode // 顶点表
{
    VertexType data; // 数据域部分
    ArcNode *first;  // 边表的首节点指针
} VNode, AdjList[MaxVertexNum];

typedef struct ALGraph // 邻接表
{
    AdjList vetices;    // 存储整个邻接表
    int vexnum, arcnum; // 顶点数量与边的数量
} AlGraph;