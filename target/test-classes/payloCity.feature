Feature: Add employee no discount
  @outline
  Scenario Outline:
    Given user is on paylocity homepage
    And   user logs in the Benefits Dashboard page
    When  user  clicks add employee button
    And   user  enters employee details "<name>", "<lastname>", "<dependant>"
    Then  user verifies their info "<name>", "<lastname>", "<dependant>", benefitCost and netPay on the benefits dashboard
    And   user sees if employee gets "<name>" percent discount.
    Examples:
      | name    | lastname | dependant |
      | Armagan | Aydingul | 1         |
      | Mike    | Lee      | 2         |