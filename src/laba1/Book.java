package laba1;

/**
 * Created by Елена on 14.03.2015.
 */
public class Book
{
    private String article;
    private String autor;
    private String title;
    private int quantity;
    private double price;

    Book (){
    }
    /*----------------------------*/
     public void setAutor(String autor)
    {
        this.autor=autor;
    }

    public String getAutor ()
    {
        return autor;
    }
    /*-------------------------*/
    public void setTitle (String title)
    {
        this.title=title;
    }

    public String getTitle ()
    {
        return title;
    }
    /*------------------------*/
    public void setArticle (String article)
    {
        this.article=article;
    }
    public String getArticle ()
    {
        return article;
    }
    /*--------------------------*/
    public void setQuantity (int quantity)
    {
        this.quantity=quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }
    /*-----------------------------*/
    public double getPrice()
    {
        return price;
    }

    public void setPrise (double price)
    {
        this.price=price;
    }
}
