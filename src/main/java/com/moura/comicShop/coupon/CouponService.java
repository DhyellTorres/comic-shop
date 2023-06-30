package com.moura.comicShop.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
  @Autowired
  private CouponRepository couponRepository;

  public Coupon createCoupon(CouponDTO couponDTO) {
    Coupon coupon = new Coupon(couponDTO.discount(), couponDTO.days());
    return couponRepository.save(coupon);
  }

  public Coupon getCouponById(Long id) {
    return couponRepository.findById(id).orElseThrow();
  }

  public Coupon updateCoupon(Long id, CouponDTO couponDTO) {
    Coupon coupon = couponRepository.findById(id).orElseThrow();
    coupon.setDiscount(couponDTO.discount());
    coupon.setExpirationDate(couponDTO.days());
    return couponRepository.save(coupon);
  }

  public void deleteCoupon(Long id) {
    couponRepository.deleteById(id);
  }

  public List<Coupon> listAllCoupons() {
    return couponRepository.findAll();
  }

  public List<Coupon> getCouponByCode(String code) {
    return couponRepository.findByCodeContainingIgnoreCase(code);
  }
}
