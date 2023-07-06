package com.moura.comicShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.comic.ComicRepository;
import com.moura.comicShop.comic.ComicResponse;
import com.moura.comicShop.comic.ComicResponseList;
import com.moura.comicShop.comic.ComicService;

import java.util.ArrayList;
import java.util.List;

public class ComicServiceTest {

  @Mock
  private ComicRepository comicRepository;

  @InjectMocks
  private ComicService comicService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateComic_ShouldReturnComicResponse() {
    // Arrange
    Comic comic = new Comic();
    comic.setTitle("The Amazing Spider-Man");

    Comic savedComic = new Comic();
    savedComic.setId(1L);
    savedComic.setTitle("The Amazing Spider-Man");

    when(comicRepository.save(comic)).thenReturn(savedComic);

    // Act
    ComicResponse response = comicService.createComic(comic);

    // Assert
    assertEquals("Comic created", response.getMessage());
    assertEquals(savedComic, response.getComic());
  }

  @Test
  public void testUpdateComic_ShouldReturnComicResponse() {
    // Arrange
    Comic comic = new Comic();
    comic.setId(1L);
    comic.setTitle("Updated Spider-Man");

    Comic savedComic = new Comic();
    savedComic.setId(1L);
    savedComic.setTitle("Updated Spider-Man");

    when(comicRepository.save(comic)).thenReturn(savedComic);

    // Act
    ComicResponse response = comicService.updateComic(comic);

    // Assert
    assertEquals("Comic updated", response.getMessage());
    assertEquals(savedComic, response.getComic());
  }

  @Test
  public void testDeleteComicById_ShouldReturnComicResponse() {
    // Arrange
    Long comicId = 1L;

    // Act
    ComicResponse response = comicService.deleteComicById(comicId);

    // Assert
    assertEquals("Comic deleted", response.getMessage());
    verify(comicRepository).deleteById(comicId);
  }

  @Test
  public void testListAllComics_ShouldReturnComicResponseList() {
    // Arrange
    List<Comic> comics = new ArrayList<>();
    comics.add(new Comic());
    comics.add(new Comic());

    when(comicRepository.findAll()).thenReturn(comics);

    // Act
    ComicResponseList response = comicService.listAllComics();

    // Assert
    assertEquals("List of comics", response.getMessage());
    assertEquals(comics, response.getComics());
  }
}
