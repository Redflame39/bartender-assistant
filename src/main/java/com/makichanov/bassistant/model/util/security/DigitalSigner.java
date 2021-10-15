package com.makichanov.bassistant.model.util.security;

public interface DigitalSigner {

    String encrypt(String data);

    String decrypt(String signedData);
}
