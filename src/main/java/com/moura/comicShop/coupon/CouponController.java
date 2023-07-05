package com.moura.comicShop.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Coupon", description = "Endpoints para manipula\u00E7\u00E3o de cupons")
@RestController
@RequestMapping("/api/v1/coupons")
@PreAuthorize("hasRole('ADMIN')")
public class CouponController {
  @Autowired
  private CouponService couponService;

  @Operation(summary = "Cria um cupom")
  @PostMapping
  @PreAuthorize("hasAuthority('admin:create')")
  public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponDTO couponDTO) {
    return ResponseEntity.ok().body(couponService.createCoupon(couponDTO));
  }

  @Operation(summary = "Atualiza um cupom")
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:update')")
  public ResponseEntity<CouponResponse> updateCoupon(@PathVariable Long id, @RequestBody CouponDTO couponDTO) {
    return ResponseEntity.ok().body(couponService.updateCoupon(id, couponDTO));
  }

  @Operation(summary = "Deleta um cupom")
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

  @Operation(summary = "Lista todos os cupons")
  @GetMapping
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponseList> listAllCoupons() {
    return ResponseEntity.ok().body(couponService.listAllCoupons());
  }

  @Operation(summary = "Lista cupom pelo id")
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponse> getCouponById(@PathVariable Long id) {
    return ResponseEntity.ok().body(couponService.getCouponById(id));
  }

  @Operation(summary = "Lista cupom pelo c\u00F3digo")
  @GetMapping("/code/{code}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<CouponResponse> getCouponByCode(@PathVariable String code) {
    return ResponseEntity.ok().body(couponService.getCouponByCode(code));
  }
}
