package com.makichanov.bassistant.controller.util.security;

/**
 * The interface Digital signer is used to generate secure signatures for account verification and password restoring.
 */
public interface DigitalSigner {

    /**
     * Encrypts string data which should be a number value.
     *
     * @param data the data to encrypt
     * @return signature for passed data
     */
    String encrypt(String data);

    /**
     * Decrypts string which is an encrypted signature.
     *
     * @param signedData the signature
     * @return decrypted signature
     */
    String decrypt(String signedData);
}
