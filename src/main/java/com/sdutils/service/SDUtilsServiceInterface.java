package com.sdutils.service;

import com.sdutils.vo.AccountingYear;

public interface SDUtilsServiceInterface {
	
	String getWelcomeMessage();
	
	AccountingYear getAccountingCalender(Integer year, Integer month);
	
}
