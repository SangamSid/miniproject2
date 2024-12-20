package com.ashokit.project2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashokit.project2.dto.QuoteAPIResponseDTO;

@Service
public class DashboardServiceImpl implements DashboardService {

	
	private String quoteApiUrl="https://dummyjson.com/quotes/random";
	
	@Override
	public QuoteAPIResponseDTO getQuote() {
		RestTemplate rt=new RestTemplate();
		
	ResponseEntity<QuoteAPIResponseDTO>quoteEntity	=rt.getForEntity(quoteApiUrl, QuoteAPIResponseDTO.class);
		
	QuoteAPIResponseDTO quoteBody=	quoteEntity.getBody();
		return quoteBody;
	}

}
