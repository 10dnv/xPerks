package com.xperks.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String parseDate(Date date) {
        if (date == null) {
            return  null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
