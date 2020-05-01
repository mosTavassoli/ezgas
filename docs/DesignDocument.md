# Design Document 


Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date:23/04/2020

Version:1


# Contents

- [High level design](#package-diagram)
- [Low level design](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document (see EZGas Official Requirements.md ). <br>
The design must comply with interfaces defined in package it.polito.ezgas.service (see folder ServicePackage ) <br>
UML diagrams **MUST** be written using plantuml notation.

# High level design 

The style selected is client - server. Clients can be smartphones, tablets, PCs.
The choice is to avoid any development client side. The clients will access the server using only a browser. 

The server has two components: the frontend, which is developed with web technologies (JavaScript, HTML, Css) and is in charge of collecting user inputs to send requests to the backend; the backend, which is developed using the Spring Framework and exposes API to the front-end.
Together, they implement a layered style: Presentation layer (front end), Application logic and data layer (back end). 
Together, they implement also an MVC pattern, with the V on the front end and the MC on the back end.



```plantuml
@startuml
package "Backend" {

}

package "Frontend" {

}


Frontend -> Backend
@enduml


```


## Front End

The Frontend component is made of: 

Views: the package contains the .html pages that are rendered on the browser and that provide the GUI to the user. 

Styles: the package contains .css style sheets that are used to render the GUI.

Controller: the package contains the JavaScript files that catch the user's inputs. Based on the user's inputs and on the status of the GUI widgets, the JavaScript controller creates REST API calls that are sent to the Java Controller implemented in the back-end.


```plantuml
@startuml
package "Frontend" {

    package "it.polito.ezgas.resources.views" {

    }


package "it.polito.ezgas.resources.controller" {

    }


package "it.polito.ezgas.resources.styles" {

    }



it.polito.ezgas.resources.styles -down-> it.polito.ezgas.resources.views

it.polito.ezgas.resources.views -right-> it.polito.ezgas.resources.controller


}
@enduml

```

## Back End

The backend  uses a MC style, combined with a layered style (application logic, data). 
The back end is implemented using the Spring framework for developing Java Entrerprise applications.

Spring was selected for its popularity and relative simplicity: persistency (M and data layer) and interactions are pre-implemented, the programmer needs only to add the specific parts.

See in the package diagram below the project structure of Spring.

For more information about the Spring design guidelines and naming conventions:  https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3



```plantuml
@startuml
package "Backend" {

package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"
   interface "UserService"
} 


package "it.polito.ezgas.controller" {

}

package "it.polito.ezgas.converter" {

}

package "it.polito.ezgas.dto" {

}

package "it.polito.ezgas.entity" {

}

package "it.polito.ezgas.repository" {

}

    
}
note "see folder ServicePackage" as n
n -- ps
```



The Spring framework implements the MC of the MVC pattern. The M is implemented in the packages Entity and Repository. The C is implemented in the packages Service, ServiceImpl and Controller. The packages DTO and Converter contain classes for translation services.



**Entity Package**

Each Model class should have a corresponding class in this package. Model classes contain the data that the application must handle.
The various models of the application are organised under the model package, their DTOs(data transfer objects) are present under the dto package.

In the Entity package all the Entities of the system are provided. Entities classes provide the model of the application, and represent all the data that the application must handle.




**Repository Package**

This package implements persistency for each Model class using an internal database. 

For each Entity class, a Repository class is created (in a 1:1 mapping) to allow the management of the database where the objects are stored. For Spring to be able to map the association at runtime, the Repository class associated to class "XClass" has to be exactly named "XClassRepository".

Extending class JpaRepository provides a lot of CRUD operations by inheritance. The programmer can also overload or modify them. 



**DTO package**

The DTO package contains all the DTO classes. DTO classes are used to transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database.

For each Entity class, a DTO class is created (in a 1:1 mapping).  For Spring the Dto class associated to class "XClass" must be called "XClassDto".  This allows Spring to find automatically the DTO class having the corresponding Entity class, and viceversa. 




**Converter Package**

The Converter Package contains all the Converter classes of the project.

For each Entity class, a Converter class is created (in a 1:1 mapping) to allow conversion from Entity class to DTO class and viceversa.

For Spring to be able to map the association at runtime, the Converter class associated to class "XClass" has to be exactly named "XClassConverter".




**Controller Package**

The controller package is in charge of handling the calls to the REST API that are generated by the user's interaction with the GUI. The Controller package contains methods in 1:1 correspondance to the REST API calls. Each Controller can be wired to a Service (related to a specific entity) and call its methods.
Services are in packages Service (interfaces of services) and ServiceImpl (classes that implement the interfaces)

The controller layer interacts with the service layer (packages Service and ServieImpl) 
 to get a job done whenever it receives a request from the view or api layer, when it does it should not have access to the model objects and should always exchange neutral DTOs.

The service layer never accepts a model as input and never ever returns one either. This is another best practice that Spring enforces to implement  a layered architecture.



**Service Package**


The service package provides interfaces, that collect the calls related to the management of a specific entity in the project.
The Java interfaces are already defined (see file ServicePackage.zip) and the low level design must comply with these interfaces.


**ServiceImpl Package**

Contains Service classes that implement the Service Interfaces in the Service package.










# Low level design

<Based on the official requirements and on the Spring Boot design guidelines, define the required classes (UML class diagram) of the back-end in the proper packages described in the high-level design section.>



```plantuml
@startuml
left to right direction
package "Backend" {
package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"{
      +getGasStationById(gasStationId)
      +saveGasStation(gasStationDto)
      +getAllGasStations()
      +deleteGasStation(gasStationId)
      +getGasStationsByGasolineType(gasolinetype)
      +getGasStationsByProximity(lat,lon)
      +getGasStationsWithCoordinates(lat,lon,gasolinetype,carsharing)
      +getGasStationsWithoutCoordinates(gasolinetype,carsharing) 
      +setReport(gasStationId,dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice,userId)
      +getGasStationByCarSharing(carSharing)
   }
   interface "UserService"{
      +getUserById(userId)
      +saveUser(userDto)
      +getAllUsers()
      +deleteUser(userId)
      +login(credentials)
      +increaseUserReputation(userId)
      +decreaseUserReputation(userId)
   }
   class "GasStationServiceimpl"
   class "UserServiceimpl"
   "GasStationServiceimpl" -|> "GasStationService"
   "UserServiceimpl" -|> "UserService"

}


package "it.polito.ezgas.controller" as pc{
   class "UserController"{
      +getUserById(userId)
      +getAllUsers()
      +saveUser(userDto)
      +deleteUser(userId)
      +increaseUserReputation(userId)
      +decreaseUserReputation(userId)
      +login(credentials)
   }
   class "GasStationController"{
      +getGasStationById(gasStationId)
      +getAllGasStations()
      +saveGasStation()
      +deleteUser(gasStationId)
      +getGasStationsByGasolineType(gasolinetype)
      +getGasStationsByProximity(myLat,myLon)
      +getGasStationsWithCoordinates(myLat,myLon,gasolineType,carSharing)
      +setGasStationReport(gasStationId,dieselPrice,superPrice,superPlusPrice,gasPrice,methanePrice,userId)
   }
   class "HomeController"{
      +admin()
      +index()
      +map()
      +login()
      +update()
      +signup()
   }
}

package "it.polito.ezgas.converter" {
   class "UserConverter"
   class "GasStationConverter"
   class "PriceReportConverter"
}

package "it.polito.ezgas.dto" {
   class "UserDto"{
      -userId
      -userName
      -password
      -email
      -reputation
      -admin
   }
   class "GasStationDto"{
      -gasStationId
      -gasStationName
      -gasStationAddress
      -hasDiesel
      -hasSuper
      -hasSuperPlus
      -hasGas
      -hasMethane
      -carSharing
      -lat
      -lon
      -dieselPrice
      -superPrice
      -superPlusPrice
      -gasPrice
      -methanePrice
      -reportUser
      -userDto
      -reportTimestamp
      -reportDependability
   }
   class "PriceReportDto"{
      -priceReportId
      -user
      -dieselPrice
      -superPrice
      -superPlusPrice
      -gasPrice
   }
   class "LoginDto"{
      -userId
      -userName
      -token
      -email
      -reputation
      -admin
   }
   class "IdPw"{
      -user
      -pw
   }
}

package "it.polito.ezgas.entity" {
   class "User"{
      -userId
      -userName
      -password
      -email
      -reputation
      -admin

   }
   class "GasStation"{
      -gasStationId
      -gasStationName
      -gasStationAddress
      -hasDiesel
      -hasSuper
      -hasSuperPlus
      -hasGas
      -hasMethane
      -carSharing
      -lat
      -lon
      -dieselPrice
      -superPrice
      -superPlusPrice
      -gasPrice
      -methanePrice
      -reportUser
      -reportTimestamp
      -reportDependability

   }
   class "PriceReport"{
      -dieselPrice
      -superPrice
      -superPlusPrice
      -gasPrice
   }
}

package "it.polito.ezgas.repository" {
   class "UserRepository"
   class "GasStationRepository"
   class "PriceReportRepository"
}

}

@enduml
```








# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>

|       | UserController | GasStationController | HomeController | UserService | GasStationService | UserRepository | GasStationRepository | PriceReportRepository | User          | GasStation |  PriceReport  | UserConverter | GasStationConverter  | PriceReportConverter | UserDto         | GasStationDto      | PriceReportDto | LoginDto | IdPw |
| :---: |:--------------:| :-------------:      | :---------: |:-------------:    | :-----:        | :-------------:      |:-------------:| :-----:    | :-------------: |:-------------:| :-----:            | :-------------: |:-------------:| :-----:              | :-------------: |:------------------:| :---:|:---:|:---:|
| FR1   |X| | |X| |X| | |X| | |X| | |X| | |X| | 
| FR1.1 |X| | |X| |X| | |X| | |X| | |X| | |X| | 
| FR1.2 |X| | |X| |X| | |X| | |X| | |X| | |X| | 
| FR1.3 |X| | |X| |X| | |X| | |X| | |X| | |X| | 
| FR1.4 |X| | |X| |X| | |X| | |X| | |X| | |X| | 
| FR2   |X| | |X| |X| | |X| | |X| | |X| | |X|X| 
| FR3   | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR3.1 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR3.2 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR3.3 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4   | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4.1 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4.2 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4.3 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4.4 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR4.5 | |X| | |X| |X|X| |X|X| |X|X| |X|X| | | 
| FR5   |X|X| |X|X|X|X|X|X|X|X|X|X|X|X|X|X| | | 
| FR5.1 |X|X| |X|X|X|X|X|X|X|X|X|X|X|X|X|X| | | 
| FR5.2 |X|X| |X|X|X|X|X|X|X|X|X|X|X|X|X|X| | | 
| FR5.3 |X|X| |X|X|X|X|X|X|X|X|X|X|X|X|X|X| | | 

# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

## Sequence diagram for use case 1
```plantuml
"Front End" -> UserController:1 : saveUser()
activate UserController
UserController -> UserServiceimpl:2 : saveUser()

UserServiceimpl -> UserRepository:3 : saveUser()

UserController -> UserConverter:4 : saveUser()

UserConverter -> UserDto:5 : saveUser()
deactivate UserController

"Front End" -> UserController:6 : getAllUsers()
activate UserController

UserController -> UserServiceimpl:7 : getAllUsers()

UserServiceimpl -> UserRepository:8 : getAllUsers()

UserController -> UserConverter:9 : getAllUsers()

UserConverter -> UserDto:10 : getAllUsers()
deactivate UserController
```

## Sequence diagram for use case 2

```plantuml

"Front End" -> UserController:1 : saveUser()
activate UserController

UserController -> UserServiceimpl:2 : saveUser()

UserServiceimpl -> UserRepository:3 : saveUser()

UserController -> UserConverter:4 : saveUser()

UserConverter -> UserDto:5 : saveUser()
deactivate UserController

"Front End" -> UserController :6 : getAllUsers()
activate UserController
UserController -> UserServiceimpl :7 : getAllUsers()

UserServiceimpl -> UserRepository :8 : getAllUsers()

UserController -> UserConverter:9 : getAllUsers()

UserConverter -> UserDto:10 : getAllUsers()
deactivate UserController
```
## Sequence diagram for use case 3
```plantuml
"Front End" -> UserController:1 : deleteUser()
activate UserController

UserController -> UserServiceimpl:2 : deleteUser()

UserServiceimpl -> UserRepository:3 : deleteUser()

UserController -> UserConverter:4 : deleteUser()

UserConverter -> UserDto:5 : deleteUser()
deactivate UserController

"Front End" -> UserController:6 : getAllUsers()
activate UserController

UserController -> UserServiceimpl:7 : getAllUsers()

UserServiceimpl -> UserRepository:8 : getAllUsers()

UserController -> UserConverter:9 : getAllUsers()

UserConverter -> UserDto:10 : getAllUsers()
deactivate UserController
```

## Sequence diagram for use case 4

```plantuml
"Front End" -> GasStationController:1 : saveGasStation()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : saveGasStation()

GasStationServiceimpl -> GasStationRepository:3 : saveGasStation()

GasStationController -> GasStationConverter:4 : saveGasStation()

GasStationConverter -> GasStationDto:5 : saveGasStation()
deactivate GasStationController

"Front End" -> GasStationController:6 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : getAllGasStations()

GasStationServiceimpl -> GasStationRepository:8 : getAllGasStations()

GasStationController -> GasStationConverter:9 : getAllGasStations()

GasStationConverter -> GasStationDto:10 : getAllGasStations()
deactivate GasStationController

```

## Sequence diagram for use case 5
```plantuml
"Front End" -> GasStationController:1 : saveGasStation()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : saveGasStation()

GasStationServiceimpl -> GasStationRepository:3 : saveGasStation()

GasStationController -> GasStationConverter:4 : saveGasStation()

GasStationConverter -> GasStationDto:5 : saveGasStation()
deactivate GasStationController

"Front End" -> GasStationController:6 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : getAllGasStations()

GasStationServiceimpl -> GasStationRepository:8 : getAllGasStations()

GasStationController -> GasStationConverter:9 : getAllGasStation()

GasStationConverter -> GasStationDto:10 : getAllGasStation()
deactivate GasStationController
```


## Sequence diagram for use case 6

```plantuml

"Front End" -> GasStationController:1 : deleteGasStation()
activate GasStationController

GasStationController -> GasStationServiceimpl:2 : deleteGasStation()

GasStationServiceimpl -> GasStationRepository:3 : deleteGasStation()

GasStationController -> GasStationConverter:4 : deleteGasStation()

GasStationConverter -> GasStationDto:5 : deleteGasStation()
deactivate GasStationController

"Front End" -> GasStationController :6 : getAllGasStations()
activate GasStationController

GasStationController -> GasStationServiceimpl :7 : getAllGasStations()

GasStationServiceimpl -> GasStationRepository :8 : getAllGasStations()

GasStationController -> GasStationConverter:9 : getAllGasStation()

GasStationConverter -> GasStationDto:10 : getAllGasStation()
deactivate GasStationController

```
## Sequence diagram for use case 7

```plantuml
"Front End" -> UserController:1 : getUserById()
activate UserController

UserController -> UserServiceimpl:2 : getUserById()

UserServiceimpl -> UserRepository:3 : getUserById()

UserController -> UserConverter:4 : getUserById()

UserConverter -> UserDto:5 : getUserById()
deactivate UserController

"Front End" -> GasStationController:6 : setReport()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : setGasStationReport()

GasStationServiceimpl -> GasStationRepository:8 : setReport()

GasStationController -> GasStationConverter:9 : setReport()

GasStationConverter -> GasStationDto:10 : setReport()
deactivate GasStationController

"Front End" -> GasStationController:11 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:12 : getAllGasStations()

GasStationServiceimpl -> GasStationRepository:13 : getAllGasStations()

GasStationController -> GasStationConverter:14 : getAllGasStation()

GasStationConverter -> GasStationDto:15 : getAllGasStation()
deactivate GasStationController

```
## Sequence diagram for use case 8

```plantuml
"Front End" -> GasStationController:1 : getGasStationsWithCoordinates()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationsWithCoordinates()

GasStationServiceimpl -> GasStationRepository:3 : getGasStationsWithCoordinates()

GasStationController -> GasStationConverter:4 : getGasStationsWithCoordinates()

GasStationConverter -> GasStationDto:5 : getGasStationsWithCoordinates()
deactivate GasStationController
```

## Sequence diagram for use case 9

```plantuml

"Front End" -> UserController:1 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:2 : getAllUsers()
UserServiceimpl -> UserRepository:3 : getAllUsers()
UserController -> UserConverter:4 : getAllUsers()
UserConverter -> UserDto:5 : getAllUsers()
deactivate UserController

```

## Sequence diagram for scenario 10.1

```plantuml
"Front End" -> GasStationController:1 : getStationById()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationById()
GasStationServiceimpl -> GasStationRepository:3 : getGasStationById()
GasStationController -> GasStationConverter:4 : getGasStationById()
GasStationConverter -> GasStationDto:5 : getGasStationById()
deactivate GasStationController


"Front End" -> UserController:6 : getUserById()
activate UserController
UserController -> UserServiceimpl:7 : getUserById()
UserServiceimpl -> UserRepository:8 : getUserById()
UserController -> UserConverter:9 : getUserById()
UserConverter -> UserDto:10 : getUserById()
deactivate UserController

"Front End" -> UserController :11 : increaseUserReputation()
activate UserController
UserController -> UserServiceimpl :12 : increaseUserReputation()
UserServiceimpl -> UserRepository :13 : increaseUserReputation()
UserController -> UserConverter:14 : increaseUserReputation()
UserConverter -> UserDto:15 : increaseUserReputation()
deactivate UserController

"Front End" -> UserController :16 : getAllUsers()
activate UserController
UserController -> UserServiceimpl :17 : getAllUsers()
UserServiceimpl -> UserRepository :18 : getAllUsers()
UserController -> UserConverter:19 : getAllUsers()
UserConverter -> UserDto:20 : getAllUsers()
deactivate UserController
```

## Sequence diagram for scenario 10.2

```plantuml
"Front End" -> GasStationController:1 : getStationById()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationById()
GasStationServiceimpl -> GasStationRepository:3 : getGasStationById()
GasStationController -> GasStationConverter:4 : getGasStationById()
GasStationConverter -> GasStationDto:5 : getGasStationById()
deactivate GasStationController


"Front End" -> UserController:6 : getUserById()
activate UserController
UserController -> UserServiceimpl:7 : getUserById()
UserServiceimpl -> UserRepository:8 : getUserById()
UserController -> UserConverter:9 : getUserById()
UserConverter -> UserDto:10 : getUserById()
deactivate UserController

"Front End" -> UserController :11 : increaseUserReputation()
activate UserController
UserController -> UserServiceimpl :12 : decreaseUserReputation()
UserServiceimpl -> UserRepository :13 : decreaseUserReputation()
UserController -> UserConverter:14 : decreaseUserReputation()
UserConverter -> UserDto:15 : decreaseUserReputation()
deactivate UserController

"Front End" -> UserController :16 : getAllUsers()
activate UserController
UserController -> UserServiceimpl :17 : getAllUsers()
UserServiceimpl -> UserRepository :18 : getAllUsers()
UserController -> UserConverter:19 : getAllUsers()
UserConverter -> UserDto:20 : getAllUsers()
deactivate UserController
```