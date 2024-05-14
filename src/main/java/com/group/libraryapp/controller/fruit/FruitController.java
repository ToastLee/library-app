package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.FruitInfo;
import com.group.libraryapp.dto.fruit.request.RecordSoldFruitRequest;
import com.group.libraryapp.dto.fruit.request.SaveFruitRequest;
import com.group.libraryapp.dto.fruit.response.CountFruitResponse;
import com.group.libraryapp.dto.fruit.response.GetFruitStatResponse;
import com.group.libraryapp.service.fruit.FruitServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitController {

    private final FruitServiceV2 fruitService;

    public FruitController(FruitServiceV2 fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public SaveFruitRequest saveFruit(@RequestBody SaveFruitRequest request) {
        return fruitService.saveFruit(request);
    }

    @PutMapping("/api/v1/fruit")
    public ResponseEntity<Void> recordSoldFruit(@RequestBody RecordSoldFruitRequest request){
        return fruitService.recordSoldFruit(request);
    }

    @GetMapping("/api/v1/fruit/stat")
    public GetFruitStatResponse getFruitStat(@RequestParam String name) {
        return fruitService.getFruitStat(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public CountFruitResponse countFruit(@RequestParam String name) {
        return fruitService.countFruit(name);
    }

    @GetMapping("/api/v1/fruit/list")
    public List<FruitInfo> getFruitList(@RequestParam String option, @RequestParam long price) {
        return fruitService.getFruitList(option, price);
    }

}
