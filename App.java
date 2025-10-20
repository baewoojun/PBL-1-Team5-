import Myclass.*;
import DataBase.*;
/**
 * 여기에 App 클래스 설명을 작성하십시오.
 * lbMS를 통해 DB에 수집된 데이터를 시각화 하는 클래스
 * @author (2022320035_배우준) 
 * @version (2025.10.20)
 */
public class App
{
    public static void main(String[] args) {
        LibraryManagementSystem lbMS = new LibraryManagementSystem();

        //1 User DB 설정
        System.out.println("----- 이용자 목록 출력 -----");
        lbMS.setUserDB("UserData2025.txt");
        lbMS.printDB(lbMS.userDB);//userDB 데이터 출력 

        //2 Book DB 설정
        System.out.println("\n----- 책 목록 출력 -----");
        lbMS.setBookDB("BookData2025.txt");
        lbMS.printDB(lbMS.bookDB);//bookDB 데이터 출력

        //3 책 대출(요구사항 : borrowBook 3번 실행)
        lbMS.borrowBook("2023320003", "B04");
        lbMS.borrowBook("2025320001", "B02");
        lbMS.borrowBook("2024320002", "B03");

        //4 대출 현황 출력 (사용자 화면에 출력)
        lbMS.printLoanList();
    }
}

