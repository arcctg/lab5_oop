package org.arcctg.lab5_oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.arcctg.lab5_oop.shapes.Shape;

public class MyEditor {

    private static MyEditor instance;
    private static final int ARRAY_SIZE = 118;
    @Getter
    private final List<Shape> shapes = new ArrayList<>(ARRAY_SIZE);
    private double startX, startY;
    @Setter
    private GraphicsContext gc;
    @Getter
    private Shape currentShape;
    private int selectedShapeIndex = -1;
    private static final String OBJECTS_FILE_PATH = "src/main/resources/objects.txt";

    private MyEditor() {}

    public static MyEditor getInstance() {
        if (instance == null) {
            instance = new MyEditor();
        }
        return instance;
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
        appendShapeToObjectsFile(currentShape);
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
        clearCanvas();
        renderAllShapes();
    }

    private void clearCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    private void renderAllShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            renderShape(shape, i == selectedShapeIndex);
        }
    }

    private void renderShape(Shape shape, boolean isSelected) {
        if (isSelected) {
            gc.save();
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(3);
            gc.setLineDashes(0);
            shape.show(gc);
            gc.restore();
        } else {
            shape.show(gc);
        }
    }

    public void clear() {
        clearShapeSelection();
        shapes.clear();
        clearObjectsFile();
        onPaint();
    }

    private void clearObjectsFile() {
        writeObjectsToFile("", false);
    }

    public void appendShapeToObjectsFile(Shape shape) {
        writeObjectsToFile(shape.toString(), true);
    }

    private void writeObjectsToFile(String content, boolean append) {
        try (FileWriter writer = new FileWriter(MyEditor.OBJECTS_FILE_PATH, append)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAllShapesToFile(File filename) {
        clearShapeSelection();

        try (FileWriter writer = new FileWriter(filename)) {
            for (Shape shape : shapes) {
                if (shape != null) {
                    writer.write(shape.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            shapes.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                Shape shape = parseShapeFromLine(line);
                if (shape != null) {
                    shapes.add(shape);
                    appendShapeToObjectsFile(shape);
                }
            }
            onPaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private Shape parseShapeFromLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length != 5) {
            return null;
        }

        Shape shape = createShapeInstance(parts[0]);
        shape.set(
            Double.parseDouble(parts[1]),
            Double.parseDouble(parts[2]),
            Double.parseDouble(parts[3]),
            Double.parseDouble(parts[4])
        );
        shape.setFinished(true);

        return shape;
    }

    @SneakyThrows
    private Shape createShapeInstance(String className) {
        return (Shape) Class.forName(className)
            .getDeclaredConstructor()
            .newInstance();
    }

    public void clearShapeSelection() {
        updateShapeSelection(-1);
    }

    public void selectShape(int index) {
        if (index < shapes.size()) {
            updateShapeSelection(index);
        }
    }

    public void deleteShape(int index) {
        if (index >= 0 && index < shapes.size()) {
            shapes.remove(index);
            updateShapeSelection(-1);
        }
    }

    private void updateShapeSelection(int index) {
        selectedShapeIndex = index;
        onPaint();
    }
}
