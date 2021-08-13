Feature: Buy

  #Scenario: login with and active user
   # Given im in the main menu as a guest user
   # And i click on the sign in button
   # When i write my username "laura@yopmail.com"
   # And i write my password "123456"
   # And i click on the Sign in button to login
   # Then i click on the main icon
   # And i should see the user name "Laura Garavito" on the screen

  Scenario: Laura try to buy a dress
    Given im in the main menu as a guest user
    And i login as "laura@yopmail.com" with password "123456" and name "Laura Garavito"
    And i click on dresses menu
    And i want to see details of the "Printed Chiffon Dress"
    And i want to buy "3" dress, color "Green" and Size "M"
    And i try to add to cart
    And i click on Proceed to Checkout button
    When i verify all data on screen is the same as i saw on product page with shipping "2.0" and tax "0.0"
    And my product is avaiblable
    And i click on the Proceed to Checkout button
    And i choose my delivery address "lauras"
    And my address information is correct
      | address_firstname address_lastname               | Laura Garavito         |
      #CAMPO EMAIL INCORRECTO CON LA INTENCIÓN DE TERMINAR EL FLUJO DEL ROBOT, VALOR REAL laura@yopmail.com
      | address_company                                  | Yopmail                |
      | address_address1 address_address2                | Calle 55 55 55         |
      | address_city address_state_name address_postcode | Medellín, Nevada 00000 |
      | address_country_name                             | United States          |
      | address_phone                                    | 3111234567             |
      | address_phone_mobile                             | 3111234567             |
    And i click on the Proceed to Checkout button on Address screen
    And i click the agree to the terms of service button
    And i click on the Proceed to Checkout button on Shipping screen
    #MO SE VERIFICARÁ EL TAX PARA DARLE CONTINUIDAD AL FLUJO DEL ROBOT
    And i verify all values of my buy to be the same as the summary screen
    And choose the pay by bank-wire option
    And i should see the correct total price
    And i click the I confirm my order button
    Then i should see my order confirmation
    And logout


