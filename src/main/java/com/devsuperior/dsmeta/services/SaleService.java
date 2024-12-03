package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		//Calculo para data MAXIMA
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate maxDigitada = LocalDate.parse(maxDate);
		LocalDate max = maxDate.equals("") ? today : maxDigitada;

		//Calculo para data MINIMA
		LocalDate min1AnoAtras = today.minusYears(1L);
		LocalDate minDigitada = LocalDate.parse(minDate);
		LocalDate min = minDate.equals("") ? min1AnoAtras : minDigitada;

		Page<SaleSellerDTO> result = repository.getReport(min, max, name, pageable);

		return result;
	}

	public List<SellerAmountDTO> getSummary(String dateInic, String dateFinal) {
		//Calculo para data FINAL
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate finalDigitada = LocalDate.parse(dateFinal);
		LocalDate fin = dateFinal.equals("") ? today : finalDigitada;

		//Calculo para data INICIAL
		LocalDate min1AnoAtras = today.minusYears(1L);
		LocalDate inicDigitada = LocalDate.parse(dateInic);
		LocalDate inic = dateInic.equals("") ? min1AnoAtras : inicDigitada;

		List<SellerAmountDTO> result2 = repository.getSummary(inic, fin);

		return result2;
	}


}
