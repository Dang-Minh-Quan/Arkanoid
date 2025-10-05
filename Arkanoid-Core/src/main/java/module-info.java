module org.example.arkanoidcore {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
  requires javafx.graphics;
  requires jdk.compiler;
  requires java.desktop;

  opens org.example.arkanoidcore to javafx.fxml;
    exports org.example.arkanoidcore;

  exports Interface;
  opens Interface to javafx.fxml;
}