@tag
Feature: Purachase the order from the ecommerce website

Background:
	Given I landed on ecommerce website
	
@Regression
Scenario Outline: Positive test of purchasing the order
  Given Logged in with useremail <email> and password <password>
  When I add product <productname> in the cart
  And Checkout the product <productname> and submit the order with country as <country>
  Then "Thankyou for the order." message is displayed on the ConfirmationPage

Examples: 
  | email                  | password      | productname     | country |
  | avinash0062@gmail.com  | Avinash0062@1 | ADIDAS ORIGINAL | India   |