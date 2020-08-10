Feature: HFC Smoke Testing 2

Scenario: Procurment Dashboard data load for 180 days,,Individual cancel order and again convert into open order.
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Then user lands on Procurment dashboard
	And Individual cancel Vendor PO
	And convert cancel mpo order into open order

Scenario: Procurment Dashboard:Load data for 180 days,Whole cancel order and again convert into open order.
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	Then user lands on Procurment dashboard
	And Cancel the MPO order
	And convert cancel mpo order into open order
	And close the browser at the end
	
Scenario: Create Appointment for All Day event and Confirm the appointment in Home page
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

Scenario: Create Appointment for one week recurrence and Verify the recurrence series in calendar page
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
	
Scenario: Create new Add Calendar,Verify then Delete the Calendar
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
	
Scenario: Create new Task from Appointment and verify the task created in Home Page
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
	
Scenario: Verify Sales Summary report works fine:Load data,Download excel file,Sorting,Pagination and Filter by First Name
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	And user navigates to Reports and loads data for Sales Summary report
	And close the browser at the end
	
Scenario: Verify Sales and Purchasing Detail report works fine:Load data,Download excel file,Sorting,Pagination and Filter by Territory Name
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	And user navigates to Reports and loads data for Sales Detail report
	And close the browser at the end
	
Scenario: Verify Sales Tax report works fine:Load data,Download excel file,Sorting,Pagination and Filter by Zip Code
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	And user navigates to Reports and loads data for Sales Tax report
	And close the browser at the end
	
Scenario: Verify Sales Tax Summary report works fine:Load data,Download excel file,Sorting,Pagination and Filter by Zip Code
	Given Open Chrome browser and url
	Given user enters username and password
	Then user clicks on login button
	And user navigates to Reports and loads data for Sales Tax Summary report
	And close the browser at the end
	
Scenario: Measurement upload image which covers negative case too
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
	Then user fill data and upload image in measurement
	Then user saves the measurement page
	
