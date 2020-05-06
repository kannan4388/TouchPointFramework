@web
Feature: HFC Smoke Testing
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