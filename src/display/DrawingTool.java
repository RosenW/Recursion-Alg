package display;

import models.dragonCurve.DragonCurve;
import models.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingTool {

    private List<Model> models;

    public DrawingTool() {
        models = new ArrayList<>();
        models.add(new DragonCurve(12));
    }

    public void render(Graphics g) {
        for (Model model : models) {
            model.render(g);
        }
    }
}
