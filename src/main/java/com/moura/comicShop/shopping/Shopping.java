package com.moura.comicShop.shopping;

import java.util.Calendar;
import java.util.TimeZone;

import com.moura.comicShop.comic.Comic;
import com.moura.comicShop.coupon.Coupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Entity
@Table(name = "shopping")
public class Shopping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "comic_id", referencedColumnName = "id")
  private Comic comic;

  @Column(name = "email")
  private String email;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "total_price")
  private float total_price;

  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar date;

  @ManyToOne
  @JoinColumn(name = "coupon_id", referencedColumnName = "id")
  private Coupon coupon;

  public Shopping(Comic comic, String email, int quantity) {
    this.comic = comic;
    this.email = email;
    this.quantity = quantity;
    this.total_price = Float.parseFloat(comic.getPrice()) * quantity;
    this.date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    this.coupon = null;
  }

  public Shopping(Comic comic, String email, int quantity, Coupon coupon) {
    this.comic = comic;
    this.email = email;
    this.quantity = quantity;
    float discount = Float.parseFloat(coupon.getDiscount()) / 100;
    float price = Float.parseFloat(comic.getPrice()) * quantity;
    this.total_price = price - (discount * price);
    this.date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    this.coupon = coupon;
  }

  public Shopping() {

  }

}
