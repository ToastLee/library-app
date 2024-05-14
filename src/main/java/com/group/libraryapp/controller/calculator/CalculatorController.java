package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculationRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.calculator.request.SumNumbersRequest;
import com.group.libraryapp.dto.calculator.response.CalculationResponse;
import com.group.libraryapp.service.calculator.CalculatorService;
import com.group.libraryapp.service.calculator.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody //POST에서 JSON을  파싱해서 객체로 사용시
                                      CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();

    }

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService, SumService sumService) {
        this.calculatorService = calculatorService;
        this.sumService = sumService;
    }

    @GetMapping("/api/v1/calc")
    public CalculationResponse calculate(CalculationRequest request) {
        return calculatorService.calulate(request);
    }
    private final SumService sumService;

    @PostMapping("/api/v1/sum-numbers")
    public ResponseEntity<Integer> sumNumbers(@RequestBody SumNumbersRequest request){
        List<Integer> numbers = request.getNumbers();
        int sum = sumService.sumNumbers(numbers);
        return ResponseEntity.ok().body(sum);
    }



}
