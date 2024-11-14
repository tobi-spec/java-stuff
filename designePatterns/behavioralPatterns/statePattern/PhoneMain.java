package designePatterns.behavioralPatterns.statePattern;

import javax.swing.*;

public class PhoneMain {

    public static void main(String[] args) {
        Phone phone = new Phone();

        JButton home = new JButton("Home ");
        home.addActionListener(e -> System.out.println(phone.getState().onHome()));

        JButton offOn = new JButton("OffOn");
        offOn.addActionListener(e -> System.out.println(phone.getState().onOffOn()));

        JFrame frame = new JFrame("Phone Example");
        frame.setSize(300, 500);
        frame.setLayout(null);
        frame.add(home);
        home.setBounds(100, 80, 100, 40);
        frame.add(offOn);
        offOn.setBounds(100, 130, 100, 40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
