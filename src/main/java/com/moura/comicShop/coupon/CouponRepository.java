package com.moura.comicShop.coupon;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
  List<Coupon> findAll();

  List<Coupon> findByCodeContainingIgnoreCase(String code);

  List<Coupon> findByDiscountContainingIgnoreCase(String discount);

  List<Coupon> findByExpirationDateContaining(String expirationDate);

  Optional<Coupon> findByCode(String code);

  void deleteById(Long id);
}
