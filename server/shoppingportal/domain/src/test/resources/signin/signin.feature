#Feature: User Authentication
#
#
#  Scenario Outline: Successful Login
#    Given a user exists with an email "<email>"
#    When the user with role "<role>" attempts to log in with email "<email>" and password "<password>"
#    Then the user should be authenticated with success
#    And the user should receive a token with userDetails
#
#    Examples:
#      | email                | password    | role |
#      | test@gmail.com       | password    | USER |

#
#  Scenario Outline: Login Failure due to Email Not Found
#    Given the user enters email "<email>" and password "<password>"
#    When the user clicks on the login button
#    Then the user should see a "<message>"
#
#    Examples:
#      | email               | password | message                      |
#      | unknown@example.com | password | Email not found in database! |
#
#  Scenario Outline: Login Failure due to Incorrect Credentials
#    Given the user enters email "<email>" and password "<password>"
#    When the user clicks on the login button
#    Then the user should see a "<message>"
#
#    Examples:
#      | email               | password  | message                    |
#      | unknown@example.com | incorrect | Credentials are uncorrect. |