package ru.netology.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.repository.ShopRepository;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "Хлеб", 38);
    Product product2 = new Product(2, "Кефир", 95);
    Product product3 = new Product(3, "Яйца", 65);

    @BeforeEach
    public void addProduct() {
        repo.add(product1);
        repo.add(product2);
    }

    @Test
    public void shouldFindAllProduct() {
        repo.findAll();
        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddProductToRepo() {
        repo.add(product3);
        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldRemoveProduct() {
        repo.removeById(1);
        Product[] expected = {product2};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldRemoveNonExistingProduct() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(5);
        });
    }
}