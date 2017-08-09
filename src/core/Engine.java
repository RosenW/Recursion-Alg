package core;

import display.*;
import display.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Engine extends Canvas implements Runnable {

    public static final int SCALE = 3;
    public static final int WIDTH = 450 * SCALE;
    public static final int HEIGHT = WIDTH * 9 / 12;
    public static final String TITLE = "Recursion Algs";

    public boolean running = false;

    public DrawingTool drawingTool;

    private Thread thread;

    public Engine() {
        drawingTool = new DrawingTool();
        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            long lastTime = System.nanoTime();
            double amountOfTicks = 20.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (running) {

                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running) {
                    render();
                }
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    frames = 0;
                }
            }
            stop();
        }
    }

    private void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void tick() {
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        this.drawingTool.render(g);

        g.dispose();
        bs.show();
    }

}
