package laba3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laba1.Book;
import laba1.ClientImplement;
import laba1.DataServer;
import laba1.ServerImplement;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by mvideo on 25.04.2015.
 */
public class GUI extends Application{
        private ObservableList<Object> data = FXCollections.observableArrayList();
        private ArrayList<Book> books;
        private DataServer server;
        TextField text;
        Stage stage;

        private void init(Stage primaryStage) throws IOException {
            Group root = new Group();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("BOOK DATABASE 2015");
            data.addAll(server.getAll());
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

            TableView tableView = new TableView();
            tableView.setItems(data);
            tableView.getColumns().addAll(articleCol, autorCol, titleCol, quantityCol, priceCol);
            root.getChildren().add(tableView);
            HBox hBox = new HBox();

            Button add = new Button("Add");
            add.setPrefWidth(170);
            add.setStyle("-fx-base: rgb(40,155,220);");
            Button remove = new Button("Remove");
            remove.setPrefWidth(170);
            remove.setStyle("-fx-base: rgb(40,155,220);");
            Button edit = new Button("  Edit  ");
            edit.setPrefWidth(170);
            edit.setStyle("-fx-base: rgb(40,155,220);");
            Button search = new Button("Search");
            search.setPrefWidth(170);
            search.setStyle("-fx-base: rgb(40,155,220);");

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
