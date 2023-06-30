package com.moura.comicShop.comic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comic")
public class Comic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "synopsis", length = 5000)
  private String synopsis;

  @Column(name = "page_count")
  private int page_count;

  @Column(name = "date")
  private String date;

  @Column(name = "image", length = 1000)
  private String image;

  @Column(name = "creator")
  private String creator;

  @Column(name = "price")
  private String price;

  @Column(name = "rarity")
  private String rarity;
}
