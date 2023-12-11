package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@CrossOrigin("*")
public class GeneralController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getServerStatus() {
        ZonedDateTime currentDateTimeUtc = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy, hh:mm:ss a");
        String formattedDateTime = currentDateTimeUtc.format(formatter);

        Map<String, Object> data = new HashMap<>();
        data.put("status", "SERVER and Systems are Up & Running.");
        data.put("dateTime", formattedDateTime);

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

}
