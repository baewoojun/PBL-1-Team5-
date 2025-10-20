package Myclass;
/**
 * 여기에 Book 클래스 설명을 작성하십시오.
 * String 형식으로 BookID, title, author, publihser 속성 생성 
 * @author (2022320035_배우준) 
 * @version (25.10.20)
 */
public class Book extends DB_Element
{
    private String author;
    private String BookID;
    private String publisher;
    private String title;
    private int year;
    //컨스트럭터 생성 (파라메터 5개)
    public Book(String BookID, String title, String author, String publisher,
    int year)
    {
        this.author = author;
        this.BookID = BookID;
        this.publisher = publisher;
        this.title = title;
        this.year = year;
    }

    //getID()에서 BookID 값 리턴
    public String getID()
    {
        return BookID;
    }

    //오버라이딩 과정 (BookId, title, author, publisher,year 리턴)
    public String toString()
    {
        return"(" + BookID + ")"+ title +"," + author +","+ publisher +","+ year;
    }
}
