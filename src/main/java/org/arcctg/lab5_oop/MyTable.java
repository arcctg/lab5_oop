package org.arcctg.lab5_oop;

import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.SneakyThrows;
import org.arcctg.lab5_oop.interfaces.TableObserver;
import org.arcctg.lab5_oop.shapes.Shape;

public class MyTable {

    @FXML private TableView<ShapeData> tableView;
    @FXML private TableColumn<ShapeData, String> nameColumn;
    @FXML private TableColumn<ShapeData, Double> x1Column;
    @FXML private TableColumn<ShapeData, Double> y1Column;
    @FXML private TableColumn<ShapeData, Double> x2Column;
    @FXML private TableColumn<ShapeData, Double> y2Column;

    private Stage tableStage;
    private final ObservableList<ShapeData> data;
    @Setter
    private TableObserver observer;

    public MyTable() {
        data = FXCollections.observableArrayList();
    }

    private void initialize() {
        tableView.setItems(data);

        nameColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().name()));
        x1Column.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().x1()).asObject());
        y1Column.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().y1()).asObject());
        x2Column.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().x2()).asObject());
        y2Column.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().y2()).asObject());

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

    @SneakyThrows
    private void createStage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
        loader.setController(this);

        tableStage = new Stage();
        tableStage.setTitle("Таблиця об'єктів");
        tableStage.setScene(new Scene(loader.load(), 600, 400));
    }

    public record ShapeData(String name, double x1, double y1, double x2, double y2) {}
}
