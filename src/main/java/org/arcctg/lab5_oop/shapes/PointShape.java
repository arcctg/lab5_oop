package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.PointDrawable;

public class PointShape extends Shape implements PointDrawable {

    @Override
    public void show(GraphicsContext gc) {
        drawPoint(gc, x1, y1);
    }
}