package org.arcctg.lab5_oop.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface RectDrawable {
    default void drawRect(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double x = Math.min(x1, x2);
        double y = Math.min(y1, y2);
        double width = Math.abs(x2 - x1);
        double height = Math.abs(y2 - y1);
        gc.strokeRect(x, y, width, height);
    }
}
