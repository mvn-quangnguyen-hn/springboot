package com.bai3.todo;

import org.springframework.http.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String err_status, String message, Object ResponseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", err_status);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        map.put("timestamp", sdf.format(new Date()));

        map.put("message", message);

        if (ResponseObj != null) {
            map.put("subErrors", ResponseObj);
        }

        Map<String, Object> map2 = new HashMap<>();
        map2.put("apierror",map);

        return new ResponseEntity<Object>(map2,status);
    }
}
