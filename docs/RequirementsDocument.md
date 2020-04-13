# Requirements Document 

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date:10/04/2020

Version:1

# Contents

- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
    	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)
- [Deployment diagram](#deployment-diagram)

# Abstract

TODO

# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
| Developer | Specialist that develops, deploys and maintains the application. | 
| Administrator | Manages users, user reports and other data related to gas stations and prices | 
| Data Analyst | TODOChecks the quality of the uploaded data from the consumer. |
| Google Maps |Service used by the application to locate and display gas stations close to the users|
| User |Uses the web application in order to find the cheapest fuel prices in his proximity and/or update the prices when they differ from the actual ones|

# Context Diagram and interfaces

## Context Diagram
```plantuml
left to right direction
actor "Administrator" as admin
actor "Driver" as d
actor "Gas Station Manager" as gsm
actor "Google Maps" as gm
actor "Data Analyst" as da

rectangle System{
	(EZGas) -- admin
	gsm -- (EZGas)
	d -- (EZGas)
	(EZGas) -- gm
	(EZGas) -- da
}
```

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
|User| Web application GUI |Screen, mouse and keyboard,<br/>smartphone touchscreen|
|Administrator| Administrative panel GUI |Screen, mouse and keyboard|
|Data Analyst|Administrative panel GUI|Screen, mouse and keyboard|
|Google Maps|Web Services (APIs)|Internet connection|


# Stories and personas
Joseph is an active and industrious teacher and program developer who always try to manage their time as best as possible. He has an impressive time scheduling for himself to get to work timely. He tries to follow this personal time management, but he often suffers from finding the nearest Gas Station. He wasted a huge amount of time to get the station and most of the time he is not able to find the right path to the station. Besides, he wants to save his money by searching the cheapest location of GAS station nearby.
Goals: wants a system to find a suitable location (in terms of distances and prices) of the station to reach, as well as offer the cheapest one to others. 

He would like to have an application to locate the nearest Gas station around by using his cell phone and also by his computer. He wants to implement a very simple but practical application, which easy to use for all users.

Marco is a businessman living in Rome. His job forces him to drive around the city all day with his car and he has often to refuel. Thanks to EZGas, he is always able to find a closer and convenient gas station, without having to remember the price of the various stations every time. While refueling, he checks if the price on the application is updated and if it isn't, he updates it.

Julia is on vacation in Rome. During these days she decides to take a rental car to visit the city. Since she doesn't know the city and is afraid of wasting too much time and money looking for gas stations, she searches on EZGas for those that were most comfortable to reach, comparing the prices they practice. Fortunately, she immediately finds a very convenient station, thanks to Marco who have just updated the price.

George is gas station manager in London and he was really worried for his job becouse his activity was not very known in his area. He found EZGas and by seeing the nearby stations, finally realized that he should lower the price to be more competitive. Now in George's gas station there are many customers queuing to refuel and he is always up to date to the price of competitors in his area.

Micheal is a student worker who does not earn much money in his part-time job. 
He has a really tight budget due to his daily expenses and can barely afford a car as is.
He would really love to shop for cheaper gasoline for his car, but cannot find a service with up to date prices.

Vivian is the manager of a small independent gas station with some of the best prices in her part of town. However, many motor vehicle owners are not even aware of its existence as they are used to buying fuel at flashier and well established chains. She doesn't know how to attract customers without spending a fortune in an advertising campaign.

John is driving his car and decides to make a stop in order to fill the car with fuel. John
opens the EZGas app, signs in to his own account (signs up if he didn’t already have an
account) and looks for the nearby gas stations along their fuel’s price. Then, john chooses a
gas station and makes a stop there, fills the car with fuel, pays for the fuel, and since john
has an inclination to contribute to help people, decides to write his own review on the
EZGas app. The review contains the gas station’s location and the price of the fuel.
But sometimes john does not use the app to find a gas station, instead he finds one himself,and uploads his review concerning that gas station, since he loves to contribute.
And sometimes after stopping by a gas station and uploading his review, john will check
out the other reviews submitted by other people, and if he finds a wrong review, he will
report it to the app’s administrators.


# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |:-------------:| 
|  FR1     | Record fuel prices for a gas station|  
|  FR2     | Check prices of fuel in various gas stations |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.1|Load prices|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.2|Load map|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.3|Display prices on the map|
|  FR3     | Authorize and authenticate |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.1 | Log in |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.2 | Log out |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.3 | Create new a account |
|  FR4     | Report inaccurate fuel prices |
|  FR5 | Manage users and data |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR5.1 | Ban fraudulent users |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR5.2 | Hide suspicious prices |
| FR6 | Manage personal account|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR6.1 | Edit account information |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR6.2 | Delete personal account |

## Non Functional Requirements
| ID        | Type (efficiency, reliability, .. see iso 9126)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Usability | The web application should be used with no training | All FR except FR5|
|  NFR2     | Performance | Functions should complete in < 0.5 sec  | FR TODO |
|  NFR3     | Portability | The web application should run on Firefox (74+), Chrome (62+), Safari (12+), Chrome for Android, Safari iPhone and Safari iPad | All FR |
|  NFR4     | Localization | Decimal numbers use . (dot) as decimal separator ||
|  NFR5     | Localization | Prices are to be displayed as "per liter" or "per gallon" depending on the region of the user ||
|  NFR6     | Efficiency | The user should be able to sort gas stations by distance and price |FR2|
|  NFR7     | Efficiency | Given the fuel consumption of the motor vehicle to be filled and the amount of fuel desired (or the amount of money the user is going to spend to fill the motor vehicle), the web application should be able to sort gas stations by most cost-effective considering fuel cost at the gas station, fuel consumption of the motor vehicle and distance between the user and the gas station |FR2|
| NFR8 | Performance | Functions should complete in < 2 sec | TODO |


# Use case diagram and use cases


## Use case diagram
```plantuml	
actor User as u
actor Administrator as admin
actor "Google Maps" as gm
actor "Data Analyst" as da
rectangle System{
u --> (FR1 Record fuel prices for a given gas station)
(FR1 Record fuel prices for a given gas station) --> gm
(FR2 Check prices of fuel in various gas stations) --> gm

u --> (FR2 Check prices of fuel in various gas stations)
u --> (FR4 Report inaccurate fuel prices)
u --> (FR3 Authorize and authenticate)
u --> (FR6 Manage personal account)
admin --> (FR6 Manage personal account)
da --> (FR6 Manage personal account)
da --> (FR3 Authorize and authenticate)
admin --> (FR3 Authorize and authenticate)
da --> (FR5 Manage users and data)
admin --> (FR5 Manage users and data)
(FR2 Check prices of fuel in various gas stations) ..> (FR3 Authorize and authenticate):<<include>>
(FR1 Record fuel prices for a given gas station) ..> (FR3 Authorize and authenticate):<<include>>
(FR4 Report inaccurate fuel prices) ..> (FR3 Authorize and authenticate):<<include>>
(FR4 Report inaccurate fuel prices) ..> (FR1 Record fuel prices for a given gas station):<<include>>
(FR5 Manage users and data) ..> (FR3 Authorize and authenticate):<<include>>

```
### Use case 1, UC1 - FR1  Record fuel prices for a given gas station

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | User is registered, logged in, and visited gas station GS for which they are recording the new price for a fuel type |  
|  Postcondition     | For all updated fuel types: GS.\<fuel_name\>.post_price != GS.\<fuel_name\>.pre_price|
|  Nominal Scenario     | User selects gas station GS from the provided map or list, then selects the type of fuel whose price they want to update and provides the new price, if it is different from the previously recorded one|
|  Variants     | User provides price that doesn't match the real price. The web application will rely on another user reporting this incorrect price. <br/>User is banned from contributing. In this case, they are presented with a warning message that states that they are not allowed to complete this operation.|

##### Scenario 1.1 
| Scenario ID: SC1.1        | Corresponds to UC1  |
| ------------- |:-------------| 
| Description | User wants to register a fuel F price P at a gas station GS|
| Precondition |  User is registered and logged in|
| Postcondition |  Price P of fuel F at gas station GS is updated |
| Step#        |  Step description   |
|  1     | User selects fuel F |  
|  2     | User selects gas station GS |
|  3     | User selects option to update price P |
|  4     | User enters the new price |

##### Scenario 1.2

| Scenario ID: SC1.2        | Corresponds to UC1  |
| ------------- |:-------------| 
| Description | User wants to register a fuel F price P at a gas station GS, but their account is banned|
|Precondition |  User is registered and logged in but banned from contributing|
|Postcondition |  Price P of fuel F at gas station GS is not updated|
| Step#        | Step description  |
|  1     | User selects fuel F |  
|  2     | User selects gas station GS |
|  3     | User selects option to update price P |
|  4     | User is presented with a message warning them that they are not authorized to perform this action|

### Use case 2, UC2 - FR2 Check prices of fuel in various gas stations

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition         | User is registered and logged in and the GPS is enabled|  
|  Postcondition       |  |
|  Nominal Scenario     | User selects the type of fuel they need and they are presented with a map and a list of gas stations near them |
|  Variants             |   |

##### Scenario 2.1
| Scenario ID: SC2.1        | Corresponds to UC1  |
SC3
Description Consumer wants to view the gas stations on the map, and their fuel prices
Precondition Account of C exists.
Gas station G exists.
Account of C exists.
Reviews on the map exist.
Post
condition
Step#
Step description
1
Consumer opens the application
2
Consumer logs into his account
3
Consumer observes the map
4
Consumer clicks on a certain gas station and checks the corresponding fuel
prices.


### Use case 3, UC3 - FR3 Authorize and authenticate

| Actors Involved        | User, Administrator |
| ------------- |:-------------:| 
|  Precondition     |  |  
|  Postcondition     | User/Administrator logged in, logged out or new user personal account created |
|  Nominal Scenario     | User/Administrator wants to use one of the functions of the web application, so they are requested to register or login.<br/>User/Administrator independently decides to register or log in.<br/> User/Administrator wants to log out of the web application.|
|  Variants     | User/Administrator fails to register or log in because of missing/incorrect/incoherent data in the form. In this case they are presented again with the same form, with their data filled as it was, and with an error message explaining what is the issue. |

### Use case 4, UC4 - FR4 Report inaccurate fuel prices

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | There is a price P related to a fuel type F in a gas station GS that is perceived by the user as suspiciously out of line with the prices at that gas station|  
|  Postcondition     | Report sent to the Administrator for further consideration |
|| User thanked for their contribution and asked to continue as specified in UC1 |
|  Nominal Scenario     | User notices a suspicious price on the web application, that they think does not make sense or is clearly fraudulent. They press the report button next to the price and provide the correct one following the procedure specified in UC1|
|  Variants     | User does not want to (or cannot) enter a new price. In this case the previous price is neither hidden nor replaced, but it is publicly marked as suspicious on the web application, until the administrator takes further steps. |

### Use case 5, UC5 - FR5 Manage users and data

| Actors Involved        | Administrator, Data Analyst  |
| ------------- |:-------------:| 
|  Precondition     | Administrator/Data Analyst logged in |  
|  Postcondition     |  |
|  Nominal Scenario     | Administrator can add, remove or ban users, inspect reports and take the necessary actions. <br/>Administrator/Data Analyst can view gas stations with their fuels and relative prices. |
|  Variants     | |

# Glossary

```plantuml

class GasStation{
	+ id
	+ name
	+ brand
}
class User {
	+ username
	+ name
	+ surname
	+ banned
	+ sendReport()
	+ recordPrice()
}
class Administrator {
	+ banUser()
}
class FuelType {
	+ name
	+ description
}
class FuelPrice {
	+ id
	+ value
	+ suspicious
	+ hidden
}
class Report {
	+ id
	+ date
	+ reviewed
	+ confirmed
}

class Location {
	+ address
	+ longitude
	+ latitude
}
class DataAnalyst {
	+ editData()
	+ reviewReport()
	+ hideFuelPrice()
}

User  -- "*" FuelPrice:Recorded by
User -- "*" Report:Sent by
DataAnalyst <|-- Administrator
User <|-- DataAnalyst
DataAnalyst -- "*" Report:Reviewed by
FuelPrice "*" -- GasStation:Offered by
FuelPrice "*" -- FuelType:Described by
FuelPrice -- "*" Report:Regarding
Location "1" -- "1" GasStation:Located at

note left of User : Includes both \nGas Station Managers \nand Motor Vehicle Owners
note left of FuelPrice : Relates fuel type and price \nat a specific gas station
note right of FuelType : Contains the\n description\nof a specific \nfuel type
note left of Report: Generated when \na User reports \na suspicious price

```

# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram 

\<describe here deployment diagram >
