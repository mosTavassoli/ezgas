# Unit Testing Documentation

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date:15/05/2020

Version:1

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

 ### **Class *User* - method *setUserId()***



**Criteria for method *setUserId()*:**

 - Type of parameter
 - Value of parameter


**Predicates for method *setUserId()*:**

| Criteria | Predicate |
| -------- | --------- |
|Type of parameter        |int  |
|                         |other|
|Value of parameter       |[minint,-1]|
|                         |[0,maxint]|





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of parameter | minint, minint+1, -1, 0, maxint-1, maxint |



**Combination of predicates**:


| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to pass parameter of type different from int	| T1("test") -> Error|
|int|minint|Invalid|Try to set a value and then test the value stored in the object|T2(minint) -> InvalidUserException|
||-1|invalid||T3(-1) -> InvalidUserException|
||0|valid||T4(0) -> User|
||maxint|valid||T(5) -> maxint|



 ### **Class *User* - method *getUserId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | test cases |
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to set parameter of type different from int| setUserId("test"); -> InvalidUserException|
||||| setUserId(1.5); -> InvalidUserException|
|int|minint|Invalid||setUserId(minint); -> InvalidUserException|
|int|minint+1|Invalid||setUserId(minint+1); -> InvalidUserException|
||-1|invalid||setUserId(-1); -> InvalidUserException|
||0|valid||setUserId(0); <br> getUserId() -> 0|
||maxint|valid||setUserId(maxint); <br> getUserId() -> maxint |
||maxint-1|valid||setUserId(maxint-1); <br> getUserId() -> maxint-1 |
||maxint+1|Invalid||setUserId(maxint+1);-> InvalidUserException|



 ### **Class *GasStation* - method *getGasStationId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | test cases |
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to set parameter of type different from int| setGasStationId("test"); -> InvalidGasStationException|
||||| setGasStationId(1.5); -> InvalidGasStationException|
|int|minint|Invalid||setGasStationId(minint); -> InvalidGasStationException|
|int|minint+1|Invalid||setGasStationId(minint+1); -> InvalidGasStationException|
||-1|invalid||setGasStationId(-1); -> InvalidGasStationException|
||0|valid||setGasStationId(0); <br> getGasStationId() -> 0|
||maxint|valid||setGasStationId(maxint); <br> getGasStationId() -> maxint |
||maxint-1|valid||setGasStationId(maxint-1); <br> getGasStationId() -> maxint-1 |
||maxint+1|Invalid||setGasStationId(maxint+1);-> InvalidGasStationException|



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



