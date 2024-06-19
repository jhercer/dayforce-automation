@xray
Feature: gpri
  Scenario: a GPRI post
    Given a gpri
    """
   {
    "PayrunImport": {
        "Items": [
            {
                "NoOverride": "false",
                "ContributeToNetPay": "true",
                "PayPeriodSuffix": "",
                "PayGroupXrefCode": "MX_SALARIED_WEEKLY",
                "IsEmployerDeduction": "false",
                "ItemType": "Earning",
                "PayPeriodStart": "2024-05-25T00:00:00",
                "PayDate": "2024-05-31T00:00:00",
                "PayPeriodEnd": "2024-06-01T00:00:00",
                "CommitDate": "2024-07-17T00:00:00",
                "Rate": 0,
                "OffCyclePayRunDefXrefCode": "OFF_CYCLE",
                "OffCyclePayRunTypeXrefCode": "NORMAL",
                "OffCyclePayRunXrefCode": "Bonus_2201",
                "IsOffCycle": "true",
                "EmployeeXrefCode": "88702",
                "LegalEntityXrefCode": "81",
                "IsEmployerTax": "false",
                "Units": 0,
                "PayPeriod": "22",
                "IsPreTax": "false",
                "ItemCode": 8,
                "CheckOrder": 1,
                "ItemAmount": 3200
            }
        ]
    },
    "clientnamespace": "qaextx2_1615501"
    }
    """
    When I call a post
    Then a job must been queued
    And I login into DayForce as an admin