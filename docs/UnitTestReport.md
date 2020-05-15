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

### **Class *GasStation* - method *setGasStationId()***



**Criteria for method *setGasStationId()*:**
 - Type of parameter
 - Value of parameter

**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
| Type of parameter  | int         |
|                    | other       |
| Value of parameter | [minint,-1] |
|                    | [0,maxint]  |
|          |                 |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of parameter | minint, minint+1, -1, 0, maxint-1, maxint |
|          |                 |



**Combination of predicates**:


| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to pass parameter of type different from int | T1("test") -> Error |
|int|minint|Invalid|Try to set a value and then test the value stored in the object |T2(minint) -> InvalidUserException|
||-1|Invalid||T3(-1) -> InvalidUserException|
||0|Valid||T4(0) -> User|
||maxint|Valid||T5(maxint) -> maxint|
||||||


 ### **Class *GasStationDto* - method *checkPrices()***

The function should perform the same checks on every type of fuel so here we will use a generic fuel instead of repeating the same thing multiple times.

**Criteria for method *checkPrices()*:**
 - Value of fuel price
 - Availability of fuel





**Predicates for method *checkPrices()*:**

| Criteria | Predicate |
| -------- | --------- |
| Value of fuel price | [minfloat,-1) |
|          |      -1     |
|          |      (-1,0) |
|          |      [0,maxfloat]   |
| Availability of fuel | Yes |
|                      | No |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of fuel price  | minfloat, -1, 0, maxfloat              |
|          |                 |



**Combination of predicates**:


| Availability of fuel | Value of fuel price | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| No | - | Valid | A particular fuel is not available at this gas station |  |
| Yes | [minfloat,-1) U (-1,0) | Valid | A particular fuel is available and has a negative price different that -1 |  |
|  | -1 | Valid | A particular fuel is available and has a price equal to -1 |  |
|  | [0,maxfloat] | Valid | A particular fuel is available and has a non-negative price |  |
|  |  |  |  |  |

 ### **Class *class_name* - method *name***



**Criteria for method *name*:**
	

 - 
 - 





**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|          |           |
|          |           |
|          |           |
|          |           |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|          |                 |
|          |                 |



**Combination of predicates**:


| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|||||||
|||||||
|||||||
|||||||
|||||||

 ### **Class *class_name* - method *name***



**Criteria for method *name*:**
	

 - 
 - 





**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|          |           |
|          |           |
|          |           |
|          |           |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|          |                 |
|          |                 |



**Combination of predicates**:


| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|||||||
|||||||
|||||||
|||||||
|||||||





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



