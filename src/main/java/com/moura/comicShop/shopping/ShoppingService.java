package com.moura.comicShop.shopping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.coupon.Coupon;
import com.moura.comicShop.coupon.CouponRepository;

@Service
public class ShoppingService {
  @Autowired
  private ShoppingRepository shoppingRepository;

  @Autowired
  private CouponRepository couponRepository;

  public Shopping createShopping(ShoppingDTO shoppingDTO) {
    Shopping shopping = new Shopping(shoppingDTO.comic_id(), shoppingDTO.user_email(), shoppingDTO.quantity());
    return shoppingRepository.save(shopping);
  }

  public Shopping createShoppingCoupon(ShoppingCouponDTO shoppingCouponDTO) {
    Shopping shopping = new Shopping(shoppingCouponDTO.shoppingDTO().comic_id(),
        shoppingCouponDTO.shoppingDTO().user_email(),
        shoppingCouponDTO.shoppingDTO().quantity(),
        couponRepository.findByCode(shoppingCouponDTO.coupon_code()).orElseThrow());
    return shoppingRepository.save(shopping);
  }

  public Shopping getShoppingById(Long id) {
    return shoppingRepository.findById(id).orElseThrow();
  }

  public Shopping updateShopping(Long id, ShoppingDTO shoppingDTO) {
    Shopping shopping = shoppingRepository.findById(id).orElseThrow();
    shopping.setComic(shoppingDTO.comic_id());
    shopping.setEmail(shoppingDTO.user_email());
    shopping.setQuantity(shoppingDTO.quantity());
    return shoppingRepository.save(shopping);
  }

  public void deleteShopping(Long id) {
    shoppingRepository.deleteById(id);
  }

  public List<Shopping> listAllShopping() {
    return shoppingRepository.findAll();
  }

  public List<Shopping> getShoppingByEmail(String email) {
    return shoppingRepository.findByEmailContainingIgnoreCase(email);
  }

  public List<Shopping> getShoppingByComicId(Comic comic) {
    return shoppingRepository.findByComic_id(comic);
  }

  public List<Shopping> getShoppingByCouponId(Coupon coupon) {
    return shoppingRepository.findByCoupon_id(coupon);
  }

  public List<Shopping> getShoppingByEmailAndCoupon(String email, Coupon coupon) {
    return shoppingRepository.findByEmailContainingIgnoreCaseAndCoupon_id(email, coupon);
  }
}
