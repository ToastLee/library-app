package com.group.libraryapp.repositoy.fruit;

import com.group.libraryapp.dto.fruit.request.RecordSoldFruitRequest;
import com.group.libraryapp.dto.fruit.request.SaveFruitRequest;
import com.group.libraryapp.dto.fruit.response.GetFruitStatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FruitRepositoryV1 {

    private final JdbcTemplate jdbcTemplate;

    public FruitRepositoryV1(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SaveFruitRequest saveFruit(SaveFruitRequest request) {
        // 기본 상태를 'NOT_SOLD'로 설정
        request.setStatus("NOT_SOLD");

        String sql = "INSERT INTO fruits (name, warehousingDate, price, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(),
                request.getPrice(), request.getStatus());

        return request; // 저장된 과일 정보 반환
    }

    public boolean isFruitNotExist(String name) {
        String readSql = "SELECT * FROM Fruits WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();

    }
    public GetFruitStatResponse getFruitAmountByStat(String name) {
        String salesSql = "SELECT sum(price) FROM fruits WHERE status = 'SOLD' ";
        String notSalesSql = "SELECT sum(price) FROM fruits WHERE status = 'NOT_SOLD'";

        Long salesAmount = jdbcTemplate.queryForObject(salesSql, Long.class);
        Long notSalesAmount = jdbcTemplate.queryForObject(notSalesSql, Long.class);

        return new GetFruitStatResponse(salesAmount, notSalesAmount);
    }

    public ResponseEntity<Void> recordSolodFruit (RecordSoldFruitRequest request) {
        String sql ="UPDATE fruits SET status = 'SOLD' WHERE id = ?";
        int updatedRows = jdbcTemplate.update(sql, request.getId());

        if (updatedRows == 0) { // 만약 과일이 목록이 없다면
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
