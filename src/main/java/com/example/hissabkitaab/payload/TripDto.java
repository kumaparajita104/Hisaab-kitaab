package com.example.hissabkitaab.payload;

import lombok.Data;

import java.util.Date;
@Data
public class TripDto {
    Long id;
    private String name;
    private int days;
    private String startDate;
    private String endDate;
    Long groupId;
    int f=0;

}
