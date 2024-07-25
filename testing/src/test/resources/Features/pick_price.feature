@all
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
      
   
  @tag3
  Scenario: Pick random purchase price and proceed to select product and validate url components and validations on the first section of the Checkout page
  	Given I start the Driver for <browser_name> browser
  	And I access the <url>
    And I close the cookies
    And I wait for the price picker to show up
    When The price picker shows I choose a random value from it
    And I validate the choice selected is visible in the dropdown
    And I capture the values in this page that will be used in future validations
    Then I select the product
    And I wait for the page to load completly
    And I validate that the url contains <value> and the same utm_source as the previous page
    Then I validate that the current price and product name are in accordance with the ones shown on the previous page
    And I validate the <provider>, the Contract Start Date and <contract_renewal>
    Then I close the browser and driver
        
		Examples: 
       | url																																				| browser_name | value 																 | provider   | contract_renewal |
       | "https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend" | "Edge"			 | "/device-protection/checkout/payment" | "bolttech" | "Monthly"				 |
       
       
 