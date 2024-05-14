package com.group.libraryapp.service.calculator;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SumService {

    public int sumNumbers(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }
}
