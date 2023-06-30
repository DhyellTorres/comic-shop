package com.moura.comicShop.shopping;

import jakarta.validation.constraints.NotBlank;

public record ShoppingCouponDTO(@NotBlank ShoppingDTO shoppingDTO, @NotBlank String coupon_code) {

}
