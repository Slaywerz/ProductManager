package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book book1 = new Book(1, "Book 1", 300, "Author 1");
    private Book book2 = new Book(2, "Book 2", 350, "Author 2");
    private Smartphone smart1 = new Smartphone(3, "Smart 1", 400, "Producer 1");
    private Smartphone smart2 = new Smartphone(4, "Smart 2", 450, "Producer 2");

    @Test
    public void shouldSaveProduct(){
        repository.save(book1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSaveAllProducts(){
        repository.save(book2);
        repository.save(smart1);
        repository.save(smart2);
        repository.save(book1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book2, smart1, smart2, book1};
        assertArrayEquals(expected, actual);
    }
}