package com.moura.comicShop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.comic.ComicRepository;
import com.moura.comicShop.shopping.Shopping;
import com.moura.comicShop.shopping.ShoppingDTO;
import com.moura.comicShop.shopping.ShoppingRepository;
import com.moura.comicShop.shopping.ShoppingResponse;
import com.moura.comicShop.shopping.ShoppingService;

public class ShoppingServiceTest {

  @Mock
  private ComicRepository comicRepository;

  @Mock
  private ShoppingRepository shoppingRepository;

  @InjectMocks
  private ShoppingService shoppingService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateShopping_WithValidData_ShouldReturnSuccessMessage() {
    // Arrange
    long comicId = 1;
    String userEmail = "test@example.com";
    int quantity = 2;
    float comicPrice = 9.99f;
    Comic comic = new Comic();
    comic.setId(comicId);
    comic.setPrice(String.valueOf(comicPrice));
    ShoppingDTO shoppingDTO = new ShoppingDTO(comicId, userEmail, quantity);
    when(comicRepository.existsById(comicId)).thenReturn(true);
    when(comicRepository.findById(comicId)).thenReturn(java.util.Optional.of(comic));
    when(shoppingRepository.save(any(Shopping.class))).thenReturn(new Shopping());

    // Act
    ShoppingResponse response = shoppingService.createShopping(shoppingDTO);

    // Assert
    assertNotNull(response);
    assertEquals("Shopping created successfully", response.getMessage());
    verify(comicRepository).existsById(comicId);
    verify(comicRepository).findById(comicId);
    verify(shoppingRepository).save(any(Shopping.class));
  }

}
