package com.moura.comicShop.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {

  @Autowired
  private ComicService comicService;

  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public Comic createComic(@RequestBody Comic comic) {
    return comicService.createComic(comic);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public Comic updateComic(@PathVariable Long id, @RequestBody Comic comic) {
    comic.setId(id);
    return comicService.updateComic(comic);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:delete')")
  public void deleteComicById(@PathVariable Long id) {
    comicService.deleteComicById(id);
  }

  @GetMapping
  public List<Comic> listAllComics() {
    return comicService.listAllComics();
  }

  @GetMapping("/{id}")
  public Comic getComicById(@PathVariable Long id) {
    return comicService.searchComicById(id);
  }

  @GetMapping("/search")
  public List<Comic> searchComicsByTitle(@RequestParam("title") String title) {
    return comicService.searchComicByTitle(title);
  }

  @GetMapping("/searchByCreator")
  public List<Comic> searchComicsByCreator(@RequestParam("creator") String creator) {
    return comicService.searchComicByCreator(creator);
  }

  @GetMapping("/page/{page}")
  public List<Comic> listComicsByPage(@PathVariable int page) {
    return comicService.listAllComicsByPage(page);
  }
}
