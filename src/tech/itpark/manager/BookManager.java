package tech.itpark.manager;

import tech.itpark.model.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> items = new ArrayList<Book>();
    private int nextId = 1;

    public Book getBookById(int id){
        for (Book item : items) {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    public void save(Book item) {
        if (item.getId() == 0) {
            item.setId(nextId);
            nextId++;
            items.add(item);
        }
        else{
            Book saved = getBookById(item.getId());
            if (saved != null){
                saved.setAuthor(item.getAuthor());
                saved.setName(item.getName());
            }

        }
    }
    public void importFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Books.txt"), StandardCharsets.UTF_8);
        for(String line: lines){
            final String[] words = line.split("\\|");
            save(new Book(0, words[0], words[1]));
        }
    }

    public void exportFile(String path) throws IOException {
        Files.deleteIfExists(Paths.get(path));
        String content = "";
        for (Book item : items) {
            content = content + item.getId() + "|" + item.getAuthor() + "|" + item.getName() + "\n";
            Files.writeString(Path.of(path), content, StandardCharsets.UTF_8);
        }
    }
}
