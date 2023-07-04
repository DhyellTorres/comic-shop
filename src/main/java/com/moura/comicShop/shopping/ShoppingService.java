package com.moura.comicShop.shopping;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.comic.ComicRepository;
import com.moura.comicShop.coupon.Coupon;
import com.moura.comicShop.coupon.CouponRepository;

@Service
public class ShoppingService {
  @Autowired
  private ShoppingRepository shoppingRepository;

  @Autowired
  private CouponRepository couponRepository;

  @Autowired
  private ComicRepository comicRepository;

  public ResponseEntity<?> createShopping(ShoppingDTO shoppingDTO) {
    if (comicRepository.existsById(shoppingDTO.comic_id())) {
      if (shoppingDTO.quantity() > 0) {
        if (shoppingDTO.user_email() != null) {
          Shopping shopping = new Shopping(comicRepository.findById(shoppingDTO.comic_id()).orElseThrow(),
              shoppingDTO.user_email(), shoppingDTO.quantity());
          shoppingRepository.save(shopping);
          return ResponseEntity.ok().body("Shopping created successfully");
        }
        return ResponseEntity.badRequest().body("User email is required");
      }
      return ResponseEntity.badRequest().body("Quantity must be greater than 0");
    }
    return ResponseEntity.badRequest().body("Comic not found");
  }

  public ResponseEntity<?> createShoppingCoupon(ShoppingCouponDTO shoppingCouponDTO) {
    TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
    if (comicRepository.existsById(shoppingCouponDTO.shoppingDTO().comic_id())) {
      if (shoppingCouponDTO.shoppingDTO().quantity() > 0) {
        if (shoppingCouponDTO.shoppingDTO().user_email() != null) {
          if (couponRepository
              .existsById(couponRepository.findByCode(shoppingCouponDTO.coupon_code()).orElseThrow().getId())) {
            if (Calendar.getInstance(timeZone).getTimeInMillis() <= couponRepository
                .findByCode(shoppingCouponDTO.coupon_code()).orElseThrow().getExpirationDate().getTimeInMillis()) {
              Shopping shopping = new Shopping(
                  comicRepository.findById(shoppingCouponDTO.shoppingDTO().comic_id()).orElseThrow(),
                  shoppingCouponDTO.shoppingDTO().user_email(),
                  shoppingCouponDTO.shoppingDTO().quantity(),
                  couponRepository.findByCode(shoppingCouponDTO.coupon_code()).orElseThrow());
              shoppingRepository.save(shopping);
              return ResponseEntity.ok().body("Shopping created successfully");
            }
            return ResponseEntity.badRequest().body("Coupon expired");
          }
          return ResponseEntity.badRequest().body("Coupon not found");
        }
        return ResponseEntity.badRequest().body("User email is required");
      }
      return ResponseEntity.badRequest().body("Quantity must be greater than 0");
    }
    return ResponseEntity.badRequest().body("Comic not found");
  }

  public ResponseEntity<?> getShoppingById(Long id) {
    if (shoppingRepository.existsById(id)) {
      return ResponseEntity.ok().body(shoppingRepository.findById(id).orElseThrow());
    }
    return ResponseEntity.badRequest().body("Shopping not found");
  }

  public ResponseEntity<?> updateShopping(Long id, ShoppingDTO shoppingDTO) {
    if (shoppingRepository.existsById(id)) {
      if (comicRepository.existsById(shoppingDTO.comic_id())) {
        if (id.equals(shoppingDTO.comic_id())) {
          Shopping shopping = shoppingRepository.findById(id).orElseThrow();
          shopping.setComic(comicRepository.findById(shoppingDTO.comic_id()).orElseThrow());
          shopping.setEmail(shoppingDTO.user_email());
          shopping.setQuantity(shoppingDTO.quantity());
          shoppingRepository.save(shopping);
          return ResponseEntity.ok().body("Shopping updated successfully");
        }
        return ResponseEntity.badRequest().body("Id must be the same as the comic id");
      }
      return ResponseEntity.badRequest().body("Comic not found");
    }
    return ResponseEntity.badRequest().body("Shopping not found");
  }

  public ResponseEntity<?> deleteShopping(Long id) {
    if (shoppingRepository.existsById(id)) {
      shoppingRepository.deleteById(id);
      return ResponseEntity.ok().body("Shopping deleted successfully");
    }
    return ResponseEntity.badRequest().body("Shopping not found");
  }

  public ResponseEntity<?> listAllShopping() {
    return ResponseEntity.ok().body(shoppingRepository.findAll());
  }

  public ResponseEntity<?> getShoppingByEmail(String email) {
    return ResponseEntity.ok().body(shoppingRepository.findByEmailContainingIgnoreCase(email));
  }

  public ResponseEntity<?> getShoppingByComicId(Comic comic) {
    return ResponseEntity.ok().body(shoppingRepository.findByComic_id(comic));
  }

  public ResponseEntity<?> getShoppingByCouponId(Coupon coupon) {
    return ResponseEntity.ok().body(shoppingRepository.findByCoupon_id(coupon));
  }

  public ResponseEntity<?> getShoppingByEmailAndCoupon(String email, Coupon coupon) {
    return ResponseEntity.ok().body(shoppingRepository.findByEmailContainingIgnoreCaseAndCoupon_id(email, coupon));
  }
}
