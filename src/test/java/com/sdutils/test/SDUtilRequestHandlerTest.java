package com.sdutils.test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;




@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc

public class SDUtilRequestHandlerTest {
	
	 // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_welcome() throws Exception {

        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/welcome"))
         				.andDo(print())
         				.andExpect(status().isOk())
         				.andReturn();
        
        assertEquals("Welcome to SDUtil Api - A group of Utility API for common functionalities", result.getResponse().getContentAsString());
         				

    }
    
    @Test
    
    public void test_invalid_input_error_response_case1() throws Exception {
    	
    mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/calendar/accounting/year/-1"))
  				.andDo(print())
  				.andExpect(status().isBadRequest())
  				.andExpect(jsonPath("$.status", equalTo("BAD_REQUEST")))
  				.andExpect(jsonPath("$.message", equalTo("Invalid Input")))
  				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("getAccountingCalendar.year: must be between 1 and 3000")));
  			
    }
 
    
   @Test
    
    public void test_invalid_input_error_response_case2() throws Exception {
    	
    mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/calendar/accounting/year/3002"))
  				.andDo(print())
  				.andExpect(status().isBadRequest())
  				.andExpect(jsonPath("$.status", equalTo("BAD_REQUEST")))
  				.andExpect(jsonPath("$.message", equalTo("Invalid Input")))
  				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("getAccountingCalendar.year: must be between 1 and 3000")));
		
    	
    }

   
   @Test
   
   public void test_invalid_input_error_response_case3() throws Exception {
   	
   mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/calendar/accounting/year/1.5"))
 				.andDo(print())
 				.andExpect(status().isBadRequest())
 				.andExpect(jsonPath("$.status", equalTo("BAD_REQUEST")))
 				.andExpect(jsonPath("$.message", equalTo("Invalid Input")))
 				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("Only whole numbers are allowed as input")));
		
   	
   }
   
   
  @Test
   
   public void test_invalid_input_error_response_case4() throws Exception {
   	
   mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/calendar/accounting/year//month/1"))
 				.andDo(print())
 				.andExpect(status().isNotFound());
   	
   }
   
   
   @Test
   
   public void test_positive_response_case1() throws Exception {
   	
	   mockMvc.perform(get("http://localhost:" + port + "/sdutils/api/calendar/accounting/year/2021/month/1"))
 				.andDo(print())
 				.andExpect(status().isOk())
 				.andExpect(jsonPath("$.fiscalYear", org.hamcrest.Matchers.equalTo(2021)))
 				.andExpect(jsonPath("$.months").isArray())
 				.andExpect(jsonPath("$.months",hasSize(1)))
	   			.andExpect(jsonPath("$.months[0].fiscalMonth", equalTo("January")))
	   			.andExpect(jsonPath("$.months[0].noOfWeeks", equalTo(4)))
	   			.andExpect(jsonPath("$.months[0].weeks").isArray())
	   			.andExpect(jsonPath("$.months[0].weeks[0].days", hasItems("2020-12-28","2020-12-29","2020-12-30","2020-12-31","2021-01-01","2021-01-02","2021-01-03")));
 				
	   
   }

}
