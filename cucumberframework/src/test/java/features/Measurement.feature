@Measurement
Feature: This Feature contains Scenarios for HFC Measurement Integration

Scenario: 1 This scenario is to validate Measurement_Page by removing already added rows,again add one row and validate added row.

	Given Open Chrome browser and url         
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on qualify page
	Then user edits the qualify page
	Then user enters data to some fields
	And user saves the qualify page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Given user opens Opportunity page
	When user fills the data
	Then user saves the screen
	Given user lands on Measurement page
	Then user fills the data to measurement
	Then user saves the measurement page
	Then user removes filled rows in the measurement page
	Then user adds new row in the measurement page
	Then user saves the measurement page
	And user validates row count in the measurement page
	And close the browser at the end
				
	Scenario: 2 This scenario is to add rows in more than one page in Measurement_Page with image and validate the same.
	
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on qualify page
	Then user edits the qualify page
	Then user enters data to some fields
	And user saves the qualify page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Given user opens Opportunity page
	When user fills the data
	Then user saves the screen
	Given user lands on Measurement page
	Then user fills row data in the measurement page
	Then user saves the measurement page
	And user validates row count in the measurement page
	And close the browser at the end
	
	Scenario: 3 Measurement upload image which covers negative flow
	
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on qualify page
	Then user edits the qualify page
	Then user enters data to some fields
	And user saves the qualify page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Given user opens Opportunity page
	When user fills the data
	Then user saves the screen
	Given user lands on Measurement page
	Then user fill data and upload image in measurement
	Then user saves the measurement page
	And close the browser at the end
	
	Scenario: 4 This scenario is to copy rows in Measurement_Page using copy_Line feature available by which user can copy desire row

	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on qualify page
	Then user edits the qualify page
	Then user enters data to some fields
	And user saves the qualify page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Given user opens Opportunity page
	When user fills the data
	Then user saves the screen
	Given user lands on Measurement page
	Then user fills the data to measurement
	Then user copies rows in the measurement page
	And user validates row count in the measurement page
	Then user saves the measurement page
	And close the browser at the end
	


	