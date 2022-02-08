Feature: Book a ticket and validating fare

  @First
  Scenario Outline: User should able to book and validate fare
    Given the user is on make my trip homepage
    When the user login with "<UserName>" and "<Password>"
    Then the user enter "<DestinationFrom>" to "<DestinationTo>" in from and to box
    And enter the date and select number of passengers as "<NoOfAdults>" Adults and "<NoOfChildren>" Children
    Then select the lower priced airlines and click book button
    And change economy class to premium flex and continue
    Then verify the fare rules for selected time period and verify it for all the passengers
    Then verify the total amount and validate it

    Examples:
      |UserName           | Password   |DestinationFrom|DestinationTo|NoOfAdults|NoOfChildren|
      |1993m1997@gmail.com|12345678ABC@|  Bengaluru    |  New Delhi  |    2      |     1     |