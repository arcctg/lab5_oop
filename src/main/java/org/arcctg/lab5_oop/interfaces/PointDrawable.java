package org.arcctg.lab5_oop.interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface PointDrawable {
    default void drawPoint(GraphicsContext gc, double x, double y) {
        double size = 4;
        gc.setFill(Color.BLACK);
        gc.fillOval(x - size/2, y - size/2, size, size);
    }
}
