package com.group.libraryapp.controller.date;

import com.group.libraryapp.dto.date.request.DateRequest;
import com.group.libraryapp.dto.date.response.DateResponse;
import com.group.libraryapp.service.date.DateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {

    private final DateService dateService;


    public DateController(DateService dateService){
        this.dateService = dateService;
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DateResponse getDayOfWeek(DateRequest request) {
        return dateService.getDayOfWeek(request.getDate());
    }
}
