package com.moura.comicShop.coupon;

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
public class CouponResponseList {
  @JsonProperty("message")
  private String message;
  @JsonProperty("coupons")
  private List<Coupon> coupons;
}
