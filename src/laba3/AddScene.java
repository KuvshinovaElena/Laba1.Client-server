package laba3;

import javafx.beans.NamedArg;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * Created by Елена on 09.05.2015.
 */
public class AddScene  extends Stage{
    TextField text;
    public AddScene(Stage primaryStage){
        try
        {
            start(primaryStage);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void init(final Stage primaryStage){
        Group root = new Group();
        primaryStage.setTitle("BOOK DATABASE 2015");

        Label label = new Label("Name");
        text = new TextField("Text");
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        root.getChildren().addAll(label,text);
        show();
    }
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
}
