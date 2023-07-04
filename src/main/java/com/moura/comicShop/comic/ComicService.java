package com.moura.comicShop.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicService {
  @Autowired
  private ComicRepository comicRepository;

  public ComicResponse createComic(Comic comic) {
    return ComicResponse.builder()
        .message("Comic created")
        .comic(comicRepository.save(comic))
        .build();
  }

  public ComicResponse updateComic(Comic comic) {
    return ComicResponse.builder()
        .message("Comic updated")
        .comic(comicRepository.save(comic))
        .build();
  }

  public ComicResponse deleteComicById(Long id) {
    comicRepository.deleteById(id);
    return ComicResponse.builder()
        .message("Comic deleted")
        .build();
  }

  public ComicResponseList listAllComics() {
    return ComicResponseList.builder()
        .message("List of comics")
        .comics(comicRepository.findAll())
        .build();
  }

  public ComicResponseList listAllComicsByPage(int page) {
    return ComicResponseList.builder()
        .message("List of comics")
        .comics(comicRepository.findAll().subList(page * 20, (page + 1) * 20))
        .build();
  }

  public ComicResponse searchComicById(Long id) {
    String message = "Comic not found";
    Comic comic = null;
    if (comicRepository.findById(id).isPresent()) {
      message = "Comic found";
      comic = comicRepository.findById(id).get();
    }
    return ComicResponse.builder()
        .message(message)
        .comic(comic)
        .build();
  }

  public ComicResponseList searchComicByTitle(String title) {
    return ComicResponseList.builder()
        .message("List of comics")
        .comics(comicRepository.findByTitleContainingIgnoreCase(title))
        .build();
  }

  public ComicResponseList searchComicByCreator(String creator) {
    return ComicResponseList.builder()
        .message("List of comics")
        .comics(comicRepository.findByCreatorContainingIgnoreCase(creator))
        .build();
  }

}
