package com.alwinsimon.UserManagementJavaSpringBoot.Controller;

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
    public Map<String, String> getServerStatus() {

        // Get the current date and time in UTC
        ZonedDateTime currentDateTimeUtc = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy, hh:mm:ss a");
        String formattedDateTime = currentDateTimeUtc.format(formatter);

        // Create a Map for the response
        Map<String, String> response = new HashMap<>();
        response.put("status", "SERVER and Systems are Up & Running.");
        response.put("dateTime", formattedDateTime);

        return response;
    }
}
