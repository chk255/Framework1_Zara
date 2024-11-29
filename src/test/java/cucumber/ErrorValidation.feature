
@tag
Feature: Validate Error msg at login page.
 

  @errorvalidation
  Scenario Outline: Error Message Validation at Login Page with wrong input Data.
    Given I landed on ecommerce login page
    When Login With incorrect Username <username> and Password <password> 
    Then Validate "Incorrect email or password." displayed on Login Page.

    Examples: 
      | username             | password      |
      | chk255@gmail.com     | Ckumar@2428   |
