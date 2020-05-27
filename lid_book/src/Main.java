import Controller.BookManager;
import Exception.AppException;
import model.Book;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws AppException {
        Scanner scn = new Scanner(System.in);
        String choose;
        int flag ;

        BookManager controller = BookManager.getInstance();
        // show main menu
        showMenu();
        do {
            flag = 1;
            
            choose = scn.nextLine();
            
            
            
            while(!Pattern.matches("^(?=.*[0-9]).{1}$",choose)){
          	   System.out.print("Please retype you selection ");
          	   choose = scn.nextLine();   
             }
            
            switch (Integer.parseInt(choose)) {
                case 1:
                    controller.addToDb();
                    break;
                case 2:
                    controller.getAllBookFromDb();
                    break;
                case 3:
                    controller.getBookById(controller.inputId());
                    break;
                case 4:
                    controller.updateBookById(controller.inputId());
                    break;
                case 5:
                    controller.deleteBookById(controller.inputId());
                    break;
                case 6:
                    controller.getAllBookFromDbbyOrder();
                    break;
                case 7:
                    System.out.println("exited");
                    flag = 2;
                    break;
                default:
                    System.out.println("default");
                    break;
            }
            if(flag == 2){
                break;
            }
            showMenu();
        }while (flag == 1);

    }
    /**
     * create menu
     */
    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Add Book.");
        System.out.println("2. Show All Book");
        System.out.println("3. Get Book By Id. ");
        System.out.println("4. Edit Book By id.");
        System.out.println("5. Delete Book By Id");
        System.out.println("6. Show All Book by Name");
        System.out.println("7. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }

}
