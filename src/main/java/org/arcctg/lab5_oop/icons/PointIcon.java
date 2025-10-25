package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.PointDrawable;

public class PointIcon extends Icon implements PointDrawable {

    public PointIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        drawPoint(gc, width / 2.0, height / 2.0);
    }
}