package Controller;

import Dao.TextConnection;
import model.Book;
import model.BookManager;
import java.util.List;
public class Controller {
    BookManager bookManager = BookManager.getInstance();
    TextConnection dbConnection;
    List<Book> bookList;


}
