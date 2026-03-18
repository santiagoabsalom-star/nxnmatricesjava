module com.surrogate.nxnmatrices {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.surrogate.nxnmatrices to javafx.fxml;
    exports com.surrogate.nxnmatrices;
}