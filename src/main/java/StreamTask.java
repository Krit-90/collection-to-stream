import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTask {
    public static void main(String[] args) {
        List<Book> bookListOfPushkin = new ArrayList<Book>();
        Author pushkin = new Author("Pushkin", (short) 33, bookListOfPushkin);
        List<Book> bookListOfBulgakov = new ArrayList<Book>();
        Author bulgakov = new Author("Bulgakov", (short) 48, bookListOfBulgakov);
        List<Book> bookListOfLermontov = new ArrayList<Book>();
        Author lermontov = new Author("Lermontov", (short) 26, bookListOfLermontov);
        List<Author> authorsOfBook1 = new ArrayList<Author>();
        Book book1 = new Book("Title1", authorsOfBook1, 340);
        List<Author> authorsOfBook2 = new ArrayList<Author>();
        Book book2 = new Book("Title2", authorsOfBook2, 560);
        List<Author> authorsOfBook3 = new ArrayList<Author>();
        Book book3 = new Book("Title3", authorsOfBook3, 493);
        List<Author> authorsOfBook4 = new ArrayList<Author>();
        Book book4 = new Book("Title4", authorsOfBook4, 580);

        bookListOfPushkin.add(book1);
        authorsOfBook1.add(pushkin);

        bookListOfBulgakov.add(book2);
        bookListOfLermontov.add(book2);
        authorsOfBook2.add(bulgakov);
        authorsOfBook2.add(lermontov);

        bookListOfPushkin.add(book3);
        bookListOfLermontov.add(book3);
        authorsOfBook3.add(pushkin);
        authorsOfBook3.add(lermontov);

        bookListOfPushkin.add(book4);
        bookListOfBulgakov.add(book4);
        bookListOfLermontov.add(book4);
        authorsOfBook4.add(pushkin);
        authorsOfBook4.add(bulgakov);
        authorsOfBook4.add(lermontov);

        Author[] authors = {pushkin, bulgakov, lermontov};
        Book[] books = {book1, book2, book3, book4};
        boolean isMoreThanTwoHundred = Arrays.stream(books).anyMatch(book -> book.getNumberOfPages() > 200);
        int maxCountPages = Arrays.stream(books).map(Book::getNumberOfPages).collect(Collectors.toList()).stream()
                .max(Integer::compareTo).orElseThrow();
        int minCountPages = Arrays.stream(books).map(Book::getNumberOfPages).collect(Collectors.toList()).stream()
                .min(Integer::compareTo).orElseThrow();
        List<Book> withSingleAuthor = Arrays.stream(books).filter(book -> book.getAuthors().size() == 1)
                .collect(Collectors.toList());
        List<Book> sortedByNumberOfPages = Arrays.stream(books).sorted(Comparator.comparingInt(Book::getNumberOfPages))
                .collect(Collectors.toList());
        Arrays.stream(books).map(Book::getTitle).forEach(System.out::println);
        List<Author> authorList = Arrays.stream(books).flatMap(book -> book.getAuthors().stream()).distinct()
                .collect(Collectors.toList());
        pushkin.getBooks().stream().max(Comparator.comparingInt(Book::getNumberOfPages))
                .ifPresent(book -> System.out.println(book.getTitle()));
    }
}
