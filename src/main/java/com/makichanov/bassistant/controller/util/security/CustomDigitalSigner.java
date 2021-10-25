package com.makichanov.bassistant.controller.util.security;

import java.math.BigInteger;

public class CustomDigitalSigner implements DigitalSigner {

    private static final CustomDigitalSigner instance = new CustomDigitalSigner();
    private final int NUMBER_OF_SHIFTS = 10;

    private CustomDigitalSigner() {}

    public static CustomDigitalSigner getInstance() {
        return instance;
    }

    @Override
    public String encrypt(String data) {
        BigInteger dataNumber = new BigInteger(data, 10);
        dataNumber = dataNumber.shiftLeft(NUMBER_OF_SHIFTS);
        return dataNumber.toString();
    }

    @Override
    public String decrypt(String signedData) {
        BigInteger dataNumber = new BigInteger(signedData, 10);
        dataNumber = dataNumber.shiftRight(NUMBER_OF_SHIFTS);
        return dataNumber.toString();
    }
}
