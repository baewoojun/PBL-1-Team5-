import Myclass.*;
import DataBase.*;
import java.util.*;
import java.io.*;
/**
 * 여기에 LibraryManagementSystem 클래스 설명을 작성하십시오.
 * Constructor생성, Book,User Data 저장, Book 대출 현황 DB에 데이터 저장
 * @author (2022320035_배우준) 
 * @version (25.10.20)
 */

public class LibraryManagementSystem
{
    LibDB<Book> bookDB;
    LibDB<User> userDB;
    HashMap<User, Book> loanDB;//HashMap 이용

    //Constructor 생성
    public LibraryManagementSystem() {
        bookDB = new LibDB<Book>();
        userDB = new LibDB<User>();
        loanDB = new HashMap<User, Book>();
    }

    //setUserDB: Temp 파일에서 UserData2025.txt 데이터 읽어 DB 저장
    public LibDB<User> setUserDB(String userFile) {
        try {
            //1 .txt파일 입력 스트림으로 입력
            FileReader fr = new FileReader("C:\\Temp\\UserData2025.txt");
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isEmpty()) continue;
                String[] token = line.split("/");
                if (token.length >= 2) {
                    int id = Integer.parseInt(token[0]);
                    String name = token[1];
                    User u = new User(name, id);
                    userDB.addElement(u);
                }
            }

            scan.close();
            fr.close();
        } 
        catch (IOException e) {
            System.out.println("입출력 오류: " + e.getMessage());
        }
        return userDB;
    }

    //setBookDB: Temp 파일에서 BookData2025.txt 데이터 읽어 DB 저장
    public LibDB<Book> setBookDB(String bookFile) {
        try {
            //1 .txt 파일 입력 스트림으로 입력
            FileReader fr = new FileReader("C:\\Temp\\BookData2025.txt");
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isEmpty()) continue;
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

            scan.close();
            fr.close();
        } 
        catch (IOException e) {
            System.out.println("입출력 오류: " + e.getMessage());
        }
        return bookDB;
    }

    //printDB: 데이터베이스 출력
    public <T extends DB_Element> void printDB(LibDB<T> db) {
        ArrayList<T> list = db.getAllElements();  
        // LibDB 내부에 public getAllElements() 추가 필요

        if (list.size() == 0) return;

        if (list.get(0) instanceof User) {
            for (int i = 0; i < list.size(); i++) {
                User u = (User) list.get(i);
                System.out.println("[" + u.getID() + "] " + u.toString());
            }
        } 
        else if (list.get(0) instanceof Book) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }

    //borrowBook: userID/bookID 기반으로 대출 등록
    public void borrowBook(String userID, String bookID) {
        ArrayList<User> uList = userDB.getAllElements();
        ArrayList<Book> bList = bookDB.getAllElements();

        User foundUser = null;
        Book foundBook = null;
        for (int i = 0; i < uList.size(); i++) {
            if (uList.get(i).getID().equals(userID)) {
                foundUser = uList.get(i);
                break;
            }
        }

        for (int i = 0; i < bList.size(); i++) {
            if (bList.get(i).getID().equals(bookID)) {
                foundBook = bList.get(i);
                break;
            }
        }

        if (foundUser != null && foundBook != null) {
            loanDB.put(foundUser, foundBook);
        }
    }

    //printLoanList: 대출 현황 출력
    public void printLoanList() {
        System.out.println("----- 대출 현황 -----");
        for (User u : loanDB.keySet()) {
            Book b = loanDB.get(u);
            System.out.println("[" + u.getID() + "] " + u.toString()
                    + " ==> (" + b.getID() + ") " + b.toString());
        }
        System.out.println("--------------------------------------");
    }
}



