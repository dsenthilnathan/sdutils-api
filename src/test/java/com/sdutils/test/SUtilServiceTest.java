package com.sdutils.test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sdutils.service.SDUtilsService;
import com.sdutils.vo.AccountingYear;

@SpringBootTest
public class SUtilServiceTest {
	
	
	
	@Autowired
	SDUtilsService service;

	@DisplayName("Test - Welcome Message")

	@Test
	void testGet() {
		assertEquals("Welcome to SDUtil Api - A group of Utility API for common functionalities",
				service.getWelcomeMessage());
	}

	@DisplayName("Test - getAccountingCalendar - 1")

	@Test
	void testAccountingCalendar_case1() {

		int inputYear = 2021;


		AccountingYear accntYr = service.getAccountingCalender(inputYear,null);

		assertEquals(accntYr.getFiscalYear(), inputYear);

		assertEquals(accntYr.getMonths().size(), 12);
		
		assertEquals(accntYr.getMonths().get(11).getFiscalMonth(), "December");
		
		assertEquals(accntYr.getMonths().get(11).getNoOfWeeks(), 5);
		
		assertThat(accntYr.getMonths().get(11).getWeeks().get(4).getDays(), hasItems("2021-12-20","2021-12-21","2021-12-22","2021-12-23","2021-12-24","2021-12-25","2021-12-26"));

	}

}
