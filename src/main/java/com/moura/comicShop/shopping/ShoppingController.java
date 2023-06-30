package com.moura.comicShop.shopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.coupon.Coupon;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppings")
@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
public class ShoppingController {
  @Autowired
  private ShoppingService shoppingService;

  @PostMapping
  @PreAuthorize("hasAuthority('management:create')")
  public Shopping createShopping(@RequestBody ShoppingDTO shoppingDTO) {
    return shoppingService.createShopping(shoppingDTO);
  }

  @PostMapping("/coupon")
  @PreAuthorize("hasAuthority('management:create')")
  public Shopping createShoppingCoupon(@RequestBody ShoppingCouponDTO shoppingCouponDTO) {
    return shoppingService.createShoppingCoupon(shoppingCouponDTO);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('management:update')")
  public Shopping updateShopping(@PathVariable Long id, @RequestBody ShoppingDTO shoppingDTO) {
    return shoppingService.updateShopping(id, shoppingDTO);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('management:delete')")
  public void deleteShopping(@PathVariable Long id) {
    shoppingService.deleteShopping(id);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Shopping> listAllShopping() {
    return shoppingService.listAllShopping();
  }

  @GetMapping("/user/{user_email}")
  @PreAuthorize("hasAuthority('management:read')")
  public List<Shopping> getShoppingByEmail(@PathVariable String user_email) {
    return shoppingService.getShoppingByEmail(user_email);
  }

  @GetMapping("/comic/{comic_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Shopping> getShoppingByComicId(@PathVariable Comic comic_id) {
    return shoppingService.getShoppingByComicId(comic_id);
  }

  @GetMapping("/coupon/{coupon_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Shopping> getShoppingByCouponId(@PathVariable Coupon coupon_id) {
    return shoppingService.getShoppingByCouponId(coupon_id);
  }

  @GetMapping("/user/{user_email}/coupon/{coupon_id}")
  @PreAuthorize("hasAuthority('admin:read')")
  public List<Shopping> getShoppingByEmailAndCouponId(@PathVariable String user_email,
      @PathVariable Coupon coupon_id) {
    return shoppingService.getShoppingByEmailAndCoupon(user_email, coupon_id);
  }

}
