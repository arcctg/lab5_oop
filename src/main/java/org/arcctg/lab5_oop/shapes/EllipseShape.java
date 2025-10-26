package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;

public class EllipseShape extends Shape implements EllipseDrawable {

    @Override
    public void show(GraphicsContext gc) {
        if (isFinished) fillEllipse(gc, x1, y1, x2, y2);
        drawEllipse(gc, x1, y1, x2, y2);
    }
}