package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineShape extends Shape implements LineDrawable {

    @Override
    public void show(GraphicsContext gc) {
        drawLine(gc, xs1, ys1, xs2, ys2);
    }
}