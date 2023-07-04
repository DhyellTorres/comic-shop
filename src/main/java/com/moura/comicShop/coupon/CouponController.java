package com.moura.comicShop.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@PreAuthorize("hasRole('ADMIN')")
public class CouponController {
  @Autowired
  private CouponService couponService;

  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponDTO couponDTO) {
    return ResponseEntity.ok().body(couponService.createCoupon(couponDTO));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public ResponseEntity<CouponResponse> updateCoupon(@PathVariable Long id, @RequestBody CouponDTO couponDTO) {
    return ResponseEntity.ok().body(couponService.updateCoupon(id, couponDTO));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:delete')")
  public ResponseEntity<CouponResponse> deleteCoupon(@PathVariable Long id) {
    CouponResponse cr = couponService.deleteCoupon(id);
    if (cr.getMessage().equals("Coupon deleted successfully")) {
      return ResponseEntity.ok().body(cr);
    } else {
      return ResponseEntity.badRequest().body(cr);
    }
  }

  @GetMapping
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponseList> listAllCoupons() {
    return ResponseEntity.ok().body(couponService.listAllCoupons());
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponse> getCouponById(@PathVariable Long id) {
    return ResponseEntity.ok().body(couponService.getCouponById(id));
  }

  @GetMapping("/code/{code}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponse> getCouponByCode(@PathVariable String code) {
    return ResponseEntity.ok().body(couponService.getCouponByCode(code));
  }
}
