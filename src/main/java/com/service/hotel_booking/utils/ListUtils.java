package com.service.hotel_booking.utils;

import java.util.List;

public class ListUtils {

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

}
