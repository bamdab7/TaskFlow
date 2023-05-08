module com.taskflow.taskflow {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.taskflow.taskflow to javafx.fxml;
    exports com.taskflow.taskflow;
}