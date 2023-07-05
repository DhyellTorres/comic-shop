package com.moura.comicShop.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Comic", description = "Endpoints para manipula\u00E7\u00E3o de quadrinhos")
@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {

  @Autowired
  private ComicService comicService;

  @Operation(summary = "Cria um quadrinho")
  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public ResponseEntity<ComicResponse> createComic(@RequestBody Comic comic) {
    return ResponseEntity.ok().body(comicService.createComic(comic));
  }

  @Operation(summary = "Atualiza um quadrinho")
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public ResponseEntity<ComicResponse> updateComic(@PathVariable Long id, @RequestBody Comic comic) {
    comic.setId(id);
    return ResponseEntity.ok().body(comicService.updateComic(comic));
  }

  @Operation(summary = "Deleta um quadrinho")
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:delete')")
  public ResponseEntity<ComicResponse> deleteComicById(@PathVariable Long id) {
    return ResponseEntity.ok().body(comicService.deleteComicById(id));
  }

  @Operation(summary = "Lista todos os quadrinhos")
  @GetMapping
  public ResponseEntity<ComicResponseList> listAllComics() {
    return ResponseEntity.ok().body(comicService.listAllComics());
  }

  @Operation(summary = "Lista quadrinho pelo id")
  @GetMapping("/{id}")
  public ResponseEntity<ComicResponse> getComicById(@PathVariable Long id) {
    return ResponseEntity.ok().body(comicService.searchComicById(id));
  }

  @Operation(summary = "Lista quadrinho pelo t\u00EDtulo")
  @GetMapping("/search")
  public ResponseEntity<ComicResponseList> searchComicsByTitle(@RequestParam("title") String title) {
    return ResponseEntity.ok().body(comicService.searchComicByTitle(title));
  }

  @Operation(summary = "Lista quadrinho pelo criador")
  @GetMapping("/searchByCreator")
  public ResponseEntity<ComicResponseList> searchComicsByCreator(@RequestParam("creator") String creator) {
    return ResponseEntity.ok().body(comicService.searchComicByCreator(creator));
  }

  @Operation(summary = "Lista quadrinho com pagina\u00E7\u00E3o")
  @GetMapping("/page/{page}")
  public ResponseEntity<ComicResponseList> listComicsByPage(@PathVariable int page) {
    return ResponseEntity.ok().body(comicService.listAllComicsByPage(page));
  }
}
