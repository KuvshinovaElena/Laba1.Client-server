package laba1;

/**
 * Created by Елена on 14.03.2015.
 */
public class Book
{
    private String autor;
    private String title;
    private String genre;
    private int quantity;
    private double price;

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
    public void setGenre (String genre)
    {
        this.genre=genre;
    }
    public String getGenre ()
    {
        return genre;
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
