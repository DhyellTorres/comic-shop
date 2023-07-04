package com.moura.comicShop.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {

  @Autowired
  private ComicService comicService;

  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public ResponseEntity<ComicResponse> createComic(@RequestBody Comic comic) {
    return ResponseEntity.ok().body(comicService.createComic(comic));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public ResponseEntity<ComicResponse> updateComic(@PathVariable Long id, @RequestBody Comic comic) {
    comic.setId(id);
    return ResponseEntity.ok().body(comicService.updateComic(comic));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:delete')")
  public ResponseEntity<ComicResponse> deleteComicById(@PathVariable Long id) {
    return ResponseEntity.ok().body(comicService.deleteComicById(id));
  }

  @GetMapping
  public ResponseEntity<ComicResponseList> listAllComics() {
    return ResponseEntity.ok().body(comicService.listAllComics());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ComicResponse> getComicById(@PathVariable Long id) {
    return ResponseEntity.ok().body(comicService.searchComicById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<ComicResponseList> searchComicsByTitle(@RequestParam("title") String title) {
    return ResponseEntity.ok().body(comicService.searchComicByTitle(title));
  }

  @GetMapping("/searchByCreator")
  public ResponseEntity<ComicResponseList> searchComicsByCreator(@RequestParam("creator") String creator) {
    return ResponseEntity.ok().body(comicService.searchComicByCreator(creator));
  }

  @GetMapping("/page/{page}")
  public ResponseEntity<ComicResponseList> listComicsByPage(@PathVariable int page) {
    return ResponseEntity.ok().body(comicService.listAllComicsByPage(page));
  }
}
