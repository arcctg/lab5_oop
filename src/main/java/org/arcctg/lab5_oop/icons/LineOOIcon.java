package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineOOIcon extends Icon implements LineDrawable, EllipseDrawable {

    public LineOOIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setLineWidth(1);

        double padding = 3.0;
        double y = height / 2.0;
        double x1 = padding;
        double x2 = width - padding;
        double r = 2.5;

        drawLine(gc, x1+r, y, x2-r, y);
        drawEllipse(gc, x1, y, x1 + r, y + r);
        drawEllipse(gc, x2, y, x2 + r, y + r);
    }
}
