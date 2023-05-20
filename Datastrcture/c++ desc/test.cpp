#include "vector.h"

int main()
{

    int A[] = {2, 3, 10, 231, 3};
    myVector<int> vct(A, 0, 5);
    printf("%d\n", vct.size());
    printf("%d\n", vct.remove(1));
    // printf("%d\n",vct.deduplicate());
    // printf("%d\n", vct.unquify());
    vct.bubbleSort(0, vct.size());
    for (int i = 0; i <= vct.size(); i++)
        printf("%d\t", vct[i]);
    return 0;
}