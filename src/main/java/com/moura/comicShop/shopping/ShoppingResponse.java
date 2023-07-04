package com.moura.comicShop.shopping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingResponse {

  @JsonProperty("message")
  private String message;
  @JsonProperty("shopping")
  private Shopping shopping;
}
