package org.arcctg.lab5_oop.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface LineDrawable {
    default void drawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }
}
