package org.arcctg.lab5_oop;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.arcctg.lab5_oop.shapes.Shape;

public class MyEditor {

    private static final int ARRAY_SIZE = 118;
    private final List<Shape> shapes = new ArrayList<>(ARRAY_SIZE);
    private double startX, startY;
    private final GraphicsContext gc;
    private Shape currentShape;

    public MyEditor(GraphicsContext gc) {
        this.gc = gc;
    }

    public void start(Shape currentShape) {
        this.currentShape = currentShape;
    }

    public void onLBDown(MouseEvent event) {
        if (currentShape == null) return;

        startX = event.getX();
        startY = event.getY();
    }

    public void onLBUp(MouseEvent event) {
        if (currentShape == null) return;

        currentShape.setFinished(true);
        currentShape.set(startX, startY, event.getX(), event.getY());
        shapes.add(currentShape.clone());
        currentShape.setFinished(false);
        onPaint();
    }

    public void onMouseMove(MouseEvent event) {
        if (currentShape == null) return;

        onPaint();
        drawRubberBand(event);
    }

    private void drawRubberBand(MouseEvent event) {
        gc.save();
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.setLineDashes(5);
        currentShape.set(startX, startY, event.getX(), event.getY());
        currentShape.show(gc);
        gc.restore();
    }

    public void onPaint() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (Shape shape : shapes) {
            shape.show(gc);
        }
    }

    public void clear() {
        shapes.clear();
        onPaint();
    }
}
