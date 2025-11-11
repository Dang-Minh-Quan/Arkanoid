module Arkanoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;

    exports Interface;
    exports LogicGamePlay;

    opens Interface to javafx.fxml;
    exports Score;
}