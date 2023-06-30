package com.moura.comicShop.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
@PreAuthorize("hasRole('ADMIN')")
public class CouponController {
  @Autowired
  private CouponService couponService;

  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public Coupon createCoupon(@RequestBody CouponDTO couponDTO) {
    return couponService.createCoupon(couponDTO);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public Coupon updateCoupon(@PathVariable Long id, @RequestBody CouponDTO couponDTO) {
    return couponService.updateCoupon(id, couponDTO);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:delete')")
  public void deleteCoupon(@PathVariable Long id) {
    couponService.deleteCoupon(id);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Coupon> listAllCoupons() {
    return couponService.listAllCoupons();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public Coupon getCouponById(@PathVariable Long id) {
    return couponService.getCouponById(id);
  }

  @GetMapping("/code/{code}")
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Coupon> getCouponByCode(@PathVariable String code) {
    return couponService.getCouponByCode(code);
  }
}
