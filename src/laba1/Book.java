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
    public boolean equals(Object obj) {
        Book other = (Book) obj;

        if (this.getArticle().equals(other.getArticle())) {
            if (this.getAutor() == other.getAutor()) {
                if (this.getTitle() == other.getTitle()) {
                    if (this.getQuantity() == other.getQuantity()) {
                        if (this.getPrice() == other.getPrice()) {
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
