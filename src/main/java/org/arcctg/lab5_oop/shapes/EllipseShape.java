package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;

public class EllipseShape extends Shape implements EllipseDrawable {

    @Override
    public void show(GraphicsContext gc) {
        if (isFinished) fillEllipse(gc, xs1, ys1, xs2, ys2);
        drawEllipse(gc, xs1, ys1, xs2, ys2);
    }
}