package com.moura.comicShop.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
  @Autowired
  private CouponRepository couponRepository;

  public CouponResponse createCoupon(CouponDTO couponDTO) {
    Coupon coupon = new Coupon(couponDTO.discount(), couponDTO.days());
    return CouponResponse.builder()
        .message("Coupon created successfully")
        .coupon(couponRepository.save(coupon))
        .build();
  }

  public CouponResponse getCouponById(Long id) {
    String message = "Coupon found";
    Coupon coupon = couponRepository.findById(id).orElseThrow();
    if (coupon == null) {
      message = "Coupon not found";
    }
    return CouponResponse.builder()
        .message(message)
        .coupon(coupon)
        .build();
  }

  public CouponResponse updateCoupon(Long id, CouponDTO couponDTO) {
    String message = "Coupon updated successfully";
    Coupon coupon = null;
    if (couponRepository.findById(id).orElseThrow() == null) {
      message = "Coupon not found";
    } else {
      coupon = couponRepository.findById(id).orElseThrow();
      coupon.setDiscount(couponDTO.discount());
      coupon.setExpirationDate(couponDTO.days());
      couponRepository.save(coupon);
    }
    return CouponResponse.builder()
        .message(message)
        .coupon(coupon)
        .build();
  }

  public CouponResponse deleteCoupon(Long id) {
    String message = "Coupon not found";
    if (couponRepository.existsById(id)) {
      message = "Coupon deleted successfully";
      couponRepository.deleteById(id);
    }
    return CouponResponse.builder()
        .message(message)
        .build();
  }

  public CouponResponseList listAllCoupons() {
    return CouponResponseList.builder()
        .message("Coupons found")
        .coupons(couponRepository.findAll())
        .build();
  }

  public CouponResponse getCouponByCode(String code) {
    Coupon coupon = couponRepository.findById(couponRepository.findByCode(code).orElseThrow().getId()).orElseThrow();
    return CouponResponse.builder()
        .message("Coupons found")
        .coupon(coupon)
        .build();
  }
}
