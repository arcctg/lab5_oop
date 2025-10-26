package org.arcctg.lab5_oop.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;
import org.arcctg.lab5_oop.interfaces.RectDrawable;

public class CubeShape extends Shape implements LineDrawable, RectDrawable {

    @Override
    public void show(GraphicsContext gc) {
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double offX = Math.max(10, Math.min(30, dx * 0.2));
        double offY = -Math.max(10, Math.min(30, dy * 0.2));

        drawRect(gc, x1, y1, x2, y2);
        drawRect(gc, x1 + offX, y1 + offY, x2 + offX, y2 + offY);

        drawLine(gc, x1, y1, x1 + offX, y1 + offY);
        drawLine(gc, x2, y1, x2 + offX, y1 + offY);
        drawLine(gc, x1, y2, x1 + offX, y2 + offY);
        drawLine(gc, x2, y2, x2 + offX, y2 + offY);
    }
}
