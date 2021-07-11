package com.sdutils.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"fiscalMonth", "noOfWeeks", "weeks" })
public class AccountingMonth implements Serializable{
	
	

	private static final long serialVersionUID = 1L;

	
	private String fiscalMonth = null;
	

	private int noOfWeeks = 0;

	private List<AccountingWeek> weeks = null;

	public String getFiscalMonth() {
		return this.fiscalMonth;
	}

	public void setFiscalMonth(String fiscalMonth) {
		this.fiscalMonth = fiscalMonth;
	}

	public List<AccountingWeek> getWeeks() {
		return this.weeks;
	}

	public void setWeeks(List<AccountingWeek> weeks) {
		this.weeks = weeks;
	}

	public int getNoOfWeeks() {
		return this.noOfWeeks;
	}

	public void setNoOfWeeks(int noOfWeeks) {
		this.noOfWeeks = noOfWeeks;
	}
	
	
	

}
