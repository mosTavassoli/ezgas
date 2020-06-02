# GUI  Testing Documentation 

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date: 02/06/2020

Version: 1

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

```
<Complete this table (from IntegrationApiTestReport.md) with the column on the right. In the GUI Test column, report the name of the .py  file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | GUI Test(s)        |
|-------------|---------------------------------|--------------------|
| UC1         | FR1.1                           | testScenarioUC1    |
| UC1.1       | FR1.1                           | testScenarioUC1.1  |
| UC1.2       | FR1.1                           | testScenarioUC1.2  |
| UC2         | FR1.1                           | testScenarioUC2    |
| UC2.1       | FR1.1<br>FR1.3                  | testScenarioUC2.1  |
| UC3         | FR1.2                           | testScenarioUC3    |
| UC3.1       | FR1.2<br>FR1.3                  | testScenarioUC3.1  |
| UC4         | FR3.1                           | testScenarioUC4    |
| UC5         | FR3.1                           | testScenarioUC5.1  |
| UC6         | FR3.2                           | testScenarioUC6    |
| UC7         | FR5.1                           | testScenarioUC7    |
| UC7.1       | FR5.1                           | testScenarioUC7.1  |
| UC8         | FR4.1<br>FR4.2                  | testScenarioUC8    |
| UC8.1       | FR4.5                           | testScenarioUC8.1  |
| UC8.2       | FR4.5                           | testScenarioUC8.2  |
| UC8.3       | FR4.5                           | testScenarioUC8.3  |
| UC8.4       | FR4.5                           | testScenarioUC8.4  |
| UC9         | FR5.2                           | testScenarioUC9    |
| UC10.1      | FR5.3                           | testScenarioUC10.1 |
| UC10.2      | FR5.3                           | testScenarioUC10.2 |

# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name                                  | Functional Requirements covered | REST  API Test(s)                                |
|----------------------------------------------------|---------------------------------|--------------------------------------------------|
| GasStationController.getGasStationById             |          FR5.3                  | TestController.testGetGasStationById             |
| GasStationController.getAllGasStations             |         FR5.2                        | TestController.testGetAllGasStations             |
| GasStationController.saveGasStation                |         FR3.1                        | TestController.testSaveGasStation                |
| UserController.deleteUser                          | FR1.2                           | TestController.testDeleteUser                    |
| UserController.getUserById                         | FR1.4                           | TestController.testGetUserById                   |
| UserController.getAllUsers                         | FR1.3                           | TestController.testGetAllUsers                   |
| UserController.saveUser                            | FR1.1                           | TestController.testSaveUser                      |
| GasStationController.getGasStationsByGasolineType  |       FR4.5                           | TestController.testGetGasStationsByGasolineType  |
| GasStationController.getGasStationsByProximity     |       FR4.1                          | TestController.testGetGasStationsByProximity     |
| GasStationController.getGasStationsByProximity     |         FR4.2                        | TestController.testGetGasStationsByProximity     |
| GasStationController.getGasStationsWithCoordinates |  FR4.1                               | TestController.testGetGasStationsWithCoordinates |
| GasStationController.getGasStationsWithCoordinates |  FR4.2                               | TestController.testGetGasStationsWithCoordinates |
| GasStationController.getGasStationsWithCoordinates |  FR4.5                               | TestController.testGetGasStationsWithCoordinates |
| GasStationController.setGasStationReport           |    FR5.1                             | TestController.testSetGasStationReport           |
| UserController.login                               | FR1                             | TestController.testLogin                         |
| UserController.increaseUserReputation              | FR5.3                           | TestController.testIncreaseUserReputation        |
| UserController.decreaseUserReputation              | FR5.3                           | TestController.testDecreaseUserReputation        |
| HomeController.admin                               |                                 | TestController.testAdmin                         |
| HomeController.index                               |                                 | TestController.testIndex                         |
| HomeController.map                                 |                                 | TestController.testMap                           |
| HomeController.login                               |                                 | TestController.testHomeControllerLogin           |
| HomeController.update                              |                                 | TestController.testUpdate                        |
| HomeController.signup                              |                                 | TestController.testSignup                        |