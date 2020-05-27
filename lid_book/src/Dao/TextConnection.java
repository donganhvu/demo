package Dao;

import model.Book;
import Exception.AppException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextConnection {
    private static File FILE_NAME = new File("Book.txt");

    /**
     * Write to text
     *
     * @param list list of Book
     * @throws AppException
     */
    public void writeToText(List<Book> list) throws AppException {
        FileOutputStream fos = null;
        ObjectOutputStream ous = null;
        try {
            fos = new FileOutputStream(FILE_NAME);
            ous = new ObjectOutputStream(fos);
            ous.writeObject(list);
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        }
    }

    /**
     * Read from text
     *
     * @return List of book
     * @throws AppException Throw when raise FileNotFoundException, IOException ,ClassNotFoundException
     */
    public List<Book> readFromText() throws AppException {
        List<Book> list;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILE_NAME);
            ois = new ObjectInputStream(fis);
            list = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new AppException(e.getMessage());
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new AppException(e.getMessage());
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
        return list;
    }

    /**
     * close input stream
     *
     * @param is: input stream
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * close output stream
     *
     * @param os: output stream
     */
    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
