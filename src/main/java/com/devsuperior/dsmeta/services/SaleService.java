package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id).get();
		SaleMinDTO dto = new SaleMinDTO(entity);
		return dto;
	}

	public Page<SaleSellerDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate min = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);

		Page<SaleSellerDTO> result = repository.getReport(min, max, name, pageable);

		return result;
	}

	public List<SellerAmountDTO> getSummary(String minDate, String maxDate) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate min = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);

		List<SellerAmountDTO> result = repository.getSummary(min, max);

		return result;
	}


}
