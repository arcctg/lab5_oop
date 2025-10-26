package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineShape extends Shape implements LineDrawable {

    @Override
    public void show(GraphicsContext gc) {
        drawLine(gc, x1, y1, x2, y2);
    }
}