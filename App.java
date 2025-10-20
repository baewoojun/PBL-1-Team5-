import Myclass.*;
import DataBase.*;
/**
 * 여기에 App 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class App
{
    public static void main(String[] args) {
        // LibraryManagementSystem lbMS = new LibraryManagementSystem();

        // System.out.println("-------- 이용자 목록 출력 ---------");
        // lbMS.setUserDB("UserData2025.txt");
        // lbMS.printDB(lbMS.setUserDB("UserData2025.txt"));

        // System.out.println("\n-------- 책 목록 출력 ---------");
        // lbMS.setBookDB("BookData2025.txt");
        // lbMS.printDB(lbMS.setBookDB("BookData2025.txt"));

        // lbMS.borrowBook(2023320003, "B04");
        // lbMS.borrowBook(2025320001, "B02");
        // lbMS.borrowBook(2024320002, "B03");

        // lbMS.printLoanList();
        LibraryManagementSystem lbMS = new LibraryManagementSystem();

        //1 User DB 설정
        System.out.println("----- 이용자 목록 출력 -----");
        lbMS.setUserDB("UserData2025.txt");
        lbMS.printDB(lbMS.userDB);  // 중복 setUserDB 제거

        //2 Book DB 설정
        System.out.println("\n----- 책 목록 출력 -----");
        lbMS.setBookDB("BookData2025.txt");
        lbMS.printDB(lbMS.bookDB);

        //3 책 대출
        lbMS.borrowBook("2023320003", "B04");
        lbMS.borrowBook("2025320001", "B02");
        lbMS.borrowBook("2024320002", "B03");

        //4 대출 현황 출력
        lbMS.printLoanList();
    }
}

