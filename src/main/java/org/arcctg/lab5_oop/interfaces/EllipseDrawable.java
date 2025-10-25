package org.arcctg.lab5_oop.interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface EllipseDrawable {
    default void drawEllipse(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double radiusX = Math.abs(x2 - x1);
        double radiusY = Math.abs(y2 - y1);
        double x = x1 - radiusX;
        double y = y1 - radiusY;
        double width = radiusX * 2;
        double height = radiusY * 2;

        gc.strokeOval(x, y, width, height);
    }

    default void fillEllipse(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double radiusX = Math.abs(x2 - x1);
        double radiusY = Math.abs(y2 - y1);
        double x = x1 - radiusX;
        double y = y1 - radiusY;
        double width = radiusX * 2;
        double height = radiusY * 2;

        gc.setFill(Color.rgb(192, 192, 192));
        gc.fillOval(x, y, width, height);
    }
}
