package com.moura.comicShop.shopping;

import jakarta.validation.constraints.NotBlank;

public record ShoppingDTO(@NotBlank Long comic_id, @NotBlank String user_email, @NotBlank int quantity) {

}
