package vn.td.clock.shop.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String USERNAME_REGEX = "^[a-z][a-z0-9_]{6,16}$";
    public static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    public static final String NAME_REGEX = "^[A-Z]+([ ]?[A-Za-z])+$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^([a-zA-Z0-9!#$%&'*+\\/=.?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[gmail][a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+com([a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9]))?)$";
    public static final String NAME_PRODUCT_REGEX = "^(Dong ho|DONG HO|dong ho|Dong Ho)( ?[-A-Za-z0-9])+$";
    public static final String ADDRESS_REGEX = "^\\d+( ?\\w+ ?[\\,\\-\\/]*)+$";

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }
    public static boolean isUserNameValid(String username) {
        return Pattern.compile(USERNAME_REGEX).matcher(username).matches();
    }

    public static boolean isNameValid(String name) {
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    public static boolean isNameProductValid(String nameProduct) {
        return Pattern.compile(NAME_PRODUCT_REGEX).matcher(nameProduct).matches();
    }
    public static boolean isAddressValid(String address) {
        return Pattern.compile(ADDRESS_REGEX).matcher(address).matches();
    }

}

