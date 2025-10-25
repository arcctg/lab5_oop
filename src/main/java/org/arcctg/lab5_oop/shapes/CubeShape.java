package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;
import org.arcctg.lab5_oop.interfaces.RectDrawable;

public class CubeShape extends Shape implements LineDrawable, RectDrawable {

    @Override
    public void show(GraphicsContext gc) {
        double dx = Math.abs(xs2 - xs1);
        double dy = Math.abs(ys2 - ys1);
        double offX = Math.max(10, Math.min(30, dx * 0.2));
        double offY = -Math.max(10, Math.min(30, dy * 0.2));

        drawRect(gc, xs1, ys1, xs2, ys2);
        drawRect(gc, xs1 + offX, ys1 + offY, xs2 + offX, ys2 + offY);

        drawLine(gc, xs1, ys1, xs1 + offX, ys1 + offY);
        drawLine(gc, xs2, ys1, xs2 + offX, ys1 + offY);
        drawLine(gc, xs1, ys2, xs1 + offX, ys2 + offY);
        drawLine(gc, xs2, ys2, xs2 + offX, ys2 + offY);
    }
}
