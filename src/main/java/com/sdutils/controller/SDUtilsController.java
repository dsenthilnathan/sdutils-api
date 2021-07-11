package com.sdutils.controller;


import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdutils.service.SDUtilsService;
import com.sdutils.vo.AccountingYear;



@RestController
@Validated
@RequestMapping("/sdutils/api")
public class SDUtilsController {
	
	@Autowired
	
	SDUtilsService sdUtil;
	
	
	private final Logger logger = LoggerFactory.getLogger(SDUtilsController.class);
	
	
	@GetMapping( value = {"/calendar/accounting/year/{year}/month/{month}","/calendar/accounting/year/{year}"}, produces= {"application/json", "application/xml"})
	
	AccountingYear getAccountingCalendar(@PathVariable(required=true , name = "year")  @Range(min=1, max=3000) Integer year,
			@PathVariable (required=false , name = "month") @Range(min=1, max=12) Integer month) {
		 
		logger.info("getAccountingCalendar - Entry");
		
		
		return sdUtil.getAccountingCalender(year,month);
		
		
		
	}
	
	
	
	@GetMapping( value = "/welcome", produces= {"application/json", "application/xml"})
	
	String getWelcomeMessage () {
		 
		logger.info("getWelcome Message - Entry");
		
		return sdUtil.getWelcomeMessage();
		
		
	}

}
