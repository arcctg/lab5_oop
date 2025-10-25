module org.arcctg.lab4_oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires static lombok;

    opens org.arcctg.lab5_oop to javafx.fxml;

    exports org.arcctg.lab5_oop;
    exports org.arcctg.lab5_oop.shapes;
}