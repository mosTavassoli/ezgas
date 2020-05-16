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

**Predicates for method *setGasStationId()*:**

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
|int|minint|Valid|Try to set a value and then test the value stored in the object |T2(minint) -> minint|
||-1|Valid||T3(-1) -> -1|
||0|Valid||T4(0) -> 0|
||maxint|Valid||T5(maxint) -> maxint|
||||||


 ### **Class *GasStationDto* - method *checkPrices()***

The function should perform the same checks for all 5 types of fuel so here we will use a generic fuel instead of repeating the same thing multiple times.
The JUnit test case will be exhaustive.

**Criteria for method *checkPrices()*:**
 - Value of fuel price
 - Availability of fuel





**Predicates for method *checkPrices()*:**

| Criteria | Predicate |
| -------- | --------- |
| Value of fuel price | [mindouble,-1) |
|          |      -1     |
|          |      (-1,0) |
|          |      [0,maxdouble]   |
| Availability of fuel | Yes |
|                      | No  |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of fuel price  | mindouble, -1, 0, maxdouble              |
|          |                 |



**Combination of predicates**:


| Availability of fuel | Value of fuel price | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| No | - | Valid | A particular fuel is not available at this gas station | GasStationDto gasStationDto=new GasStationDto();<br/>gasStationDto.setHasFuel(false)<br/>gasStationDto.checkPrices() -> true |
| Yes | [mindouble,-1) U (-1,0) | Valid | A particular fuel is available and has a negative price different that -1 | GasStationDto gasStationDto=new GasStationDto();<br/>gasStationDto.setHasFuel(true)<br/>gasStationDto.setFuelPrice(-2.5)<br/>gasStationDto.checkPrices() -> false |
|  | -1 | Valid | A particular fuel is available and has a price equal to -1 | GasStationDto gasStationDto=new GasStationDto();<br/>gasStationDto.setHasFuel(true)<br/>gasStationDto.setFuelPrice(-1)<br/>gasStationDto.checkPrices() -> true |
|  | [0,maxdouble] | Valid | A particular fuel is available and has a non-negative price | GasStationDto gasStationDto=new GasStationDto();<br/>gasStationDto.setHasFuel(true)<br/>gasStationDto.setFuelPrice(1.56)<br/>gasStationDto.checkPrices() -> true |
|  |  |  |  |  |

 ### **Class *GasStationDto* - method *checkCoordinates()***



**Criteria for method *checkCoordinates()*:**
 - Value of longitude
 - Value of latitude
 - Type of latitude parameter
 - Type of longitude parameter





**Predicates for method *checkCoordinates()*:**

| Criteria | Predicate |
| -------- | --------- |
|    Value of longitude          | [-180,180] |
|                                | [mindouble,-180) |
|                                | (180,maxdouble] |
|    Value of latitude           | [-90,90] |
|                                | [mindouble,-90) |
|                                | (90,maxdouble] |
|    Type of latitude parameter  | double |
|                                | other |
|    Type of longitude parameter | double |
|                                | other |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of longitude | mindouble, -180, 180, maxdouble |
| Value of latitude  | mindouble, -90, 90, maxdouble   |



**Combination of predicates**:


| Type of longitude parameter | Type of latitude parameter | Value of longitude | Value of latitude | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|------|
| other | - | - | - | Invalid | Try to pass a longitude parameter of type different from double | GasStationDto.checkCoordinates(0,"test") -> error |
| double | other | - | - | Invalid | Try to pass a latitude parameter of type different from double | GasStationDto.checkCoordinates("test",0) -> error |
| double | double | [mindouble,-180) U (180,maxdouble] | - | Valid | The value of the longitude is outside the allowed boundaries | GasStationDto.checkCoordinates(0,-200) -> false |
|  |  | [-180,180] | [mindouble,-90) U (90,maxdouble] | Valid | The value of the latitude is outside the allowed boundaries | GasStationDto.checkCoordinates(120,0) -> false |
|  |  | [-180,180] | [-90,90] | Valid | Both latitude and longitude within the allowed boundaries | GasStationDto.checkCoordinates(43,168) -> true |

 ### **Class *GasStationDto* - method *toString()***



**Criteria for method *toString()*:**
 - Returned string contains correct, previously set, values
 - 





**Predicates for method *toString()*:**

| Criteria | Predicate |
| -------- | --------- |
| Any attributes with null values | Yes |
|          |      No     |






**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Any attributes with null values | 1 object attribute null, all object attributes null |



**Combination of predicates**:


| Any attributes with null values | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| Yes | Valid | One or more object attributes with null values | GasStationDto gasStationDto = new GasStationDto()<br/>gasStationDto.toString() -> string containing multiple "null" strings|
| No | Valid | No null attributes (Except for userDto) | GasStationDto gasStationDto = new GasStationDto(gasStationId,gasStationName, ...)<br/>gasStationDto.toString() -> string containing all the attributes set by the constructor (userDto is set to null) |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |





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



