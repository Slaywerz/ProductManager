package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "Book 1", 1000, "Author 1");
    private Book book2 = new Book(2, "Book 2", 2000, "Author 2");
    private Smartphone smart1 = new Smartphone(3, "Smart 1", 20000, "Producer 1");
    private Smartphone smart2 = new Smartphone(4, "Smart 2", 25000, "Producer 2");

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
    public void shouldRemoveById(){
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(smart1);
        manager.addProduct(smart2);
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1, smart1, smart2};
        assertArrayEquals(actual, expected);
    }
}