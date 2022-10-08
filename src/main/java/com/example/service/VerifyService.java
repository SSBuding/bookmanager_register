package com.example.service;

public interface VerifyService {
    void sendVerifyCode(String mail);
    Boolean doVerify(String mail,String code);
}
