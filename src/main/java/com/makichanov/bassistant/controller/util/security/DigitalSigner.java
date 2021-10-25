package com.makichanov.bassistant.controller.util.security;

public interface DigitalSigner {

    String encrypt(String data);

    String decrypt(String signedData);
}
