package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Icon {

    protected final double width;
    protected final double height;

    protected Icon(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Canvas createCanvas() {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        draw(gc);

        return canvas;
    }

    protected abstract void draw(GraphicsContext gc);
}