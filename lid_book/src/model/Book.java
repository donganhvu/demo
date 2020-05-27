package model;

import java.io.Serializable;
import java.util.UUID;


/**
 *  Book class ( Simple POJO)
 */
public class Book implements Serializable {
    /**
     * uuid
     */
    private UUID uuid;
    /**
     * title of book
     */
    private String title;
    /**
     * author name
     */
    private String authorName;
    /**
     * status
     */
    private Integer status;

    /**
     * Constructor of all arguments
     * @param uuid
     * @param title
     * @param authorName
     * @param status
     */
    public Book(UUID uuid,String title,String authorName,Integer status){
        this.uuid = uuid;
        this.title = title;
        this.authorName = authorName;
        this.status = status;
    }

    public Book() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", status=" + status +
                '}';
    }
}
