package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductManagerTest {
    private final ProductRepository repository = new ProductRepository();
    private final ProductManager manager = new ProductManager(repository);
    private final Book book1 = new Book(1, "Book 1", 1000, "Author 1");
    private final Book book2 = new Book(2, "Book 2", 2000, "Author 2");
    private final Book book3 = new Book(5, "Book 3", 2100, "Author 2");
    private final Smartphone smart1 = new Smartphone(3, "Smart 1", 20000, "Producer 1");
    private final Smartphone smart2 = new Smartphone(4, "Smart 2", 25000, "Producer 2");

    @Test
    public void shouldAddProduct() {
        manager.addProduct(book1);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldAddAllProducts() {
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(smart1);
        manager.addProduct(smart2);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1, book2, smart1, smart2};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchProduct() {
        manager.addProduct(book1);
        manager.addProduct(book2);

        Product[] actual = manager.searchBy("Book 2");
        Product[] expected = new Product[]{book2};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchBookByAuthor() {
        manager.addProduct(book1);
        manager.addProduct(book2);

        Product[] actual = manager.searchBy("Author 1");
        Product[] expected = new Product[]{book1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchSmartByProducer() {
        manager.addProduct(smart1);
        manager.addProduct(smart2);

        Product[] actual = manager.searchBy("Producer 1");
        Product[] expected = new Product[]{smart1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchSmartByName() {
        manager.addProduct(smart1);
        manager.addProduct(smart2);

        Product[] actual = manager.searchBy("Smart 2");
        Product[] expected = new Product[]{smart2};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotFoundProduct() {
        manager.addProduct(smart1);
        manager.addProduct(smart2);
        manager.addProduct(book1);
        manager.addProduct(book2);

        Product[] actual = manager.searchBy("Iphone");
        Product[] expected = new Product[0];
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldFindTwoBookByOneAuthor() {
        manager.addProduct(book2);
        manager.addProduct(book1);
        manager.addProduct(book3);

        Product[] actual = manager.searchBy("Author 2");
        Product[] expected = new Product[]{book2, book3};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldRemoveById() {
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(smart1);
        manager.addProduct(smart2);
        manager.removeProduct(2);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1, smart1, smart2};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldShowNotFoundException() {
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(smart1);
        manager.addProduct(smart2);
        assertThrows(NotFoundException.class, () -> manager.removeProduct(10));
        System.out.println("After NotFoundException");
    }
}