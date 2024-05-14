package com.group.libraryapp.service.calculator;

import com.group.libraryapp.dto.calculator.request.CalculationRequest;
import com.group.libraryapp.dto.calculator.response.CalculationResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public CalculationResponse calulate(CalculationRequest request) {
        int num1 = request.getNum1();
        int num2 = request.getNum2();

        CalculationResponse response = new CalculationResponse();
        response.setAdd(num1 + num2);
        response.setMinus(num1 - num2);
        response.setMultiply(num1 * num2);

        return response;

    }
}
