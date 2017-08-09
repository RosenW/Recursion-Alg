package display;

import core.Engine;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private JFrame frame;

    public Window(int width, int height, String title, Engine engine) {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.add(engine);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocus();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }
}

