package com.moura.comicShop.coupon;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Entity
@Table(name = "coupon")
public class Coupon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "code")
  private String code;
  @Column(name = "discount")
  private String discount;
  @Column(name = "expiration_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar expirationDate;

  public Coupon(String discount, int days) {
    this.code = generatedCode();
    this.discount = discount;
    this.expirationDate = expirationDate(days);
  }

  public Coupon() {
  }

  public void setExpirationDate(int days) {
    this.expirationDate = expirationDate(days);
  }

  private String generatedCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    int length = 6; // Define o tamanho do código desejado

    for (int i = 0; i < length; i++) {
      int index = random.nextInt(characters.length());
      char randomChar = characters.charAt(index);
      sb.append(randomChar);
    }
    return sb.toString();
  }

  private Calendar expirationDate(int days) {
    TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
    Calendar calendar = Calendar.getInstance(timeZone);
    calendar.add(Calendar.DATE, days);
    return calendar;
  }
}
