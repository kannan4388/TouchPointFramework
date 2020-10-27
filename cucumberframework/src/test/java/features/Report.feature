Feature: Report module

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