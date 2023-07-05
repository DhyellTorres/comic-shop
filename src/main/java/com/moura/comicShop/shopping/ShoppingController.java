package com.moura.comicShop.shopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shoppings")
@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
public class ShoppingController {
  @Autowired
  private ShoppingService shoppingService;

  @PostMapping
  @PreAuthorize("hasAuthority('management:create')")
  public ResponseEntity<ShoppingResponse> createShopping(@RequestBody ShoppingDTO shoppingDTO) {
    ShoppingResponse sr = shoppingService.createShopping(shoppingDTO);
    if (sr.getMessage().equals("Shopping created successfully")) {
      return ResponseEntity.ok().body(sr);
    } else {
      return ResponseEntity.badRequest().body(sr);
    }
  }

  @PostMapping("/coupon")
  @PreAuthorize("hasAuthority('management:create')")
  public ResponseEntity<ShoppingResponse> createShoppingCoupon(@RequestBody ShoppingCouponDTO shoppingCouponDTO) {
    ShoppingResponse sr = shoppingService.createShoppingCoupon(shoppingCouponDTO);
    if (sr.getMessage().equals("Shopping created successfully")) {
      return ResponseEntity.ok().body(sr);
    } else {
      return ResponseEntity.badRequest().body(sr);
    }
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('management:update')")
  public ResponseEntity<ShoppingResponse> updateShopping(@PathVariable Long id, @RequestBody ShoppingDTO shoppingDTO) {
    ShoppingResponse sr = shoppingService.updateShopping(id, shoppingDTO);
    if (sr.getMessage().equals("Shopping created successfully")) {
      return ResponseEntity.ok().body(sr);
    } else {
      return ResponseEntity.badRequest().body(sr);
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('management:delete')")
  public ResponseEntity<ShoppingResponse> deleteShopping(@PathVariable Long id) {
    ShoppingResponse sr = shoppingService.deleteShopping(id);
    if (sr.getMessage().equals("Shopping created successfully")) {
      return ResponseEntity.ok().body(sr);
    } else {
      return ResponseEntity.badRequest().body(sr);
    }
  }

  @GetMapping
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<ShoppingResponseList> listAllShopping() {
    return ResponseEntity.ok().body(shoppingService.listAllShopping());
  }

  @GetMapping("/user/{user_email}")
  @PreAuthorize("hasAuthority('management:read')")
  public ResponseEntity<ShoppingResponseList> getShoppingByEmail(@PathVariable String user_email) {
    return ResponseEntity.ok().body(shoppingService.getShoppingByEmail(user_email));
  }

  @GetMapping("/comic/{comic_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<ShoppingResponseList> getShoppingByComicId(@PathVariable long comic_id) {
    return ResponseEntity.ok().body(shoppingService.getShoppingByComicId(comic_id));
  }

  @GetMapping("/coupon/{coupon_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<ShoppingResponseList> getShoppingByCouponId(@PathVariable long coupon_id) {
    return ResponseEntity.ok().body(shoppingService.getShoppingByCouponId(coupon_id));
  }

  @GetMapping("/user/{user_email}/coupon/{coupon_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public ResponseEntity<ShoppingResponseList> getShoppingByEmailAndCouponId(@PathVariable String user_email,
      @PathVariable long coupon_id) {
    return ResponseEntity.ok().body(shoppingService.getShoppingByEmailAndCoupon(user_email, coupon_id));
  }

}
