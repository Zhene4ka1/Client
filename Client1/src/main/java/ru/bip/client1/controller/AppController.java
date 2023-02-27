package ru.bip.client1.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import ru.bip.client1.Application;
import ru.bip.client1.entity.BookEntity;
import ru.bip.client1.response.BaseResponse;
import ru.bip.client1.response.BookListResponse;
import ru.bip.client1.utils.HTTPUtils;

import java.io.IOException;

public class AppController {
    public static String api = "http:/localhost:200/api/v1/book/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    @Setter
    @FXML
    public TableView<BookEntity> tableBooks;
    @FXML
    public TableColumn<BookEntity, Integer> bookId;
    @FXML
    public TableColumn<BookEntity, String> bookTitle;
    @FXML
    public TableColumn<BookEntity, String> bookAuthor;
    @FXML
    public TableColumn<BookEntity, String> bookPublisher;
    @FXML
    public TableColumn<BookEntity, String> bookYear;
    @FXML
    public TableColumn<BookEntity, String> bookKind;


    @FXML
    private void initialize() throws IOException {
        getData();
        updateTable();
    }

    private void updateTable(){
        bookTitle.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookKind.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("kind"));
        tableBooks.setItems(booksData);
    }

    @FXML
    private void add() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        Application.showPersonEditDialog(tempBook, booksData.size() - 1);
        addBook(tempBook);
    }

    @FXML
    private void delete() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            deleteBook(selectedBook);
            booksData.remove(selectedBook);
            //AppController.saveDB(booksData);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            //alert.initOwner(Application.getPrimaryStage());
            alert.setHeaderText("Ошибка");
            alert.setContentText("Ошибка");
            alert.showAndWait();
        }
    }

    @FXML
    private void dublicate() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            addBook(selectedBook);
            booksData.add(booksData.indexOf(selectedBook)+1, selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            // alert.initOwner(Application.getPrimaryStage());
            alert.setHeaderText("Ошибка");
            alert.setContentText("Ошибка");
            alert.showAndWait();
        }
    }

    @FXML
    private void edit() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Application.showPersonEditDialog(selectedBook, booksData.indexOf(selectedBook));
            updateBook(selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            //alert.initOwner(Application.getPrimaryStage());
            alert.setHeaderText("Ошибка");
            alert.setContentText("Ошибка");
            alert.showAndWait();
        }
    }


    public static void getData() throws IOException {
        String res = http.get(api, "all");

        JsonObject base = gson.fromJson(res, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void addBook(BookEntity book) throws IOException {
        System.out.println(book.toString());
        book.setId(null);
        http.post(api + "add", gson.toJson(book).toString());
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(api + "update", gson.toJson(book).toString());
    }

    public static void deleteBook(BookEntity book) throws IOException {
        http.delete(api, book.getId());
    }
}