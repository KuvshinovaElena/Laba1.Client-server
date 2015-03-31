package laba1;

import java.io.Serializable;
/**
 * Created by Елена on 14.03.2015.
 */
public class Book implements Serializable{

    private String article;     //у каждого элемента артикул индивидуален
    private String autor;
    private String title;
    private int quantity;
    private int price;

    Book (){
    }
     public void setAutor(String autor) {
        this.autor=autor;
    }
    public String getAutor () {
        return autor;
    }
    public void setTitle (String title) {
        this.title=title;
    }
    public String getTitle () {
        return title;
    }
    public void setArticle (String article) {
        this.article=article;
    }
    public String getArticle () {
        return article;
    }
    public void setQuantity (int quantity) {
        this.quantity=quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public void setPrise (int price) {
        this.price=price;
    }

    //Сравнивание двух объктов
    public boolean equals(Object obj) {
        Book dop = (Book) obj;

        if (this.getArticle().equals(dop.getArticle())) {
            if (this.getAutor() == dop.getAutor()) {
                if (this.getTitle() == dop.getTitle()) {
                    if (this.getQuantity() == dop.getQuantity()) {
                        if (this.getPrice() == dop.getPrice()) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}
