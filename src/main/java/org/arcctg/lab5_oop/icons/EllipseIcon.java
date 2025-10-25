package org.arcctg.lab5_oop.icons;

import javafx.scene.canvas.GraphicsContext;
import org.arcctg.lab5_oop.interfaces.EllipseDrawable;

public class EllipseIcon extends Icon implements EllipseDrawable {

    public EllipseIcon() {
        super(16, 16);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setLineWidth(1.2);

        drawEllipse(gc, height/2.0, width/2.0, width - 2.0, height - 3.0);
    }
}