# Integration and API Test Documentation

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date: 23/05/2020

Version: 1

# Contents

- [Dependency graph](#dependency-graph)

- [Integration and API Test Documentation](#integration-and-api-test-documentation)
- [Contents](#contents)
- [Dependency graph](#dependency-graph)
- [Integration approach](#integration-approach)
- [Tests](#tests)
  - [Step 1](#step-1)
  - [Step 2](#step-2)
  - [Step n API Tests](#step-n-api-tests)
- [Scenarios](#scenarios)
  - [Scenario UC1.1](#scenario-uc11)
- [Coverage of Scenarios and FR](#coverage-of-scenarios-and-fr)
- [Coverage of Non Functional Requirements](#coverage-of-non-functional-requirements)
    - [](#)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph 

     <report the here the dependency graph of the classes in it/polito/Ezgas, using plantuml>
```plantuml
top to bottom direction
class User
class UserDto
class UserRepository
class UserConverter
class UserServiceimpl
class UserService
class UserController

class IdPw

class LoginDto
class LoginConverter

class GasStation
class GasStationDto
class GasStationRepository
class GasStationServiceimpl
class GasStationService

UserRepository         ---> User
   
UserConverter          ---> User
UserConverter          ---> UserDto
   
UserServiceimpl        ---> UserConverter
UserServiceimpl        ---> UserRepository
UserServiceimpl        ---> IdPw
UserServiceimpl        ---> User
UserServiceimpl        ---> UserDto
UserServiceimpl        ---> LoginDto
UserServiceimpl        ---> LoginConverter

UserService            ---> UserServiceimpl

UserController         ---> UserService

LoginConverter         ---> LoginDto
LoginConverter         ---> User

GasStation             ---> User

GasStationDto          ---> UserDto

GasStationRepository   ---> GasStation

GasStationConverter    ---> GasStation
GasStationConverter    ---> GasStationDto

GasStationServiceimpl  ---> GasStationRepository
GasStationServiceimpl  ---> GasStationConverter
GasStationServiceimpl  ---> GasStation
GasStationServiceimpl  ---> GasStationDto
GasStationServiceimpl  ---> UserService

GasStationService      ---> GasStationServiceimpl 

GasStationController   ---> GasStationService

```
     
# Integration approach

    <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
    (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)> 
    <The last integration step corresponds to API testing at level of Service package>
    <Tests at level of Controller package will be done later>



#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
| Classes  | JUnit test cases |
|--|--|
|||


## Step 2
| Classes  | JUnit test cases |
|--|--|
|||


## Step n API Tests

   <The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
|||




# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UC1.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Account U exists inside the system and account B does not exist|
|  Post condition     |  Account U exists inside the system and account B does not exist|
| Step#        | Description  |
|  1     |  A user tries to create a new account B and populates its fields using the same email of account U |  
|  2     |  System searches for email to see if it exists inside the system |
|  3     |  System finds the email inside the system |
|  4     |  System checks to see the type of account (user or admin)|
|  5     |  System finds out that type of account is user|
|  6     |  Failure message is prompted to user stating that an account already exists for this email |


# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  ..         | FRx                             |             |             
|  ..         | FRy                             |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |


