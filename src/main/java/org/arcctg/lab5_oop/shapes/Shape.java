package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

public abstract class Shape implements Cloneable {

    @Setter
    public boolean isFinished = false;
    @Getter
    protected double x1, y1, x2, y2;

    public void set(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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

    @Override
    public String toString() {
        return String.format("%s\t%.2f\t%.2f\t%.2f\t%.2f%n",
            this.getClass().getName(),
            x1, y1, x2, y2);
    }
}