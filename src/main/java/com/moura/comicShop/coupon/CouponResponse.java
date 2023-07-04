package com.moura.comicShop.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponse {
  @JsonProperty("message")
  private String message;
  @JsonProperty("coupon")
  private Coupon coupon;
}
