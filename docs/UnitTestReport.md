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
|other|-|Invalid|Try to pass parameter of type different from int	| T1("test");<br> -> InvalidUserException|
||||| T2(1.5);<br> -> InvalidUserException|
|int|minint|Invalid|Try to set a value and then test the value stored in the object|T3(minint);<br> -> InvalidUserException|
||minint+1|Invalid||T4(minint+1);<br> -> InvalidUserException|
||-1|Invalid||T5(-1);<br> -> InvalidUserException|
||0|valid||T6(0);<br> -> user|
||maxint|valid||T7(maxint);<br> -> maxint|
||maxint-1|valid||T8(maxint-1);<br> -> maxint-1|
||maxint+1|Invalid||T9(maxint+1);<br> -> InvalidUserException|



 ### **Class *User* - method *getUserId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | test cases |
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to set parameter of type different from int| T1 {<br>setUserId("test"); <br>-> InvalidUserException<br>}|
|||||T2 {<br> setUserId(1.5);<br> -> InvalidUserException<br>}|
|int|minint|Invalid||T3 {<br>setUserId(minint);<br> -> InvalidUserException<br>}|
||minint+1|Invalid||T4 {<br>setUserId(minint+1);<br> -> InvalidUserException<br>}|
||-1|invalid||T5 {<br>setUserId(-1); <br>-> InvalidUserException<br>}|
||0|valid||T6 {<br>setUserId(0); <br> getUserId() -> 0<br>}|
||maxint|valid||T7 {<br>setUserId(maxint); <br> getUserId() -> maxint <br>}|
||maxint-1|valid||T8 {<br>setUserId(maxint-1); <br> getUserId() -> maxint-1 <br>}|
||maxint+1|Invalid||T9 {<br>setUserId(maxint+1);<br>-> InvalidUserException<br>}|



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



