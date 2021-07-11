package com.sdutils.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"weekNumber", "days"})
public class AccountingWeek implements Serializable {


	private static final long serialVersionUID = 1L;
	

	private int weekNumber = 0;
	
	
	private List<String> days = null;

	public int getWeekNumber() {
		return this.weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public List<String> getDays() {
		return this.days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}
	
	

}
