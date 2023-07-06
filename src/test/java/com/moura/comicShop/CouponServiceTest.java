package com.moura.comicShop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.moura.comicShop.coupon.Coupon;
import com.moura.comicShop.coupon.CouponDTO;
import com.moura.comicShop.coupon.CouponRepository;
import com.moura.comicShop.coupon.CouponResponse;
import com.moura.comicShop.coupon.CouponService;

public class CouponServiceTest {

  @Mock
  private CouponRepository couponRepository;

  @InjectMocks
  private CouponService couponService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateCoupon_ShouldReturnSuccessMessageAndCoupon() {
    // Arrange
    String discount = "5";
    int days = 30;
    CouponDTO couponDTO = new CouponDTO(discount, days);
    Coupon coupon = new Coupon(discount, days);
    when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

    // Act
    CouponResponse response = couponService.createCoupon(couponDTO);

    // Assert
    assertNotNull(response);
    assertEquals("Coupon created successfully", response.getMessage());
    assertNotNull(response.getCoupon());
    assertEquals(discount, response.getCoupon().getDiscount());
    verify(couponRepository).save(any(Coupon.class));
  }

}
