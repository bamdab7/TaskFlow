module com.taskflow.taskflow {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.taskflow.taskflow to javafx.fxml;
    exports com.taskflow.taskflow;
}