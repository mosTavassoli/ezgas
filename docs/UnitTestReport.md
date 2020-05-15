# Unit Testing Documentation

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date: 14/05/2020

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)
- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

### **Class *UserDto* - method *editUserReputation***



**Criteria for method *editUserReputation*:**
 - Type of modifier
 - Value of modifier
 - Within bounds

**Predicates for method *editUserReputation*:**

| Criteria | Predicate |
| -------- | --------- |
| Type of modifier  | int         |
|                    | other       |
| Value of modifier | [minint,-1] |
|                    | [0,maxint]  |
| Within bounds | Y (result = [-5,5])  |
|               | N (result = [minint,-6] or [6,maxint])  |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of modifier | minint, -1, 0, maxint |
|          |                 |



**Combination of predicates**:


| Type of modifier | Value of modifier | Within bounds | Valid / Invalid | Description of the test case    | JUnit test case |
|------------------|-------------------|---------------|-----------------|---------------------------------|-----------------|
|       other      |   -               |       -       |Invalid          |Try to pass parameter of type different from int | T1("string") -> Error |
|       int        | [minint,-1]       |       N       |Valid            |Try to edit a value and then test the returned value |T2 {<br/>setReputation(3);<br/>editUserReputation(-50) -> -5<br/>}|
|                  |                   |       Y       |Valid            |                                                    |T3 {<br/>setReputation(3);<br/>editUserReputation(-7) -> -4<br/>}|
|                  | [0,maxint]        |       N       |Valid            |                                                    |T4 {<br/>setReputation(3);<br/>editUserReputation(609) -> 5<br/>}|
|                  |                   |       Y       |Valid            |                                                    |T4 {<br/>setReputation(3);<br/>editUserReputation(1) -> 4<br/>}|
|                  |                   |               |                 |                                                    |               |

 ### **Class *UserDto* - constructor *UserDto***



**Criteria for constructor *UserDto*:**
	

 - Type of userId
 - Value of userId
 - Type of userName
 - Length of userName
 - Type of password
 - Length of userName
 - Type of email 
 - Length of userName
 - Type of reputation
 - Value of reputation
 - Type of admin
 - Value of admin


**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
| Type of userId |    int    |
|                |   other   |
| Value of userId | [minint,maxint] |
| Type of userName |    String    |
|                  |     other    |
| Length of userName | [0,maxint] |
| Type of password |    String    |
|                  |     other    |
| Length of password | [0,maxint] |
| Type of email |    String    |
|               |     other    |
| Length of email | [0,maxint] |
| Type of reputation |    int    |
|                    |   other   |
| Value of reputation | [minint,maxint] |
| Type of admin |    boolean    |
|               |      other    |
| Value of admin | true |
|                | false |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|  Value of userId  |   minint, maxint  |
|    Length of userName      |   0, maxint |
|    Length of password      |   0, maxint |
|    Length of email      |   0, maxint |
|  Value of reputation  |   minint, maxint  |


**Combination of predicates**:


| Type of userId | Value of userId | Type of userName | Length of userName | Type of password | Length of password | Type of email | Length of email | Type of reputation | Value of reputation | Type of admin | Value of admin | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|  other  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |Invalid |Try to pass userId of type different from int | T1("string") -> Error|
|  -  |  -  |  other  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |Invalid |Try to pass userName of type different from String | T2(10) -> Error|
|  -  |  -  |  -  |  -  |  other  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |Invalid |Try to pass password of type different from String | T3(9.7) -> Error|
|  -  |  -  |  -  |  -  |  -  |  -  |  other  |  -  |  -  |  -  |  -  |  -  |Invalid |Try to pass email of type different from String | T4(-8) -> Error|
|  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  other  |  -  |  -  |  -  |Invalid |Try to pass reputation of type different from int | T5(false) -> Error|
|  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  other  |  -  |Invalid |Try to pass admin of type different from boolean | T2(10.56) -> Error|
|  int   |  [minint,maxint]  |  String  |  [0,maxint]  |  String  |  [0,maxint]  |  String  |  [0,maxint]  |  int   |  [minint,maxint]  |  boolean  |  true  |Valid |Try to add values and then test the returned UserDto | T1(12, "username12", "pass12", "test12 @example.com", 3, true) -> <!-- TODO --> |
|     |   |    |    |    |    |    |    |     |    |    |  false  |Valid | | T1(45, "username45", "pass45", "test45 @example.com", -5, false) -> <!-- TODO --> |



# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||
