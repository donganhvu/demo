package Controller;

import java.util.*;
import java.util.regex.Pattern;

import Dao.TextConnection;
import Exception.AppException;
import model.Book;
import org.w3c.dom.ls.LSOutput;

/**
 * Book Manager Class
 */
public class BookManager {

    private static BookManager bookManager = new BookManager();

    private BookManager() {
    }

    /**
     * @return Book Manager Instance
     */
    public static BookManager getInstance() {
        if (bookManager == null) {
            bookManager = new BookManager();
        }
        return bookManager;
    }

    /**
     * connection to text file
     */
    TextConnection dbConnection = new TextConnection();
    /**
     * book List
     */
    List<Book> bookList = new ArrayList<>();
    /**
     * Scanner
     */
    Scanner scn = new Scanner(System.in);

    
    
    private static void showAllBook(Book book) {
        System.out.format("%5s | ", book.getUuid());
        System.out.format("%10s | ", book.getTitle());
        System.out.format("%5s | ", book.getAuthorName());
        System.out.format("%10s | ", book.getStatus());
        System.out.println("\n");
    }

    
    
    public String inputAuthorName() {
    
       System.out.print("Please input your author name : ");
       String a = scn.nextLine();
       while(!Pattern.matches("^(?=.*[a-zA-Z])(?=.*[a-z]).{1,80}$",a)){
    	   System.out.print("Please retype your author name : ");
    	   a = scn.nextLine();   
       }
        return a;

    }

    
    
    public String inputTitle() {
        System.out.println();
        System.out.println("Please input your book title: ");
        String a = scn.nextLine();
        while(!Pattern.matches("^(?=.*[a-zA-Z])(?=.*[a-z]).{1,80}$",a)){
     	   System.out.print("Please retype your book title: ");
     	   a = scn.nextLine();   
        }
         return a;

    }

    
    
    public Integer inputStatus() {
        System.out.print("Please input book status(1-active,2-inactive): ");
        String a = scn.nextLine();
        while(!Pattern.matches("^(?=.*[1|2]).{1}$",a)){
     	   System.out.print("Please retype book status(1-active,2-inactive): ");
     	   a = scn.nextLine();   
        }
         return Integer.parseInt(a);
    }

    
    
    public UUID inputId() {
        System.out.print("Please input your book ID : ");
        String a = scn.nextLine();
        while(!Pattern.matches("^(?=.*[a-zA-Z])(?=.*[a-z]).{10,80}$",a)){
     	   System.out.print("Please retype book ID: ");
     	   a = scn.nextLine();   
        }
        return UUID.fromString(a);

        
        
    }

    
    
    public void getAllBookFromDb() throws AppException {
        List<Book> list;
        list = dbConnection.readFromText();
        list.forEach(BookManager::showAllBook);
    }
    
    
    
    public void getAllBookFromDbbyOrder() throws AppException {
        List<Book> list;
        
        list = dbConnection.readFromText();
        
        List<String> arr = new ArrayList<String>(50);
        for(int i = 0; i<list.size();i++) {
        	arr.add(list.get(i).getTitle());
        }
        Collections.sort(arr);
        for(int i = 0; i<arr.size();i++) {
        	for(int j = 0; j<list.size();j++) {
        		if(arr.get(i)==list.get(j).getTitle()) {
        			System.out.format("%5s | ", list.get(j).getUuid());
        	        System.out.format("%10s | ", list.get(j).getTitle());
        	        System.out.format("%5s | ", list.get(j).getAuthorName());
        	        System.out.format("%10s | ", list.get(j).getStatus());
        	        System.out.println("\n");		
        		}  		
        	}
       }
       //System.out.print(arr);
       //list.forEach(BookManager::showAllBook);
    }

    
    
    public void getBookById(UUID uuid) throws AppException {
        List<Book> list;
        list = dbConnection.readFromText();
        Optional<Book> book1 = list.stream().filter(book -> book.getUuid().equals(uuid)).findFirst();
        if (!book1.isPresent()) {
            System.out.println(
                    "Book Doesn't exist"
            );
        } else {
            showAllBook(book1.get());
        }

    }

    
    
    public void deleteBookById(UUID uuid) throws AppException {
        List<Book> list;
        list = dbConnection.readFromText();
        Optional<Book> book1 = list.stream().filter(book -> book.getUuid().equals(uuid)).findFirst();
        if (!book1.isPresent()) {
            System.out.println(
                    "Book Doesn't exist"
            );
        } else {
            deleteBookInDb(book1, list); 
        }
    }

    
    
    private void deleteBookInDb(Optional<Book> book1, List<Book> list) throws AppException {
        if (book1.isPresent()) {
            list.remove(book1.get());
            dbConnection.writeToText(list);
            System.out.println("Delete Successfully");
        } else {
            System.out.println("Delete failed");
        }
    }

    
    
    public void addToDb() throws AppException {
        Book book = new Book();
        UUID id = UUID.randomUUID();
        book.setUuid(id);
        book.setAuthorName(bookManager.inputAuthorName());
        book.setTitle(bookManager.inputTitle());
        book.setStatus(bookManager.inputStatus());
        System.out.println(book.toString());
        bookList.add(book);
        System.out.println(Arrays.toString(bookList.toArray()));
        dbConnection.writeToText(bookList);
        System.out.println();
        System.out.println("Add new Book successfully");
        System.out.println();
    }
    
    
    
    public void updateBookById(UUID uuid) throws AppException{
        List<Book> list;
        list = dbConnection.readFromText();
        Optional<Book> book1 = list.stream().filter(book -> book.getUuid().equals(uuid)).findFirst();
        if (!book1.isPresent()) {
            System.out.println(
                    "Book Doesn't exist"
            );
        } else {
                list.forEach(book -> {
                    if(book.getUuid().equals(uuid)){
                        book.setTitle(inputTitle());
                        book.setAuthorName(inputAuthorName());
                        book.setStatus(inputStatus());
                    }
                    try {
                        dbConnection.writeToText(list);
                    } catch (AppException e) {
                        e.printStackTrace();
                    }
                });
        }

    }
}
