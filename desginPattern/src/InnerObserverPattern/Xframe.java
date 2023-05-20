package InnerObserverPattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Xframe extends JFrame {

    Xframe() throws HeadlessException {
        super();
        this.setVisible(true);
        this.setTitle("Observer test");
        JButton jButton = new JButton("should i do it ?");
        jButton.addActionListener(new AngelListener());
        jButton.addActionListener(new DevilListener());
        this.getContentPane().add(BorderLayout.CENTER,jButton);
    }
}
class AngelListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"Dont do it,you might regret it!");
    }
}

class DevilListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"come on,do it!");
    }
}
class test{
    public static void main(String args[]){
        Xframe xframe =new Xframe();

    }
}
