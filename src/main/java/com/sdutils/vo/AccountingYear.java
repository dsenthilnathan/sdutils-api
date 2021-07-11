package com.sdutils.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"fiscalYear", "months" })
public class AccountingYear implements Serializable {
	
	
	private static final long serialVersionUID = 1L;


	
	private int fiscalYear = 0;
	
	
	private List<AccountingMonth> months = null;

	public int getFiscalYear() {
		return this.fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public List<AccountingMonth> getMonths() {
		return this.months;
	}

	public void setMonths(List<AccountingMonth> months) {
		this.months = months ;
	}
	
	

}
