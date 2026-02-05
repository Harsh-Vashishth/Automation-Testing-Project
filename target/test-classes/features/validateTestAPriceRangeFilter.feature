Feature: Validating price by filter  functionality

	Scenario Outline: Validating price by filter is working correctly
		Given user is on the search screen
		When dropdown Filter by price is clicked
		And Range <range> is selected
		Then price is validated
		
		Examples:
		|range  |
		|"Below ₹60"|
		|"₹60 - ₹70"|
		|"Above ₹70"|