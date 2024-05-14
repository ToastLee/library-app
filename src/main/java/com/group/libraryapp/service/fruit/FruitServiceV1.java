package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.fruit.request.RecordSoldFruitRequest;
import com.group.libraryapp.dto.fruit.request.SaveFruitRequest;
import com.group.libraryapp.dto.fruit.response.CountFruitResponse;
import com.group.libraryapp.dto.fruit.response.GetFruitStatResponse;
import com.group.libraryapp.repositoy.fruit.FruitRepositoryV1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceV1 {

    private final FruitRepositoryV1 fruitRepository;

    public FruitServiceV1(FruitRepositoryV1 fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public SaveFruitRequest saveFruit(SaveFruitRequest request) {
        fruitRepository.saveFruit(request);
        return request;
    }

    public GetFruitStatResponse getFruitStat(String name) {
        if (fruitRepository.isFruitNotExist(name)) {
            throw new IllegalArgumentException();
        }

        return fruitRepository.getFruitAmountByStat(name);
    }

    public ResponseEntity<Void> recordSoldFruit(RecordSoldFruitRequest request) {
        return fruitRepository.recordSolodFruit(request);
    }


}
