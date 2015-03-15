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
     public void setAutor(String nameAutor)
    {
        autor=nameAutor;
    }

    public String getAutor ()
    {
        return autor;
    }
    /*-------------------------*/
    public void setTitle (String nameBook)
    {
        title=nameBook;
    }

    public String getTitle ()
    {
        return title;
    }
    /*------------------------*/
    public void setGenre (Genre nameGenre)
    {
        genre=nameGenre;
    }
    public Genre getGenre ()
    {
        return genre;
    }
    /*--------------------------*/
    public void setQuantity (int num)
    {
        quantity=num;
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

    public void setPrise (double coast)
    {
        price=coast;
    }
}
