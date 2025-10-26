package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineOOShape extends Shape implements LineDrawable, EllipseDrawable {

    @Override
    public void show(GraphicsContext gc) {
        double r = 8.0;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist > 2 * r) {
            double newX1 = x1 + (dx * r / dist);
            double newY1 = y1 + (dy * r / dist);
            double newX2 = x2 - (dx * r / dist);
            double newY2 = y2 - (dy * r / dist);
            drawLine(gc, newX1, newY1, newX2, newY2);
        }

        drawEllipse(gc, x1, y1, x1 + r, y1 + r);
        drawEllipse(gc, x2, y2, x2 + r, y2 + r);
    }
}
