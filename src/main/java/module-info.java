module org.example.proj {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.media;


    opens org.example.proj to javafx.fxml;
    exports org.example.proj;
    exports logic;
    opens logic to javafx.fxml;
}