package com.johann.ecommerce.service;

public interface UmsMemberService {

    String generateAuthCode(String phone);

    Boolean verifyAuthCode(String phone, String authCode);

}
