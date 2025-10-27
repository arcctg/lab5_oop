package org.arcctg.lab5_oop;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        MainController controller = fxmlLoader.getController();
        stage.setOnCloseRequest(e -> controller.handleExit());
        controller.setStage(stage);

        stage.setTitle("Графічний редактор");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
