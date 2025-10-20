import Myclass.*;
import DataBase.*;
import java.util.*;
import java.io.*;
import java.lang.*;
/**
* 여기에 LibraryManagementSystem 클래스 설명을 작성하십시오.
* Constructor생성, Book,User Data 저장, Book 대출 현황 DB에 데이터 저장
* @author (2022320035_배우준) 
* @version (25.10.20)
*/
public class LibraryManagementSystem{
    LibDB<Book> bookDB;
    LibDB<User> userDB;
    HashMap<User, Book> loanDB;//HashMap 이용
    
    //Constructor 생성
    public LibraryManagementSystem(){
        bookDB = new LibDB<Book>();
        userDB = new LibDB<User>();
        loanDB = new HashMap<User, Book>();
    }
    
    //setUserDB: Temp 파일에서 UserData2025.txt 데이터 읽어 DB 저장
    public LibDB<User> setUserDB(String userFile){
            try{
            // .txt파일을 입력 스트림으로 입력 받는 작업
            FileReader fr = new FileReader("C:\\Temp\\UserData2025.txt");
            Scanner scan = new Scanner(fr);
    
            for ( ; scan.hasNextLine() ; ){
                String line = scan.nextLine();
                if (line.isEmpty()) continue;
                // "/"로 단어 구분, 토큰화 진행
                String[] token = line.split("/");
                if (token.length >= 2) {
                    int id = Integer.parseInt(token[0]);
                    String name = token[1];
                    User u = new User(name, id);
                    userDB.addElement(u);
                }
            }
            scan.close();//입력 스트림이 끝나면 반드시 Close 하기
            fr.close();//입력 스트림이 끝나면 반드시 Close 하기
        } 
        //예외 상황 출력
        catch (IOException e){
            System.out.println("입출력 오류: " + e.getMessage());
        }
        return userDB;
    }
    
    //setBookDB: Temp 파일에서 BookData2025.txt 데이터 읽어 DB 저장
    public LibDB<Book> setBookDB(String bookFile) {
        try (FileReader fr = new FileReader("C:\\Temp\\BookData2025.txt");
        Scanner scan = new Scanner(fr)){
        for ( ; scan.hasNextLine(); ) {
            String line = scan.nextLine();//Data를 한줄씩 읽는 명령어
            if (line.isEmpty()) continue;
            // "/"로 단어 구분, 토큰화 진행
            String[] token = line.split("/");
            if (token.length >= 5) {
                String id = token[0];
                String title = token[1];
                String author = token[2];
                String pub = token[3];
                int year = Integer.parseInt(token[4]);
                Book b = new Book(id, title, author, pub, year);
                bookDB.addElement(b);
            }
        }
        } catch (IOException e){
    System.out.println("입출력 오류: " + e.getMessage());
    }
    return bookDB;
    }
            
    //printDB: 데이터베이스 출력
    public <T extends DB_Element> void printDB(LibDB<T> db) {
        ArrayList<T> list = db.getAllElements();  
        for (T element : list) {
        System.out.println(element.toString());
        }
    }
    
    // borrowBook: userID/bookID 기반으로 대출 등록
    public void borrowBook(String userID, String bookID){
        ArrayList<User> uList = userDB.getAllElements();
        ArrayList<Book> bList = bookDB.getAllElements();
        
        User foundUser = null;
        Book foundBook = null;
    
        for (User u : userDB.getAllElements())
            if (u.getID().equals(userID)){
            foundUser = u; break;}
    
        for (Book b : bookDB.getAllElements())
            if (b.getID().equals(bookID)){
            foundBook = b; break;}
    
        if (foundUser != null && foundBook != null)
            loanDB.put(foundUser, foundBook);
        else
            System.out.println("대출 실패");
    }
    
    //printLoanList: 대출 현황 출력
    public void printLoanList() {
        System.out.println("----- 대출 현황 -----");
        for (User u : loanDB.keySet()) {
            Book b = loanDB.get(u);
            //user + book Data 화면에 출력
            System.out.println(u.toString() + " ===> " + b.toString());
        }
        System.out.println("--------------------------------------");
    }
}
    
    
    
