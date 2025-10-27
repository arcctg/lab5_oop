package org.arcctg.lab5_oop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.File;

import org.arcctg.lab5_oop.icons.*;
import org.arcctg.lab5_oop.shapes.*;

public class MainController {

    @FXML private Canvas canvas;
    @FXML private Button pointButton;
    @FXML private Button lineButton;
    @FXML private Button rectButton;
    @FXML private Button ellipseButton;
    @FXML private Button cubeButton;
    @FXML private Button lineOOButton;
    @FXML private RadioMenuItem pointMenuItem;
    @FXML private RadioMenuItem lineMenuItem;
    @FXML private RadioMenuItem rectMenuItem;
    @FXML private RadioMenuItem ellipseMenuItem;
    @FXML private RadioMenuItem cubeMenuItem;
    @FXML private RadioMenuItem lineOOMenuItem;

    @Setter
    private Stage stage;
    private Button[] buttons;
    private MyEditor myEditor;
    private MyTable myTable;

    public void initialize() {
        initEditor();
        initTable();
        setupCanvasEvents();
        initButtons();
        clearButtonSelection();
        setupButtonIcons();
    }

    @FXML
    public void choosePoint() {
        myEditor.start(new PointShape());
        updateToolBarSelection(pointButton);
        updateMenuItemSelection(pointMenuItem);
    }

    @FXML
    public void chooseLine() {
        myEditor.start(new LineShape());
        updateToolBarSelection(lineButton);
        updateMenuItemSelection(lineMenuItem);
    }

    @FXML
    public void chooseRect() {
        myEditor.start(new RectShape());
        updateToolBarSelection(rectButton);
        updateMenuItemSelection(rectMenuItem);
    }

    @FXML
    public void chooseEllipse() {
        myEditor.start(new EllipseShape());
        updateToolBarSelection(ellipseButton);
        updateMenuItemSelection(ellipseMenuItem);
    }

    @FXML
    public void chooseCube() {
        myEditor.start(new CubeShape());
        updateToolBarSelection(cubeButton);
        updateMenuItemSelection(cubeMenuItem);
    }

    @FXML
    public void chooseLineOO() {
        myEditor.start(new LineOOShape());
        updateToolBarSelection(lineOOButton);
        updateMenuItemSelection(lineOOMenuItem);
    }

    @FXML
    private void handleClear() {
        myEditor.clear();
        myTable.clear();
    }

    @FXML
    private void handleShowTable() {
        myTable.show();
    }

    @FXML
    private void handleHideTable() {
        myTable.hide();
    }

    @FXML
    private void handleSaveFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(stage);
        myEditor.saveAllShapesToFile(selectedFile);
    }

    @FXML
    private void handleLoadFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        myEditor.loadFromFile(selectedFile);

        updateTableFromEditor();
    }

    private void updateTableFromEditor() {
        var shapeDataList = myEditor.getShapes().stream()
            .map(shape ->
                new MyTable.ShapeData(
                    shape.getClass().getSimpleName(),
                    shape.getX1(), shape.getY1(),
                    shape.getX2(), shape.getY2()
                )
            )
            .toList();

        myTable.loadData(shapeDataList);
    }

    @FXML
    public void handleExit() {
        Platform.exit();
        handleClear();
    }

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Про програму");
        alert.setHeaderText("Графічний редактор");
        alert.setContentText(
            """
                Лабораторна робота №5
                Розробка багатовіконного інтерфейсу користувача
                для графічного редактора об'єктів
                
                Варіант: 10
                Клас MyEditor: Singleton (класична реалізація)
                Гумовий слід: пунктирна червона лінія
                Куб та лінія з кружечками
                
                Нові можливості:
                - Singleton патерн для MyEditor
                - Немодальне вікно таблиці об'єктів
                - Автоматичний запис об'єктів у файл в директорії
                - Збереження об'єктів в обраний файл
                - Завантаження об'єктів з обраного файлу
                - Видалення об'єктів з таблиці
                - Зняття ви
                
                Із 4 лаби:
                Прямокутник: по кутам, чорний контур без заповнення
                Еліпс: від центру, чорний контур з сірим заповненням
                Позначка режиму: в меню
                Тулбар: кнопки для вибору інструментів малювання""");
        alert.showAndWait();
    }

    private void setupCanvasEvents() {
        canvas.setOnMousePressed(myEditor::onLBDown);
        canvas.setOnMouseReleased(event -> {
            Shape currentShape = myEditor.getCurrentShape();
            myEditor.onLBUp(event);

            if (currentShape != null) {
                myTable.add(
                    currentShape.getClass().getSimpleName(),
                    currentShape.getX1(), currentShape.getY1(),
                    currentShape.getX2(), currentShape.getY2()
                );
            }
        });
        canvas.setOnMouseDragged(myEditor::onMouseMove);
    }

    private void initButtons() {
        buttons = new Button[]{pointButton, lineButton, rectButton, ellipseButton, cubeButton,
            lineOOButton};
    }

    private void updateToolBarSelection(Button selectedButton) {
        clearButtonSelection();
        if (selectedButton != null) {
            selectedButton.setStyle(
                "-fx-background-color: #b8edf1; " +
                    "-fx-background-radius: 8; " +
                    "-fx-border-radius: 8; " +
                    "-fx-border-color: #87dbec; ");
        }
    }

    private void clearButtonSelection() {
        String defaultStyle = "-fx-background-color: #fafafa; " +
            "-fx-background-radius: 8; " +
            "-fx-border-radius: 8; " +
            "-fx-border-color: #c0c0c0; ";

        for (Button button : buttons) {
            if (button != null) {
                button.setStyle(defaultStyle);
            }
        }
    }

    private void updateMenuItemSelection(RadioMenuItem item) {
        item.setSelected(true);
    }

    private void setupButtonIcons() {
        pointButton.setGraphic(createIcon(new PointIcon()));
        lineButton.setGraphic(createIcon(new LineIcon()));
        rectButton.setGraphic(createIcon(new RectIcon()));
        ellipseButton.setGraphic(createIcon(new EllipseIcon()));
        cubeButton.setGraphic(createIcon(new CubeIcon()));
        lineOOButton.setGraphic(createIcon(new LineOOIcon()));
    }

    private Canvas createIcon(Icon icon) {
        return icon.createCanvas();
    }

    private void initTable() {
        myTable = new MyTable();
        myTable.setOnSelectionChanged(myEditor::selectShape);
        myTable.setOnItemDeleted(myEditor::deleteShape);
    }

    private void initEditor() {
        myEditor = MyEditor.getInstance();
        myEditor.setGc(canvas.getGraphicsContext2D());
    }
}
