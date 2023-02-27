package ru.bip.client1.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.bip.client1.Application;
import ru.bip.client1.entity.BookEntity;

import java.io.IOException;

import static ru.bip.client1.controller.AppController.booksData;
import static ru.bip.client1.controller.AppController.updateBook;

public class EditController {

    @FXML
    private TextField bookTitle_f;
    @FXML
    private TextField bookAuthor_f;
    @FXML
    private TextField bookPublisher_f;
    @FXML
    private TextField bookYear_f;
    @FXML
    private TextField bookKind_f;


    private Stage editBookStage;
    private BookEntity book;
    private int bookID;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage){this.editBookStage = dialogStage;}

    public boolean isOkClicked() {
        return okClicked;
    }


    public void setLabels(BookEntity bookIn, int book_id){
        this.book = bookIn;
        this.bookID = book_id;

        bookTitle_f.setText(book.getTitle());
        bookAuthor_f.setText(book.getAuthor());
        bookPublisher_f.setText(book.getPublisher());
        bookYear_f.setText(book.getYear());
        bookKind_f.setText(book.getKind());
    }

    @FXML
    private void handleOk() throws IOException{
        if (isInputValid()){
            book.setTitle(bookTitle_f.getText());
            book.setAuthor(bookAuthor_f.getText());
            book.setPublisher(bookPublisher_f.getText());
            book.setYear(bookYear_f.getText());
            book.setKind(bookKind_f.getText());

            okClicked = true;
            editBookStage.close();
            booksData.set(bookID,book);
            updateBook(book);
        }
    }

    @FXML
    private void handleCancel(){
        editBookStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bookTitle_f.getText() == null || bookTitle_f.getText().length() == 0)
            errorMessage += "Не обнаружено название книги!\n";
        if (bookAuthor_f.getText() == null || bookAuthor_f.getText().length() == 0)
            errorMessage += "Не обнаружен автор книги!\n";
        if (bookPublisher_f.getText() == null || bookPublisher_f.getText().length() == 0)
            errorMessage += "Не обнаружено издательство книги!\n";
        if (bookYear_f.getText() == null || bookYear_f.getText().length() == 0)
            errorMessage += "Не обнаружен год издания книги!\n";
        else {
            try {
                Integer.parseInt(bookYear_f.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Значение года издания книги неверно!(должно быть целочисленным)\n";
            }
        }
        if (bookKind_f.getText() == null || bookKind_f.getText().length() == 0)
            errorMessage += "Не обнаружен тип книги!\n";


        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editBookStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Укажите корректные значения!");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
