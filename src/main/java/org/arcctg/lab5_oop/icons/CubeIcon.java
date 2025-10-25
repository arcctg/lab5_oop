package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.LineDrawable;
import org.arcctg.lab5_oop.interfaces.RectDrawable;

public class CubeIcon extends Icon implements LineDrawable, RectDrawable {

    public CubeIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setLineWidth(1);

        double offX = 3;
        double offY = -3;

        double x1 = 3.0;
        double y1 = 5.0;
        double x2 = width - 4.0;
        double y2 = height - 3.0;

        drawRect(gc, x1, y1, x2, y2);
        drawRect(gc, x1 + offX, y1 + offY, x2 + offX, y2 + offY);

        drawLine(gc, x1, y1, x1 + offX, y1 + offY);
        drawLine(gc, x2, y1, x2 + offX, y1 + offY);
        drawLine(gc, x1, y2, x1 + offX, y2 + offY);
        drawLine(gc, x2, y2, x2 + offX, y2 + offY);
    }
}
