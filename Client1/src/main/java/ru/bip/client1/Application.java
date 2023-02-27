package ru.bip.client1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.bip.client1.controller.AppController;
import ru.bip.client1.controller.EditController;
import ru.bip.client1.entity.BookEntity;

import java.io.IOException;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/Library.fxml"));
            AnchorPane anchor = (AnchorPane) loader.load();

            Scene scene = new Scene(anchor);
            primaryStage.setScene(scene);

            AppController controller = loader.getController();
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean showPersonEditDialog(BookEntity bookObj, int id){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/editBook.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            EditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj,id);

            dialogStage.showAndWait();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}