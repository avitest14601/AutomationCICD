@tag
Feature: Error Validation Test
  I want to use this template for my feature file

Background:
	Given I landed on ecommerce website
	
@tag2
Scenario Outline: Title of your scenario outline
  Given Logged in with useremail <email> and password <password>
  Then "Incorrect email or password." message is displayed

Examples: 
  | email                  | password        |
  | avinash0062@gmail.com  | avinash0062@123 |
