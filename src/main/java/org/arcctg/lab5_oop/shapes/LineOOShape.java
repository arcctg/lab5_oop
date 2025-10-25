package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;
import org.arcctg.lab5_oop.interfaces.LineDrawable;

public class LineOOShape extends Shape implements LineDrawable, EllipseDrawable {

    @Override
    public void show(GraphicsContext gc) {
        double r = 8.0;
        double dx = xs2 - xs1;
        double dy = ys2 - ys1;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist > 2 * r) {
            double new_xs1 = xs1 + (dx * r / dist);
            double new_ys1 = ys1 + (dy * r / dist);
            double new_xs2 = xs2 - (dx * r / dist);
            double new_ys2 = ys2 - (dy * r / dist);
            drawLine(gc, new_xs1, new_ys1, new_xs2, new_ys2);
        }

        drawEllipse(gc, xs1, ys1, xs1 + r, ys1 + r);
        drawEllipse(gc, xs2, ys2, xs2 + r, ys2 + r);
    }
}
