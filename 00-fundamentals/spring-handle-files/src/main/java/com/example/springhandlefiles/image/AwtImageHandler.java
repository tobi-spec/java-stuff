package com.example.springhandlefiles.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AwtImageHandler {

    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("spring-stuff/spring-handle-files/src/main/resources/example.jpg"));

        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(10, 10, bufferedImage.getWidth() -20,  bufferedImage.getHeight() - 20);

        JLabel label = new JLabel(new ImageIcon(bufferedImage));
        JPanel panel = new JPanel();
        panel.add(label);

        JFrame frame = new JFrame();
        frame.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
        frame.add(panel);
        frame.setVisible(true);
    }
}
