package com.moura.comicShop.shopping;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Calendar;
import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.coupon.Coupon;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
  List<Shopping> findAll();

  List<Shopping> findByComic_id(Comic comic_id);

  List<Shopping> findByEmailContainingIgnoreCase(String user_email);

  List<Shopping> findByDate(Calendar date);

  List<Shopping> findByCoupon_id(Coupon coupon_id);

  List<Shopping> findByEmailContainingIgnoreCaseAndCoupon_id(String user_email, Coupon coupon_id);

  void deleteById(Long id);
}
