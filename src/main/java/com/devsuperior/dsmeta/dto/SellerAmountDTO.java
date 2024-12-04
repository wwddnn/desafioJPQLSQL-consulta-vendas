package com.devsuperior.dsmeta.dto;

public class SellerAmountDTO {

    private String name;
    private Double sum;

   public SellerAmountDTO(){

   }

    public SellerAmountDTO(String name, Double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public Double getSum() {
        return sum;
    }
}
