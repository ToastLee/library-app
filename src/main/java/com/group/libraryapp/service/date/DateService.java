package com.group.libraryapp.service.date;

import com.group.libraryapp.dto.date.response.DateResponse;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class DateService {

    public DateResponse getDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        DateResponse response = new DateResponse();
        response.setDayOfTheWeek(dayOfWeek.toString());

        return response;
    }
}
