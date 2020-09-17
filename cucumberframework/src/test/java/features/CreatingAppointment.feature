Feature: Appointment module
	
Scenario: 1.Create Appointment for All Day event and Confirm the appointment in Home page
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

Scenario: 2.Create Appointment for one week recurrence and Verify the recurrence series in calendar page
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
	
Scenario: 3.Create new Add Calendar,Verify then Delete the Calendar
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
	
Scenario: 4.Create new Task from Appointment and verify the task created in Home Page
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
	
	