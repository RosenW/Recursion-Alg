package models.dragonCurve;

import core.Engine;
import models.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DragonCurve implements Model {

    private int startingX;
    private int startingY;
    private int size;
    private List<Line> lines;

    public DragonCurve(int size) {
        this.size = size;
        startingX = Engine.WIDTH / 2;
        startingY = Engine.HEIGHT - 250;
        lines = new ArrayList<>();
        draw(size, 0);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Ariel", 1, 50));
        g.drawString(String.format("Iteration: %s", this.size), 200, 500);
        for (Line line : lines) {
            switch (line.getDirection()) {
                case 1:
                    g.drawLine(line.getX(), line.getY(), line.getX(), line.getY() - 10);
                    break;
                case 2:
                    g.drawLine(line.getX(), line.getY(), line.getX() + 10, line.getY());
                    break;
                case 3:
                    g.drawLine(line.getX(), line.getY(), line.getX(), line.getY() + 10);
                    break;
                case 4:
                    g.drawLine(line.getX(), line.getY(), line.getX() - 10, line.getY());
                    break;
            }
        }
    }

    private void draw(int size, int i) {
        if (i != size && i >= 0) {
            if (i == 0) {
                lines.add(new Line(3, startingX, startingY));
                lines.add(new Line(2, startingX, startingY + 10));
            } else {
                List<Line> newLineList = new ArrayList<>();

                Line lastLineSave = null;
                for (int j = lines.size(); j > 0; j--) {
                    if (j == lines.size()) {
                        Line newLine = new Line(lines.get(j - 1).getCCWDirection(), lines.get(j - 1).generateNextX(), lines.get(j - 1).generateNextY());
                        lastLineSave = newLine;
                        newLineList.add(newLine);
                    } else {
                        if (lines.get(j - 1).getDirection() == lines.get(j).getCCWDirection()) { //going left backwards
                            Line newLine = new Line(lastLineSave.getCCWDirection(), lastLineSave.generateNextX(), lastLineSave.generateNextY());
                            lastLineSave = newLine;
                            newLineList.add(newLine);
                        } else if (lines.get(j - 1).getDirection() == lines.get(j).getCWDirection()) { //going right backwards
                            Line newLine = new Line(lastLineSave.getCWDirection(), lastLineSave.generateNextX(), lastLineSave.generateNextY());
                            lastLineSave = newLine;
                            newLineList.add(newLine);
                        }
                    }
                }
                lines.addAll(newLineList);
            }
            draw(size, i + 1);
        }
    }
}
