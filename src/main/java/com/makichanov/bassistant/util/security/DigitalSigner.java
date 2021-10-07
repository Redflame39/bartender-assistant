package com.makichanov.bassistant.util.security;

public interface DigitalSigner {

    String encrypt(String data);

    String decrypt(String signedData);
}
