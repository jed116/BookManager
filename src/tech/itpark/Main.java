package tech.itpark;

import tech.itpark.manager.BookManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookManager bookManager = new BookManager();
        bookManager.importFile("Books.txt");
        bookManager.exportFile("Export.txt");
    }
}
