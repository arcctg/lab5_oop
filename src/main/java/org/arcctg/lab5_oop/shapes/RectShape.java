package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.RectDrawable;

public class RectShape extends Shape implements RectDrawable {

    @Override
    public void show(GraphicsContext gc) {
        drawRect(gc, xs1, ys1, xs2, ys2);
    }
}