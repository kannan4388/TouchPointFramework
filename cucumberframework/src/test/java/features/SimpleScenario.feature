@web
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
	
Scenario: Procurment Dashboard data load for 180 days

	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Then user lands on Procurment dashboard
	And Individual cancel Vendor PO
	And convert cancel mpo order into open order
	And close the browser at the end
	
Scenario: Procurment Dashboard data load for 180 days
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Then user lands on Procurment dashboard
	And Cancel the MPO order
	And convert cancel mpo order into open order
	And close the browser at the end
	
Scenario: Create Appointment for All Day event and Confirm it in Home page
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Then Open Add appointment from Home page
	When user fill data in Appointment page
	Then user saves the Appointment and return to Account
	Then lands on Home page
	And verify created account in Home page
	And close the browser at the end

Scenario: Create Appointment and Verify the recurrence series by next day in calendar page
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Then Open Add appointment from Home page
	When user fill data in Appointment page
	Then check the created appointment present in calendar page and edit it
	Then Fill Recurrence data
	Then View the Appointment by Next Day and Confirm all recurrence present
	And close the browser at the end
	
Scenario: Create new Add Calendar and Delete the same in Manage Calendar
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Then Create new Add Calendar
	And Then verify the added new calendar
	And close the browser at the end
	
Scenario: Create new Task from Appointment and verify task has been created successsfully in Home Page
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Given user clicks the sale icon
	Given user lands on Lead page
	Given user enters all mandatory fields data
	Then user saves the lead page
	Given user lands on convert lead page
	Given user fills data
	Then user convert it as Lead
	Then Create new Task
	Then lands on Home page
	And Verify the created task present in Home Page
	And close the browser at the end
	
Scenario: Reports functionality testing
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	And user navigates to Reports and loads data for Sales Detail report
	And close the browser at the end
	
