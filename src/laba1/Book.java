package laba1;

/**
 * Created by Елена on 14.03.2015.
 */
public class Book
{
    private String autor;
    private String title;
    private Genre genre;
    private int quantity;
    private double price;

    public enum Genre
    {
        fantasy,
        detective,
        nonfiction,
        thriller,
        child,
        classic,
        hobby,
        educational
    }
    /*----------------------------*/
    public String getAutor ()
    {
        return autor;
    }

    public void setAutor(String nameAutor)
    {
        nameAutor=autor;
    }
    /*-------------------------*/
    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String nameBook)
    {
        nameBook=title;
    }
    /*------------------------*/
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity (int num)
    {
        quantity=num;
    }
    /*-----------------------------*/
    public double getPrice()
    {
        return price;
    }

    public void setPrise (double coast)
    {
        price=coast;
    }
}
