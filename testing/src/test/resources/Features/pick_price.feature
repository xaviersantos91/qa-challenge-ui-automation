Feature: Pick Purchase Price

  @tag1
  Scenario: Pick random purchase price
  	Given I start the Driver for <browser_name> browser
  	And I access the <url>
    And I close the cookies
    And I wait for the price picker to show up
    When The price picker shows I choose a random value from it
    And I validate the choice selected is visible in the dropdown
    Then I close the browser and driver
        
		Examples: 
       | url																																				| browser_name |
       | "https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend" | "Edge"			 |
      
  @tag2
  Scenario Outline: Pick given purchase price
  	Given I start the Driver for <browser_name> browser
  	And I access the <url>
    And I close the cookies
    And I wait for the price picker to show up
    When The price picker shows I choose a <value> from it 
    And I validate the choice selected is visible in the dropdown
		Then I close the browser and driver
		
    Examples: 
      | url 																																			 | browser_name |   value 	|#value needs to be in the format equal to the app
      | "https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend" | "Edge"			  |   "3,000" |