#include <stdio.h>
#include <stdlib.h>
#define MaxVertexNum 100
typedef char VertexType; // 节点存储的值的类型
typedef int EdgeType;    // 边的类型
typedef struct MGraph
{
    VertexType Vex[MaxVertexNum];              // 顶点集合
    EdgeType Edge[MaxVertexNum][MaxVertexNum]; // 边集合
    int vexnum, arcnum;                        // 存储顶点个数和弧的个数
} MGraph;
