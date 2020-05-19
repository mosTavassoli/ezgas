# Unit Testing Documentation

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date: 14/05/2020

Version: 1.5

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
|other|-|Invalid|Try to pass parameter of type different from int	| T1("test");<br> -> Error|
||-|Invalid|| T2(1.5);<br> -> Error|
|int|minint|Valid|Try to set a value and then test the value stored in the object|T3(minint);<br> -> minint|
||minint+1|Valid||T4(minint+1);<br> -> minint+1|
||-1|Valid||T5(-1);<br> -> -1|
||0|Valid||T6(0);<br> -> 0|
||maxint|Valid||T7(maxint);<br> -> maxint|
||maxint-1|Valid||T8(maxint-1);<br> -> maxint-1|


 ### **Class *User* - method *getUserId()***
 
**Criteria for method *getUserId()*:**

 - Type of parameter
 - Value of parameter


**Predicates for method *getUserId()*:**

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
|other|-|Invalid|Try to set parameter of type different from int|setUserId("test"); <br>-> Error|
||-|Invalid||setUserId(1.5);<br> -> Error|
|int|minint|Valid|Try to set a value and then test the value stored in the object|setUserId(minint);<br> getUserId(); -> minint|
||minint+1|Valid||setUserId(minint+1);<br> getUserId(); -> minint+1|
||-1|Valid||setUserId(-1); <br> getUserId(); ->-1|
||0|Valid||setUserId(0); <br> getUserId() -> 0|
||maxint|Valid||setUserId(maxint); <br> getUserId() -> maxint|
||maxint-1|Valid||setUserId(maxint-1); <br> getUserId() -> maxint-1|


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
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to pass parameter of type different from int | T1("test") -> Error |
|int|minint|Valid|Try to set a value and then test the value stored in the object |T2(minint) -> minint|
||-1|Valid||T3(-1) -> -1|
||0|Valid||T4(0) -> 0|
||maxint|Valid||T5(maxint) -> maxint|
||||||



### **Class *GasStation* - method *getGasStationId()***

**Criteria for method *getGasStationId()*:**
 - Type of parameter
 - Value of parameter

**Predicates for method *getGasStationId()*:**

| Criteria | Predicate |
| -------- | --------- |
| Type of parameter  | int         |
|                    | other       |
| Value of parameter | [minint,-1] |
|                    | [0,maxint]  |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of parameter | minint, minint+1, -1, 0, maxint-1, maxint |

**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|other|-|Invalid|Try to set parameter of type different from int| setGasStationId("string");<br> -> Error|
||-|Invalid|| setGasStationId(1.5);<br> -> Error|
|int|minint|Valid|Try to set a value and then test the value stored in the object|setGasStationId(minint);<br> getGasStationId() -> minint|
||minint+1|Valid||setGasStationId(minint+1);<br>getGasStationId() -> minint+1|
||-1|Valid||setGasStationId(-1);<br>getGasStationId() -> -1|
||0|Valid||setGasStationId(0); <br> getGasStationId() -> 0|
||maxint|Valid||setGasStationId(maxint); <br> getGasStationId() -> maxint |
||maxint-1|Valid||setGasStationId(maxint-1); <br> getGasStationId() -> maxint-1 |


 ### **Class *GasStationDto* - method *checkPrices()***

The function should perform the same checks for all 5 types of fuel so here we will use a generic fuel instead of repeating the same thing multiple times.
The JUnit test cases will be exhaustive.

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
|-------|-------|-------|-------|-------|
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
|  |  | [-180,180] | [-90,90] | Valid | Both latitude and longitude within the allowed boundaries | GasStationDto.checkCoordinates(43.63,168.111) -> true |

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
|-------|-------|-------|-------|
| Yes | Valid | One or more object attributes with null values | GasStationDto gasStationDto = new GasStationDto()<br/>gasStationDto.toString() -> string containing multiple "null" strings|
| No | Valid | No null attributes (Except for userDto) | GasStationDto gasStationDto = new GasStationDto(gasStationId,gasStationName, ...)<br/>gasStationDto.toString() -> string containing all the attributes set by the constructor (userDto is set to null) |
|  |  |  |  |


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


**Predicates for constructor *UserDto*:**

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
| User.User | UserTest.testUserEntityConstructor |
| User.getUserId | UserTest.testUserIdMinInt |
|                | UserTest.testUserIdMinIntPlusOne |
|                | UserTest.testUserIdMinusOne |
|                | UserTest.testUserIdZero |
|                | UserTest.testUserIdMaxInt |
|                | UserTest.testUserIdMaxIntMinusOne |
| UserDto.UserDto | UserDtoTest.testConstructor |
| UserDto.editUserReputation | UserDtoTest.testEditUserReputationNegativeOutOfBounds |
|                            | UserDtoTest.testEditUserReputationNegativeWithinBounds |
|                            | UserDtoTest.testEditUserReputationPositiveOutOfBounds |
|                            | UserDtoTest.testEditUserReputationPositiveWithinBounds |
| GasStation.getGasStationId | GasStationTest.testGasStationIdMinInt |
|                            | GasStationTest.testGasStationIdMinIntPlusOne |
|                            | GasStationTest.testGasStationIdMinusOne |
|                            | GasStationTest.testGasStationIdZero |
|                            | GasStationTest.testGasStationIdMaxInt |
|                            | GasStationTest.testGasStationIdMaxIntMinusOne |
| GasStationDto.checkPrices | GasStationDtoTest.testGasStationDoesNotHaveDiesel |
|                           | GasStationDtoTest.testGasStationDoesNotHaveGas |
|                           | GasStationDtoTest.testGasStationDoesNotHaveMethane |
|                           | GasStationDtoTest.testGasStationDoesNotHaveSuper |
|                           | GasStationDtoTest.testGasStationDoesNotHaveSuperPlus |
|                           | GasStationDtoTest.testNegativeDieselPrice |
|                           | GasStationDtoTest.testNegativeGasPrice |
|                           | GasStationDtoTest.testNegativeMethanePrice |
|                           | GasStationDtoTest.testNegativeSuperPrice |
|                           | GasStationDtoTest.testNegativeSuperPlusPrice |
|                           | GasStationDtoTest.testMinusOneDieselPrice |
|                           | GasStationDtoTest.testMinusOneGasPrice |
|                           | GasStationDtoTest.testMinusOneMethanePrice |
|                           | GasStationDtoTest.testMinusOneSuperPrice |
|                           | GasStationDtoTest.testMinusOneSuperPlusPrice |
|                           | GasStationDtoTest.testNonNegativeDieselPrice |
|                           | GasStationDtoTest.testNonNegativeGasPrice |
|                           | GasStationDtoTest.testNonNegativeMethanePrice |
|                           | GasStationDtoTest.testNonNegativeSuperPrice |
|                           | GasStationDtoTest.testNonNegativeSuperPlusPrice |
| GasStationDto.checkCoordinates | GasStationDtoTest.testLatitudeOutOfBounds |
|                                | GasStationDtoTest.testLongitudeOutOfBounds |
|                                | GasStationDtoTest.testLatitudeAndLongitudeOutOfBounds |
|                                | GasStationDtoTest.testLatitudeAndLongitudeInsideBounds |
| GasStationDto.toString | GasStationDtoTest.testToStringWithUninitializedAttributes |
|                        | GasStationDtoTest.testToStringWithInitializedAttributes |


### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

**There are no loops in the units.**

<!--|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||-->



