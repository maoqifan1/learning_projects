package com.mao;


import com.mao.generic.GenericMemoryCell;

import javax.swing.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        GenericMemoryCell<Integer> genericMemoryCell = new GenericMemoryCell<>();

        genericMemoryCell.write(12345);


        JOptionPane.showMessageDialog(null,genericMemoryCell.read());


    }



}
