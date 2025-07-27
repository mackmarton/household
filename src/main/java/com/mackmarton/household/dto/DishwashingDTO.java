package com.mackmarton.household.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DishwashingDTO {

    private Integer id;
    private LocalDateTime time;
    private String name;

}
