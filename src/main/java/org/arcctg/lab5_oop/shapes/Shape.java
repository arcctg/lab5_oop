package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import lombok.Setter;

public abstract class Shape implements Cloneable {

    @Setter
    public boolean isFinished = false;
    protected double xs1, ys1, xs2, ys2;

    public void set(double x1, double y1, double x2, double y2) {
        xs1 = x1;
        ys1 = y1;
        xs2 = x2;
        ys2 = y2;
    }

    public abstract void show(GraphicsContext gc);

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}