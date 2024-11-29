
@tag
Feature: Purchase the Order from ecommerce website
  I want to use this template for my feature file

 Background: 
  Given I landed on ecommerce login page

  @regression
  Scenario Outline: Submit Odrer with one Product and validate order success page message.
    Given Login With Username <username> and Password <password>
    When Go to Cart page and add product <productname> to the cart
    And  Checkout product<productname> and Submit Order
    Then Validate "THANKYOU FOR THE ORDER." displayed on Order Success Page.

    Examples: 
      | username             | password      | productname  |
      | chk255@gmail.com     | Ckumar@2426   | ZARA COAT 3  |

