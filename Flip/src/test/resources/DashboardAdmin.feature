Feature: Dashboard Admin

  Scenario :Validation of transaction data by accessing the web and database
    Given access database
    Given access web dashboard
    When validation data dashboard with database


  Scenario Outline:Validation data transaction manual data UI
    Given access database
    When validation data examples UI "<ID>", "<userName>", "<sourceBank>", "<destinationBank>", "<amount>" with data database

    Examples:
    |ID   |userName | sourceBank| destinationBank |amount  |
    |001  | Smith   | BCA       | BRI             | 1000000|
    |100  | John    | BRI       | BCA             | null   |
    |101  | Fulan   | Mandiri   | BCA             |4000000 |
    |110  | Bambang | BNI       | BSI             |3000000 |
    |111  | Sri     | BCA       | BSI             |1500000 |