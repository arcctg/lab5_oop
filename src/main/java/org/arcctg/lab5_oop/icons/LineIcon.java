package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineIcon extends Icon implements LineDrawable {

    public LineIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setLineWidth(2);

        double y = height / 2.0;

        drawLine(gc, 2, y, width - 2.0, y);
    }
}