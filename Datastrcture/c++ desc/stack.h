#include "vector.h"

template <typename T>
class myStack : public myVector<T>
{
public:
    void push(T const &e) { insert(size(), e); } // 从栈顶压入一个元素
    T pop() { remove(size() - 1); }              // 弹出栈顶元素
    T &top() { return (*this)[size() - 1]; }     // 访问栈顶元素
};
