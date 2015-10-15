package laba3;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import laba1.Book;
import laba1.EventBase;
import laba1.InputThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Елена on 16.05.2015.
 */
public class MyScene extends Stage {
    public static int checkInputInt(TextField text){
        if (text.getText().isEmpty()){
            text.setStyle("-fx-opacity: 0.5; -fx-background-color: red;");
            text.setPromptText("Field must be filled");
            return -1;
        }
        int num;
        try {
            num = Integer.parseInt(text.getText());
        } catch (NumberFormatException ex) {
            text.clear();
            text.setStyle("-fx-opacity: 0.5; -fx-background-color: red");
            text.setPromptText("Error! Enter an integer!");
            return -1;
        }
        if (num < 0){
            text.clear();
            text.setStyle("-fx-opacity: 0.5; -fx-background-color: red");
            text.setPromptText("Error! Enter a positive integer");
            return -1;
        }
        return num;
    }

    public static String checkArticle (String article,TextField newarticle, final ObjectOutputStream oos, final InputThread input) throws RemoteException {
       List<String> message = EventBase.codingMessages(EventBase.FIND, article);
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String reply = null;
        try {

            reply = (String) input.getInputStream().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (reply == "0")
            return newarticle.getText();
        else {
            newarticle.clear();
            newarticle.setStyle("-fx-opacity: 0.5; -fx-background-color: red");
            newarticle.setPromptText("This article is already in the database.");
            return null;
        }
    }

/*    public static String checkArticle (String article,TextField newarticle, ObjectOutputStream oos, ObjectInputStream ois) throws RemoteException {
        String str1,str2;
        if (article!=null) {
            str1 = article;
        }
        else
            str1=null;
        str2 = newarticle.getText();
        List<String> findList = EventBase.codingMessages(EventBase.GET_LIST,null,null);
        try {
            GUI.connect(EventBase.GET_LIST, findList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Book> find = EventBase.decodingMessages(findList);
        if (find.isEmpty())
            return str2;
        else {
            newarticle.clear();
            newarticle.setStyle("-fx-opacity: 0.5; -fx-background-color: red");
            newarticle.setPromptText("This article is already in the database.");
            return null;
        }
    }*/

    public static String checkEnter (TextField text){
        String str;
        if (!(str = text.getText()).isEmpty())
            return str;
        else {
            text.setStyle("-fx-opacity: 0.5; -fx-background-color: red");
            text.setPromptText("Field must be filled");
            return null;
        }
    }
}
