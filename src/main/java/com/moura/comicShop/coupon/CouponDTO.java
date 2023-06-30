package com.moura.comicShop.coupon;

import jakarta.validation.constraints.NotBlank;

public record CouponDTO(@NotBlank String discount, @NotBlank int days) {

}
