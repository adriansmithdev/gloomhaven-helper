package com.subjecttochange.ghhelper.persistence.model.helpers;

import java.util.Random;

public class RoomHashGenerator {

    private static final int HASHLENGTH = 7;
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateNewHash() {
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < HASHLENGTH) { // length of the random string.
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
