package com.moura.comicShop.comic;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long> {
  List<Comic> findAll();

  List<Comic> findByTitleContainingIgnoreCase(String title);

  List<Comic> findByCreatorContainingIgnoreCase(String creator);

  List<Comic> findByRarityContainingIgnoreCase(String rarity);

  List<Comic> findByPriceContainingIgnoreCase(String price);

  List<Comic> findByDateContainingIgnoreCase(String date);

  Optional<Comic> findById(Long id);

  void deleteById(Long id);
}
