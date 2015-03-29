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
        art,
        educational
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
    public void setGenre (Genre genre)
    {
        this.genre=genre;
    }
    public Genre getGenre ()
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
