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

### **Class *User* - method *setReputation***



**Criteria for method *setReputation*:**
 - Type of reputation
 - Value of reputation

**Predicates for method *setReputation*:**

| Criteria | Predicate |
| -------- | --------- |
| Type of reputation  | int         |
|                    | other       |
| Value of reputation | [minint,-6] |
|                    | [-5,5]  |
|                    | [6,maxint]  |
|          |                 |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of reputation | minint, minint+1, -6, -5, 5, 6, maxint-1, maxint |
|          |                 |



**Combination of predicates**:


| Type of reputation | Value of reputation | Valid / Invalid | Description of the test case                    | JUnit test case |
|-------------------|--------------------|-----------------|-------------------------------------------------|---------------------|
|       other       |   -                |    Invalid      |Try to pass parameter of type different from int                | T1("test") -> Error |
|       int         |minint              |Valid            |Try to set a value and then test the value stored in the object |T2(minint) -> -5|
|                   |-6                  |Valid            |                                                                |T3(-6) -> -5|
|                   |-5                  |Valid            |                                                                |T4(-5) -> -5|
|                   |5                   |Valid            |                                                                |T5(5) -> 5|
|                   |6                   |Valid            |                                                                |T6(6) -> 5|
|                   |maxint              |Valid            |                                                                |T7(maxint) -> 5|
|                   |                    |                 |                                                                |                    |

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
