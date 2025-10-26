package org.arcctg.lab5_oop;

import java.util.List;
import java.util.function.Function;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;
import org.arcctg.lab5_oop.shapes.Shape;

public class MyTable {

    private Stage tableStage;
    private TableView<ShapeData> tableView;
    private final ObservableList<ShapeData> data;
    @Setter
    private TableObserver observer;

    public MyTable() {
        data = FXCollections.observableArrayList();
        createTable();
        setUpListeners();
    }

    public void show() {
        if (tableStage == null) {
            createStage();
        }
        tableStage.show();
    }

    public void hide() {
        if (tableStage != null) {
            tableStage.hide();
        }
    }

    public void add(Shape shape) {
        ShapeData shapeData = new ShapeData(
            shape.getClass().getSimpleName(),
            shape.getX1(),
            shape.getY1(),
            shape.getX2(),
            shape.getY2()
        );
        data.add(shapeData);
    }

    public void clear() {
        data.clear();
    }

    public void loadFromShapes(List<Shape> shapes) {
        data.clear();
        for (Shape shape : shapes) {
            add(shape);
        }
    }

    private void createTable() {
        tableView = new TableView<>();
        tableView.setItems(data);

        TableColumn<ShapeData, String> nameColumn = new TableColumn<>("Назва");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));

        TableColumn<ShapeData, Double> x1Column = createDoubleColumn("x1", ShapeData::x1);
        TableColumn<ShapeData, Double> y1Column = createDoubleColumn("y1", ShapeData::y1);
        TableColumn<ShapeData, Double> x2Column = createDoubleColumn("x2", ShapeData::x2);
        TableColumn<ShapeData, Double> y2Column = createDoubleColumn("y2", ShapeData::y2);

        tableView.getColumns().addAll(nameColumn, x1Column, y1Column, x2Column, y2Column);
    }

    private TableColumn<ShapeData, Double> createDoubleColumn(String title, Function<ShapeData, Double> valueExtractor) {
        TableColumn<ShapeData, Double> column = new TableColumn<>(title);
        column.setCellValueFactory(cellData -> new SimpleDoubleProperty(valueExtractor.apply(cellData.getValue())).asObject());

        return column;
    }

    private void setUpListeners() {
        tableView.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null && observer != null) {
                    int index = tableView.getSelectionModel().getSelectedIndex();
                    observer.onShapeSelected(index);
                }
            });

        tableView.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE && observer != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                observer.onShapeDeleted(selectedIndex);
                data.remove(selectedIndex);
            }
        } else if (event.getCode() == KeyCode.ESCAPE && observer != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                observer.onShapeSelected(-1);
                tableView.getSelectionModel().clearSelection();
            }
        }
    }


    private void createStage() {
        tableStage = new Stage();
        tableStage.setTitle("Таблиця об'єктів");
        tableStage.setWidth(600);
        tableStage.setHeight(400);

        Label instructionsLabel = new Label(
            "Керування: DELETE - видалити об'єкт, ESCAPE - скасувати виділення"
        );
        instructionsLabel.setStyle("-fx-padding: 5; -fx-font-size: 12;");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(instructionsLabel, tableView);

        tableStage.setScene(new Scene(vbox));
    }


    public record ShapeData(String name, double x1, double y1, double x2, double y2) {}
}
