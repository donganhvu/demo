package model;

/**
 * Book Manager Class
 */
public class BookManager {
    private static BookManager manager;

    /**
     *
     * @return Book Manager Instance
     */
    public static BookManager getInstance() {
        if (manager == null) {
            manager = new BookManager();
        }
        return manager;
    }
}
