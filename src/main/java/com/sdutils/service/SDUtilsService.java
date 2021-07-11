package com.sdutils.service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.threeten.extra.Weeks;
import org.threeten.extra.chrono.AccountingChronology;
import org.threeten.extra.chrono.AccountingChronologyBuilder;
import org.threeten.extra.chrono.AccountingDate;
import org.threeten.extra.chrono.AccountingYearDivision;

import com.sdutils.vo.AccountingMonth;
import com.sdutils.vo.AccountingWeek;
import com.sdutils.vo.AccountingYear;

/**
 * 
 * @author Senthilnathan
 * 
 * This is the Service implementation for the SDUtilService
 * 
 */

@Service

public class SDUtilsService implements SDUtilsServiceInterface{
	
	private final Logger logger = LoggerFactory.getLogger(SDUtilsService.class);
	

	@Override
	public String getWelcomeMessage() {
		
		logger.info("getWelcomeMessage - Entry");
		
		return "Welcome to SDUtil Api - A group of Utility API for common functionalities";
	}

	@Override
	@Cacheable(value="calendarCache",key="{#year,#month}")

	public AccountingYear getAccountingCalender(@Range(min=1, max=3000) Integer year , Integer month) {
		
		logger.info("getAccountingCalender - Entry");
		
		AccountingChronology acctChrono = new AccountingChronologyBuilder().endsOn(DayOfWeek.SUNDAY)
				.inLastWeekOf(Month.DECEMBER).withDivision(AccountingYearDivision.QUARTERS_OF_PATTERN_4_4_5_WEEKS)
				.leapWeekInMonth(12).toChronology();

		AccountingYear accYr = new AccountingYear();
		accYr.setFiscalYear(year);

		if(month!=null) {
			accYr.setMonths(getAccountingCalendarMonths(acctChrono, year,month));
		}else {
			accYr.setMonths(getAccountingCalendarMonths(acctChrono, year));
		}

		return accYr;

	}
	
	
	private  List<AccountingMonth> getAccountingCalendarMonths(AccountingChronology acctChrono, int year) {
		
		logger.info("getAccountingCalendarMonths - Year - Entry");
		
		List<AccountingMonth> monthsList = new ArrayList<AccountingMonth>();

		for (int month = 1; month <= 12; month++) {

			AccountingMonth accMonth = new AccountingMonth();

			Month mon = Month.of(month);

			accMonth.setFiscalMonth(mon.getDisplayName(TextStyle.FULL, Locale.getDefault()));

			AccountingDate start = acctChrono.date(year, month, 1);
			AccountingDate end = start.with(TemporalAdjusters.lastDayOfMonth());

			accMonth.setNoOfWeeks(Weeks.between(start, end).getAmount() + 1);

			accMonth.setWeeks(getAccountingCalendarWeeks(start, end));

			System.out.println(start.format(DateTimeFormatter.ISO_LOCAL_DATE) + "  "
					+ end.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + (Weeks.between(start, end).getAmount() + 1));

			monthsList.add(accMonth);
		}

		return monthsList;
		
		
	}
	
	
	private List<AccountingMonth> getAccountingCalendarMonths(AccountingChronology acctChrono, int year, int month) {
		
		logger.info("getAccountingCalendarMonths - Months - Entry");
		
		List<AccountingMonth> monthsList = new ArrayList<AccountingMonth>();

		AccountingMonth accMonth = new AccountingMonth();

		Month mon = Month.of(month);

		accMonth.setFiscalMonth(mon.getDisplayName(TextStyle.FULL, Locale.getDefault()));

		AccountingDate start = acctChrono.date(year, month, 1);
		AccountingDate end = start.with(TemporalAdjusters.lastDayOfMonth());

		accMonth.setNoOfWeeks(Weeks.between(start, end).getAmount() + 1);

		System.out.println(start.format(DateTimeFormatter.ISO_LOCAL_DATE) + "  "
				+ end.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + (Weeks.between(start, end).getAmount() + 1));

		accMonth.setWeeks(getAccountingCalendarWeeks(start, end));

		monthsList.add(accMonth);

		return monthsList;
		
	}
	
	
	
	private List<AccountingWeek> getAccountingCalendarWeeks(AccountingDate start,  AccountingDate end) {
		
		logger.info("getAccountingCalendarWeeks - Entry");
		
		ArrayList<AccountingWeek> weeks = new ArrayList<AccountingWeek>();

		int weekNbr = 1;

		while (start.isBefore(end)) {

			AccountingWeek week = new AccountingWeek();

			week.setWeekNumber(weekNbr);

			ArrayList<String> days = new ArrayList<String>();

			for (int i = 1; i <= 7; i++) {
				System.out.println(start.format(DateTimeFormatter.ISO_LOCAL_DATE));
				days.add(start.format(DateTimeFormatter.ISO_LOCAL_DATE));
				start = start.plus(1, ChronoUnit.DAYS);
			}

			week.setDays(days);

			weeks.add(week);

			weekNbr++;

		}

		return weeks;
		
	}
	
	
	
	public static void main(String[] args) {
		
		
	}


}

	