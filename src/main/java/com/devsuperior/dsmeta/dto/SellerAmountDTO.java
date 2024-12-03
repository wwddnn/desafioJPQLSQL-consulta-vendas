package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

import java.util.List;

public class SellerAmountDTO {

    private String name;
    private Long sum;

   public SellerAmountDTO(){

   }

    public SellerAmountDTO(String name, Long sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public Long getSum() {
        return sum;
    }
}
