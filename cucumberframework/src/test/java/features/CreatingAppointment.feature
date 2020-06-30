Feature: HFC Smoke Testing
	
Scenario: HFC Valid Login Test Scenario
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
	Given open Add Appointment page
	When user fill data in Appointment page
	Then user saves the Appointment
	Given user opens Opportunity page
	When user fills the data
	Then user saves the screen
	Given user lands on Measurement page
	Then user fills the data to measurement
	Then user saves the measurement page
	Given user lands on Quotes page
	Then user save Quote page
	Given user edit the quotes
	Then user change the quote status as Accepted
	And finally user save the quote page
	Given lands on create Sales Order page
	Then user convert Sales Order into Mpo Order
	Given user save the MPO order page
	And close the browser at the end