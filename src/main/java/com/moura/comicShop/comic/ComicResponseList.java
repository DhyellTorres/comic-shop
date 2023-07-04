package com.moura.comicShop.comic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicResponseList {
  @JsonProperty("message")
  private String message;
  @JsonProperty("comics")
  private List<Comic> comics;
}
