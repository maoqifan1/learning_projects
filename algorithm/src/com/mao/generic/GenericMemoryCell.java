package com.mao.generic;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class GenericMemoryCell<AnyType> {

    public AnyType read() {
        return this.storedValue;
    }

    public void write(AnyType storedValue) {
        this.storedValue = storedValue;
    }

    private AnyType storedValue;

    public static <AnyType> Boolean contains(AnyType[] arr, AnyType arg) {
        for (AnyType val : arr) {
            if (arg.equals(val))
                return true;
        }
        return false;
    }

    public static <AnyType extends Comparable<? super AnyType>>
    AnyType findMax(AnyType[] arr) {

        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;
        }
        return arr[maxIndex];
    }

    static <AnyType> AnyType findMax(AnyType[] arr,Comparator<? super AnyType> cmp){

        int maxIndex = 0;
        for(int i=0;i<arr.length;i++){
            if(cmp.compare(arr[i],arr[maxIndex])>0)
                maxIndex = i;
        }
        return arr[maxIndex];
    }
}
class CaseInsensitiveCompare implements Comparator<String>{
    @Override
    public int compare(String lhs, String rhs) {
        return lhs.compareToIgnoreCase(rhs);
    }
}

class TestProgram{
    public static void main(String[] args){
        String []arr = {"ZEBRA","alligator","crocodile"};
        System.out.println(GenericMemoryCell.findMax(arr,new CaseInsensitiveCompare()));

    }
}
