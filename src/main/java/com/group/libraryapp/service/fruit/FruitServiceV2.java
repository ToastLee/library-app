package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.fruit.FruitInfo;
import com.group.libraryapp.dto.fruit.request.RecordSoldFruitRequest;
import com.group.libraryapp.dto.fruit.request.SaveFruitRequest;
import com.group.libraryapp.dto.fruit.response.CountFruitResponse;
import com.group.libraryapp.dto.fruit.response.GetFruitStatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceV2 {

    private final FruitRepository fruitRepository;

    public FruitServiceV2(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public SaveFruitRequest saveFruit(SaveFruitRequest request) {
         fruitRepository.save(new Fruit(
                 request.getName(),
                 request.getWarehousingDate(),
                 request.getPrice(),
                 request.getStatus()));
         return request;
    }

    public GetFruitStatResponse getFruitStat(String name) {
        // 과일 존재 여부 확인
        List<Fruit> fruit = fruitRepository.findByName(name);
        if (fruit == null) {
            throw new IllegalArgumentException("해당 과일이 없습니다.");
        }

        // 쿼리의 결과 가져오기
        Long salesAmount = fruitRepository.sumPriceByNameAndStatus(name, "SOLD");
        Long notSalesAmount = fruitRepository.sumPriceByNameAndStatus(name, "NOT_SOLD");

        return new GetFruitStatResponse(
                salesAmount != null ? salesAmount : 0L, notSalesAmount != null ? notSalesAmount : 0L);
    }


    public ResponseEntity<Void> recordSoldFruit(RecordSoldFruitRequest request) {
        Fruit fruit = fruitRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        fruit.setStatus("SOLD");
        fruitRepository.save(fruit);
        return ResponseEntity.ok().build();
    }

    public CountFruitResponse countFruit(String name) {
        long count = fruitRepository.countByName(name);
        return new CountFruitResponse(count);

    }

    //비즈니스 로직
    public List<FruitInfo> getFruitList(String option, long price) {
        List<Fruit> fruits = getFruitsByOption(option, price);
        return convertToFruitInfoList(fruits);
    }

    //JPA로직, 예외처리
    private List<Fruit> getFruitsByOption(String option, long price) {
        if ("GTE".equals(option)) {
            return fruitRepository.findByPriceGreaterThanEqual(price);
        } else if ("LTE".equals(option)) {
            return fruitRepository.findByPriceLessThanEqual(price);
        } else {
            throw new IllegalArgumentException("GTE와 LTE 중 하나를 입력하세요");
        }
    }

    //FruitInfo 묶어서 FruitList 로 만들기
    private FruitInfo convertToFruitInfo(Fruit fruit) {
        return new FruitInfo(fruit.getName(), fruit.getPrice(), fruit.getWarehousingDate());
    }

    //FruitInfo 들 가져오기
    private List<FruitInfo> convertToFruitInfoList(List<Fruit> fruits) {
        return fruits.stream()
                .map(this::convertToFruitInfo)
                .collect(Collectors.toList());
    }
}
