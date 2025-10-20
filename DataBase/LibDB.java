package DataBase;
import Myclass.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * 여기에 LibDB 클래스 설명을 작성하십시오.
 * 제네릭 클래스로 생성, 반복 작업을 위한 Iterator, for each문 사용
 * @author (2022320035)_배우준) 
 * @version (25.10.20)
 */
public class LibDB<T extends DB_Element>
{
    private ArrayList<T> db;

    public LibDB() {
        this.db = new ArrayList<T>();
    }

    public void addElement(T element) {
        db.add(element);
    }

    public String findElement(String s) {
        Iterator<T> it = db.iterator();
        while (it.hasNext()) {
            T element = it.next();
            if (element.getID().equals(s)) {
                return element.toString();
            }
        }
        return "Element with ID " + s + " not found.";
    }

    public void printAllElements() {
        for (T element : db) {
            System.out.println(element);
        }
        System.out.println("--------------------------------");
    }
    
    public ArrayList<T> getAllElements(){ 
        return db; 
    }

}
