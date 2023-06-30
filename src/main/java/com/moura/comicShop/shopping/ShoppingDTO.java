package com.moura.comicShop.shopping;

import com.moura.comicShop.comic.Comic;

import jakarta.validation.constraints.NotBlank;

public record ShoppingDTO(@NotBlank Comic comic_id, @NotBlank String user_email, @NotBlank int quantity) {

}
