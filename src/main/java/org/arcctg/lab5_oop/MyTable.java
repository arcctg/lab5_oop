package org.arcctg.lab5_oop;

import java.util.List;
import java.util.function.Consumer;
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
    private Consumer<Integer> onSelectionChanged;
    @Setter
    private Consumer<Integer> onItemDeleted;

    public MyTable() {
        data = FXCollections.observableArrayList();
    }

    @FXML
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

    public void add(String name, double x1, double y1, double x2, double y2) {
        ShapeData shapeData = new ShapeData(name, x1, y1, x2, y2);
        data.add(shapeData);
    }

    public void addData(ShapeData shapeData) {
        data.add(shapeData);
    }

    public void clear() {
        data.clear();
    }

    public void loadData(List<ShapeData> items) {
        data.clear();
        data.addAll(items);
    }

    private void setUpListeners() {
        tableView.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null && onSelectionChanged != null) {
                    int index = tableView.getSelectionModel().getSelectedIndex();
                    onSelectionChanged.accept(index);
                }
            });

        tableView.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE && onItemDeleted != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                onItemDeleted.accept(selectedIndex);
                data.remove(selectedIndex);
            }
        } else if (event.getCode() == KeyCode.ESCAPE && onSelectionChanged != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                onSelectionChanged.accept(-1);
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
