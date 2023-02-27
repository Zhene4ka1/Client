module ru.bip.client1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires static lombok;
    requires okhttp3;
    requires annotations;

    exports ru.bip.client1;
    exports ru.bip.client1.controller;
    exports ru.bip.client1.entity;
    exports ru.bip.client1.response;
    exports ru.bip.client1.utils;
    opens ru.bip.client1 to javafx.fxml;
    opens ru.bip.client1.controller to javafx.fxml;
    opens ru.bip.client1.entity to com.google.gson;

}