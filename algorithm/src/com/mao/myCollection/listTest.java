package com.mao.myCollection;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class listTest {

    /**
     * 通过在末端添加项来构造一个list
     * @param lst 需要添加项的list
     * @param N 添加的项数
     * */
    static void makeList1(List<Integer> lst,int N){
        lst.clear();
        for(int i=0;i<N;i++){
            lst.add(i);
        }
    }

    /**
     * 通过在前端添加项来构造一个List
     * * @param lst 需要添加项的list
     * * @param N 添加的项数
     * */
    static void makeList2(List<Integer> lst,int N){
        lst.clear();
        for (int i=0;i<N;i++){
            lst.add(0,i);
        }
    }

    /**
     * 对list中的值求和
     * @param lst 需要求和的list
     * */
    static Integer sum(List<Integer> lst){
        if(lst.isEmpty())
            return 0;
        Integer total = 0;
        for (int i=0;i<lst.size();i++){
            total+=lst.get(i);
        }
        return total;
    }

    /**
     * 删除表中的偶数：算法对于所有类型的表都是二次的
     * */
    static void removeEvenVer1(List<Integer> lst){
        int i = 0;
        while (i<lst.size()){
            if (lst.get(i)%2==0){
                lst.remove(i);
            }else
                i++;
        }
    }

    /**
     * 删除表中的偶数，会触发ConCurrentModificationException异常而无法运行
     * */
    static void removeEvenVer2(List<Integer> lst){
        for (Integer x:lst){
            if (x%2 == 0)
                lst.remove(x);
        }
    }

    /**
     * @see java.util.ArrayList
     * @see java.util.ListIterator
     * 删除表中的偶数：对ArrayList是二次的，对LinkedList是线性的
     * 使用ListIterator
     * */
    static void removeEventVer4(List<Integer> lst){
        ListIterator<Integer> listIterator = lst.listIterator();
        while (listIterator.hasPrevious()){
            if (listIterator.previous()%2==0)
                lst.remove(listIterator.previous());
        }
    }

    /**
     * @see java.util.LinkedList
     * @see Iterator
     * 删除表中的偶数：对ArrayList是二次的，对LinkedList是线性的
     * 使用Iterator
     * */
    static void removeEvenVer3(List<Integer> lst){
        Iterator<Integer> iterator = lst.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next%2 ==0)
                lst.remove(next);
        }
    }
}
