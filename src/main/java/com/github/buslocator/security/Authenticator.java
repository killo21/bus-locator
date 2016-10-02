package com.github.buslocator.security;

import com.github.buslocator.model.Role;
import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticator {

  private final MessageDigest md;
  // TODO(Dmitry): move md5 hashcodes into a secure configuration file
  private final String dmitrysPasswordMd5 = "e10adc3949ba59abbe56e057f20f883e";
  private final String adminsPasswordMd5 = "c33367701511b4f6020ec61ded352059";
  private final String driversPasswordMd5 = "a8698009bce6d1b8c2128eddefc25aad";

  public Authenticator() {
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

  public Role getRole(String username, String password) {
    String md5Password = convertPasswordToMd5(password);
    if (username.equals("Dmitry") && md5Password.equals(dmitrysPasswordMd5)) {
      return Role.USER;
    } else if (username.equals("Admin") && md5Password.equals(adminsPasswordMd5)) {
      return Role.ADMIN;
    } else if (username.equals("Driver") && md5Password.equals(driversPasswordMd5)) {
      return Role.DRIVER;
    }else{
      return null;
    }
  }

  private String convertPasswordToMd5(String password) {
    byte[] md5sum = md.digest(password.getBytes());
    return String.valueOf(Hex.encodeHex(md5sum));
  }
}
