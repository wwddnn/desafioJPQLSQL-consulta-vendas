package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleSellerDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);


    @Query("SELECT com.devsuperior.dsmeta.entities.Sale(obj.seller.name, SUM(obj.amount) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :dateInic AND :dateFinal " +
            "GROUP BY obj.seller.name " )
    List<SellerAmountDTO> getSummary(LocalDate dateInic, LocalDate dateFinal);


}
