package com.moura.comicShop.shopping;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moura.comicShop.comic.ComicRepository;
import com.moura.comicShop.coupon.CouponRepository;

@Service
public class ShoppingService {
  @Autowired
  private ShoppingRepository shoppingRepository;

  @Autowired
  private CouponRepository couponRepository;

  @Autowired
  private ComicRepository comicRepository;

  public ShoppingResponse createShopping(ShoppingDTO shoppingDTO) {
    String message = "Comic not found";
    Shopping shoppings = null;
    if (comicRepository.existsById(shoppingDTO.comic_id())) {
      if (shoppingDTO.quantity() > 0) {
        if (shoppingDTO.user_email() != null) {
          Shopping shopping = new Shopping(comicRepository.findById(shoppingDTO.comic_id()).orElseThrow(),
              shoppingDTO.user_email(), shoppingDTO.quantity());
          shoppings = shoppingRepository.save(shopping);
          message = "Shopping created successfully";
        } else {
          message = "User email is required";
        }
      } else {
        message = "Quantity must be greater than 0";
      }
    }
    return ShoppingResponse.builder()
        .message(message)
        .shopping(shoppings)
        .build();
  }

  public ShoppingResponse createShoppingCoupon(ShoppingCouponDTO shoppingCouponDTO) {
    TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
    String message = "Comic not found";
    Shopping shoppings = null;
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
              shoppings = shoppingRepository.save(shopping);
              message = "Shopping created successfully";
            } else {
              message = "Coupon expired";
            }
          } else {
            message = "Coupon not found";
          }
        } else {
          message = "User email is required";
        }
      } else {
        message = "Quantity must be greater than 0";
      }
    }
    return ShoppingResponse.builder()
        .message(message)
        .shopping(shoppings)
        .build();
  }

  public ShoppingResponse getShoppingById(Long id) {
    String message = "Shopping not found";
    Shopping shoppings = null;
    if (shoppingRepository.existsById(id)) {
      message = "Shopping found successfully";
      shoppings = shoppingRepository.findById(id).orElseThrow();
    }
    return ShoppingResponse.builder()
        .message(message)
        .shopping(shoppings)
        .build();
  }

  public ShoppingResponse updateShopping(Long id, ShoppingDTO shoppingDTO) {
    String message = "Shopping not found";
    Shopping shoppings = null;
    if (shoppingRepository.existsById(id)) {
      if (comicRepository.existsById(shoppingDTO.comic_id())) {
        if (id.equals(shoppingDTO.comic_id())) {
          Shopping shopping = shoppingRepository.findById(id).orElseThrow();
          shopping.setComic(comicRepository.findById(shoppingDTO.comic_id()).orElseThrow());
          shopping.setEmail(shoppingDTO.user_email());
          shopping.setQuantity(shoppingDTO.quantity());
          shoppings = shoppingRepository.save(shopping);
          message = "Shopping updated successfully";
        } else {
          message = "Id must be the same as the comic id";
        }
      } else {
        message = "Comic not found";
      }
    }
    return ShoppingResponse.builder()
        .message(message)
        .shopping(shoppings)
        .build();
  }

  public ShoppingResponse deleteShopping(Long id) {
    String message = "Shopping not found";
    Shopping shoppings = null;
    if (shoppingRepository.existsById(id)) {
      shoppingRepository.deleteById(id);
      message = "Shopping deleted successfully";
    }
    return ShoppingResponse.builder()
        .message(message)
        .shopping(shoppings)
        .build();
  }

  public ShoppingResponseList listAllShopping() {
    return ShoppingResponseList.builder()
        .shoppings(shoppingRepository.findAll())
        .build();
  }

  public ShoppingResponseList getShoppingByEmail(String email) {
    return ShoppingResponseList.builder()
        .shoppings(shoppingRepository.findByEmailContainingIgnoreCase(email))
        .build();
  }

  public ShoppingResponseList getShoppingByComicId(long comic) {
    if (comicRepository.existsById(comic)) {
      return ShoppingResponseList.builder()
          .shoppings(shoppingRepository.findByComic_id(comicRepository.findById(comic).orElseThrow()))
          .build();
    } else {
      return ShoppingResponseList.builder()
          .shoppings(null)
          .build();
    }
  }

  public ShoppingResponseList getShoppingByCouponId(long coupon) {
    if (couponRepository.existsById(coupon)) {
      return ShoppingResponseList.builder()
          .shoppings(shoppingRepository.findByCoupon_id(couponRepository.findById(coupon).orElseThrow()))
          .build();
    } else {
      return ShoppingResponseList.builder()
          .shoppings(null)
          .build();
    }
  }

  public ShoppingResponseList getShoppingByEmailAndCoupon(String email, long coupon) {
    if (couponRepository.existsById(coupon)) {
      if (shoppingRepository.findByEmailContainingIgnoreCase(email).size() > 0) {
        return ShoppingResponseList.builder()
            .shoppings(shoppingRepository.findByEmailContainingIgnoreCaseAndCoupon_id(email,
                couponRepository.findById(coupon).orElseThrow()))
            .build();
      } else {
        return ShoppingResponseList.builder()
            .shoppings(null)
            .build();
      }
    } else {
      return ShoppingResponseList.builder()
          .shoppings(null)
          .build();
    }
  }
}
