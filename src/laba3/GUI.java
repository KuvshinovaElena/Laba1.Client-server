package laba3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laba1.Book;
import laba1.DataServer;

import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


/**
 * Created by mvideo on 25.04.2015.
 */
public class GUI extends Application{
    Stage stage;
    static ArrayList<String> records;
    private DataServer clientService;
    private Registry clientRegistry;
    private ObservableList<Book> data = FXCollections.observableArrayList();

    public void init(final Stage primaryStage) throws IOException, NotBoundException {
        final Group root = new Group();
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

            final Button add = new Button("ADD");
            add.setPrefWidth(248);
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
            remove.setPrefWidth(248);
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
            edit.setPrefWidth(248);
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

            final TextField text = new TextField();
            text.setMinWidth(300);
            text.setPromptText("Search");

        Button back = new Button("GO BACK");
        back.setPrefWidth(170);
        back.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");

        back.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent event)
            {
                try
                {
                    text.clear();
                    tableView.setItems(data);
                } catch (NullPointerException e)
                {
                }
            }
        });

            final Button search1 = new Button("SEARCH BY ARTICLE");
            search1.setPrefWidth(150);
            search1.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            search1.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle (ActionEvent event)
                {

                    String article = MyScene.checkEnter(text);
                    if (article != null)
                    {
                        ObservableList<Book> findArticle = FXCollections.observableArrayList();
                        try
                        {
                            findArticle.addAll(clientService.findByArticle(article));
                        } catch (RemoteException e)
                        {
                            e.printStackTrace();
                        }
                        tableView.setItems(findArticle);
                        add.setCancelButton(true);
                    }

                }
            });
            search1.setCancelButton(false);
            Button search2 = new Button("SEARCH BY AUTOR");
            search2.setPrefWidth(150);
            search2.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            search2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    String autor = MyScene.checkEnter(text);
                    if (autor!=null)
                    {
                        ObservableList<Book> findAutor = FXCollections.observableArrayList();
                        try
                        {
                            findAutor.addAll(clientService.findByAutor(autor));
                        } catch (RemoteException e)
                        {
                            e.printStackTrace();
                        }
                        tableView.setItems(findAutor);
                    }
                }
            });


            Button search3 = new Button("SEARCH BY TITLE");
            search3.setPrefWidth(150);
            search3.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            search3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    String title = MyScene.checkEnter(text);
                    if (title!=null)
                    {
                        ObservableList<Book> findTitle = FXCollections.observableArrayList();
                        try
                        {
                            findTitle.addAll(clientService.findByTitle(title));
                        } catch (RemoteException e)
                        {
                            e.printStackTrace();
                        }
                        tableView.setItems(findTitle);
                    }
                }
            });


            Button search4 = new Button("SEARCH BY QUANTITY");
            search4.setPrefWidth(150);
            search4.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
            search4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent event) {
                    int quantity = MyScene.checkInputInt(text);
                    if (quantity!=-1)
                    {
                        ObservableList<Book> findQuantity = FXCollections.observableArrayList();
                        try
                        {
                            findQuantity.addAll(clientService.findByQuantity(quantity));
                        } catch (RemoteException e)
                        {
                            e.printStackTrace();
                        }
                        tableView.setItems(findQuantity);
                    }
                }
            });


        Button search5 = new Button("SEARCH BY PRICE");
        search5.setPrefWidth(150);
        search5.setStyle("-fx-text-fill:white; -fx-base: rgb(40,155,220);");
        search5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                int price = MyScene.checkInputInt(text);
                if (price!=-1)
                {
                    ObservableList<Book> findPrice = FXCollections.observableArrayList();
                    try
                    {
                        findPrice.addAll(clientService.findByPrice(price));
                    } catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                    tableView.setItems(findPrice);
                }
            }
        });



            hBox.getChildren().add(add);
            hBox.setSpacing(5);
            hBox.getChildren().add(remove);
            hBox.setSpacing(5);
            hBox.getChildren().add(edit);
            hBox.setSpacing(5);
            hBox.getChildren().add(search1);
            hBox.setSpacing(5);

            VBox vBox1 = new VBox(5);
            HBox hBox1 = new HBox(5);
            HBox hBox2 = new HBox(1);
            hBox1.getChildren().add(text);
            hBox2.getChildren().addAll(search1,search2,search3,search4,search5);
            vBox1.getChildren().addAll(hBox,hBox1,hBox2,back);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView,vBox1);
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
