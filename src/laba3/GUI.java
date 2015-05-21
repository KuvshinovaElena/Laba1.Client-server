package laba3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laba1.Book;
import laba1.ClientImplement;
import laba1.DataServer;
import laba1.ServerImplement;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by mvideo on 25.04.2015.
 */
public class GUI extends Application{
    private ObservableList<Object> data = FXCollections.observableArrayList();
    Stage stage;
    static ArrayList<String> records;
    private DataServer clientService;
    private Registry clientRegistry;

    private void init(final Stage primaryStage) throws IOException, NotBoundException {
            Group root = new Group();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("BOOK DATABASE 2015");

            clientRegistry = LocateRegistry.getRegistry(1099);
            String objectName = "rmi://localhost/book";
            clientService = (DataServer)clientRegistry.lookup(objectName);

            data.addAll(clientService.getAll());

            TableColumn articleCol = new TableColumn();
            articleCol.setText("Article");
            articleCol.setCellValueFactory(new PropertyValueFactory("article"));

            TableColumn autorCol = new TableColumn();
            autorCol.setText("Autor");
            autorCol.setMinWidth(150);
            autorCol.setCellValueFactory(new PropertyValueFactory("autor"));

            TableColumn titleCol = new TableColumn();
            titleCol.setText("Title");
            titleCol.setMinWidth(300);
            titleCol.setCellValueFactory(new PropertyValueFactory("title"));

            TableColumn quantityCol = new TableColumn();
            quantityCol.setText("Quantity");
            quantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));

            TableColumn priceCol = new TableColumn();
            priceCol.setText("Price");
            priceCol.setCellValueFactory(new PropertyValueFactory("Price"));

            final TableView tableView = new TableView();
            tableView.setItems(data);
            tableView.getColumns().addAll(articleCol, autorCol, titleCol, quantityCol, priceCol);

            root.getChildren().add(tableView);
            HBox hBox = new HBox();

            Button add = new Button("ADD");
            add.setPrefWidth(170);
            add.setStyle("-fx-text-fill: white;-fx-base: rgb(40,155,220);");
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(stage != null) {
                        stage.close();
                    }
                    stage = new AddScene(data,clientService);
                    stage.setTitle("ADD IN DATABASE");
                }
            });

            Button remove = new Button("REMOVE");
            remove.setPrefWidth(170);
            remove.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Book book = (Book)tableView.getSelectionModel().getSelectedItem();
                    try {
                        clientService.delTheArticle(book.getArticle());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    data.remove(book);
                }
            });

            Button edit = new Button("EDIT");
            edit.setPrefWidth(170);
            edit.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Group root = new Group();
                    try {
                        Book book = (Book)tableView.getSelectionModel().getSelectedItem();
                        stage = new EditScene(root, data, clientService, book);
                        stage.setTitle("EDITING DATABASE");
                        tableView.getSelectionModel().clearSelection();
                    }catch (NullPointerException e){
                    }
                }
            });

            Button search = new Button("SEARCH");
            search.setPrefWidth(170);
            search.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");


            hBox.getChildren().add(add);
            hBox.setSpacing(5);
            hBox.getChildren().add(remove);
            hBox.setSpacing(5);
            hBox.getChildren().add(edit);
            hBox.setSpacing(5);
            hBox.getChildren().add(search);
            hBox.setSpacing(5);
            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView, hBox);
            root.getChildren().add(vBox);
        }
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) {
            launch(args);
        }
}
