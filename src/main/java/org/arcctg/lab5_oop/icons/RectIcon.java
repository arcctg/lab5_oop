package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.RectDrawable;

public class RectIcon extends Icon implements RectDrawable {

    public RectIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setLineWidth(1);

        double margin = 2.5;
        double rectWidth = width - (2 * margin);
        double rectHeight = height - (2 * margin);

        drawRect(gc, margin, margin, margin + rectWidth, margin + rectHeight);
    }
}