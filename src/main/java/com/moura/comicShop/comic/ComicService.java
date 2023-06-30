package com.moura.comicShop.comic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicService {
  @Autowired
  private ComicRepository comicRepository;

  public Comic createComic(Comic comic) {
    return comicRepository.save(comic);
  }

  public Comic updateComic(Comic comic) {
    return comicRepository.save(comic);
  }

  public void deleteComicById(Long id) {
    comicRepository.deleteById(id);
  }

  public List<Comic> listAllComics() {
    return comicRepository.findAll();
  }

  public List<Comic> listAllComicsByPage(int page) {
    return comicRepository.findAll().subList(page * 20, (page + 1) * 20);
  }

  public Comic searchComicById(Long id) {
    return comicRepository.findById(id).orElseThrow(() -> new RuntimeException("Comic not found"));
  }

  public List<Comic> searchComicByTitle(String title) {
    return comicRepository.findByTitleContainingIgnoreCase(title);
  }

  public List<Comic> searchComicByCreator(String creator) {
    return comicRepository.findByCreatorContainingIgnoreCase(creator);
  }

}
