@mobile @calculator
Feature: Testar o app-android-calculator.apk

  Scenario: Somar dois números
    When somar 1234 e 9876
    Then o resultado deve ser 11110
