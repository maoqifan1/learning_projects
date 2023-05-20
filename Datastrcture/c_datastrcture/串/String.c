#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
typedef int Status; // Status是函数的类型，其值是根据函数结果状态代码，如ok等
typedef int ElemType;

typedef struct String
{
    ElemType data[200];
    int length;
};

