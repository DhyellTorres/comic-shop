package com.moura.comicShop.comic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicResponse {
  @JsonProperty("message")
  private String message;
  @JsonProperty("shopping")
  private Comic comic;
}
