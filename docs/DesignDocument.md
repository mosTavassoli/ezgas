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


```plantuml
@startuml
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
   class "GasStationServiceimpl"{
      -getAllPriceReports(gasStationId)
   }
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
   class "UserConverter"{
      +toUserDto(user)
      +toLoginDto(user)
      +toEntity(userDto)
   }
   class "GasStationConverter"{
      +toGasStationDto(gasStation)
      +toGasStationDto(gasStation,priceReportDtos)
      +toEntity(gasStationDto)
   }
   class "PriceReportConverter"{
      +toPriceReportDto(priceReport)
      +toEntity(priceReportDto)
   }
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
      -priceReportId
      -user
      -dieselPrice
      -superPrice
      -superPlusPrice
      -gasPrice
   }

}

package "it.polito.ezgas.repository" {
   class "UserRepository"{
      +findAll()
      +findById(userId)
      +save(user)
      +delete(user)
      +existsById(userId)
   }
   class "GasStationRepository"{
      +findAll()
      +findById(gasStationId)
      +save(gasStation)
      +delete(gasStation)
   }
   class "PriceReportRepository"{
      +findAll()
      +findById(priceReportId)
      +save(priceReport)
      +delete(priceReport)
   }
}

}

"UserController" "1"-----"1" "UserService"
"UserServiceimpl" "1"-----"1" "UserRepository"
"UserServiceimpl" "1"-----"1" "UserConverter"
"UserConverter" "1"-----"1" "UserDto"
"UserConverter" "1"-----"1" "LoginDto"
"UserServiceimpl" "1"-----"1" "IdPw"


"GasStationController" "1"-----"1" "GasStationService"
"GasStationServiceimpl" "1"-----"1" "PriceReportRepository"
"GasStationServiceimpl" "1"-----"1" "GasStationRepository"
"GasStationServiceimpl" "1"-----"1" "GasStationConverter"
"GasStationConverter" "1"-----"1" "GasStationDto"

"GasStationServiceimpl" "1"-----"1" "PriceReportConverter"
"PriceReportConverter" "1"-----"1" "PriceReportDto"

@enduml
```


# Verification traceability matrix


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


## Sequence diagram for use case 1
```plantuml
database Database order 30
"Front End" -> UserController:1 : saveUser()
activate UserController
UserController -> UserServiceimpl:2 : saveUser()
activate UserServiceimpl
UserServiceimpl -> UserConverter:3 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:4 : findByEmail()
activate UserRepository
UserRepository -> Database:
activate Database
return
return
UserServiceimpl -> UserRepository:5 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:6 : toDto()
activate UserConverter
return
return
return
"Front End" -> UserController:7 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:8 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:9 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:10 : toDto()
activate UserConverter
return
return
return
```

## Sequence diagram for use case 2

```plantuml
database Database order 30
"Front End" -> UserController:1 : saveUser()
activate UserController
UserController -> UserServiceimpl:2 : saveUser()
activate UserServiceimpl
UserServiceimpl -> UserConverter:3 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:4 : findByEmail()
activate UserRepository
UserRepository -> Database:
activate Database
return
return
UserServiceimpl -> UserRepository:5 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:6 : toDto()
activate UserConverter
return
return
return

"Front End" -> UserController:7 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:8 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:9 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:10 : toDto()
activate UserConverter
return
return
return

```
## Sequence diagram for use case 3
```plantuml
database Database order 30
"Front End" -> UserController:1 : deleteUser()
activate UserController
UserController -> UserServiceimpl:2 : deleteUser()
activate UserServiceimpl
UserServiceimpl -> UserRepository:3 : exists()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserRepository:4 : delete()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserRepository:5 : exists()
activate UserRepository
UserRepository -> Database
activate Database
return
return
return
return

"Front End" -> UserController:6 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:7 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:8 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:9 : toDto()
activate UserConverter
return
return
return
```

## Sequence diagram for use case 4

```plantuml
database Database order 30
"Front End" -> GasStationController:1 : saveGasStation()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : saveGasStation()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationConverter:3 : toEntity()
activate GasStationConverter
return
GasStationServiceimpl -> GasStationRepository:4 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:5 : toDto()
activate GasStationConverter
return
return
return

"Front End" -> GasStationController:6 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:8 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:9 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:10 : toDto()
activate GasStationConverter
return
return
return
```

## Sequence diagram for use case 5
```plantuml
database Database order 30
"Front End" -> GasStationController:1 : saveGasStation()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : saveGasStation()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationConverter:3 : toEntity()
activate GasStationConverter
return
GasStationServiceimpl -> GasStationRepository:4 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:5 : toDto()
activate GasStationConverter
return
return
return

"Front End" -> GasStationController:6 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:8 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:9 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:10 : toDto()
activate GasStationConverter
return
return
return
```


## Sequence diagram for use case 6

```plantuml
database Database order 30
"Front End" -> GasStationController:1 : deleteGasStation()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : deleteGasStation()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:3 : exists()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:4 : delete()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
return
return

"Front End" -> GasStationController:5 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:6 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:7 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:8 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:9 : toDto()
activate GasStationConverter
return
return
return
```
## Sequence diagram for use case 7

```plantuml
database Database order 30
"Front End" -> UserController:1 : getUserById()
activate UserController
UserController -> UserServiceimpl:2 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:3 : findByUserId()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:4 : toDto()
activate UserConverter
return
return
return

"Front End" -> GasStationController:5 : setGasStationReport()
activate GasStationController
GasStationController -> GasStationServiceimpl:6 : setReport()
activate GasStationServiceimpl

GasStationServiceimpl -> GasStationServiceimpl:7 : getGasStationById()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:8 : exists()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:8 : findOne()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:8 : toDto()
activate GasStationConverter
return
return


GasStationServiceimpl -> GasStationRepository:8 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
return
return

"Front End" -> GasStationController:6 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:7 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:8 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:9 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:10 : toDto()
activate GasStationConverter
return
return
return
```
## Sequence diagram for use case 8

```plantuml
database Database order 30
"Front End" -> GasStationController:1 : getGasStationsWithCoordinates()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationsWithCoordinates()
activate GasStationServiceimpl

GasStationServiceimpl -> GasStationServiceimpl: 3 : getGasStationsByProximity()
activate GasStationServiceimpl
GasStationServiceimpl -> gasStationRepository: 4 : findByLatBetweenAndLonBetween()
activate gasStationRepository
gasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter: 4 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter: 4 : toDto()
activate GasStationConverter
return
return
return

GasStationServiceimpl -> GasStationServiceimpl: 3 : getGasStationsWithoutCoordinates()
activate GasStationServiceimpl
return
return
return
```

## Sequence diagram for use case 9

```plantuml
database Database order 30
"Front End" -> UserController:1 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:2 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:3 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:4 : toDto()
activate UserConverter
return
return
return

"Front End" -> GasStationController:5 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:6 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:7 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:8 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:9 : toDto()
activate GasStationConverter
return
return
return
```

## Sequence diagram for scenario 10.1

```plantuml
database Database order 30
"Front End" -> GasStationController:1 : getGasStationById()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationById()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:3 : findById()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationServiceimpl:4 : getAllPriceReports()
activate GasStationServiceimpl
GasStationServiceimpl -> PriceReportRepository:5 : findAll()
activate PriceReportRepository
PriceReportRepository -> Database
activate Database
return
return
GasStationServiceimpl -> PriceReportConverter:6 : toPriceReportDto()
activate PriceReportConverter
return
deactivate GasStationServiceimpl
GasStationServiceimpl -> GasStationConverter:7 : toGasStationDto()
activate GasStationConverter
return
return
return

"Front End" -> UserController:8 : getUserById()
activate UserController
UserController -> UserServiceimpl:9 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:10 : findById()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:11 : toUserDto()
activate UserConverter
return
return
return

"Front End" -> UserController:12 : increaseUserReputation()
activate UserController
UserController -> UserServiceimpl:13 : increaseUserReputation()
activate UserServiceimpl
UserServiceimpl -> UserConverter:14 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:15 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
return
return

"Front End" -> UserController:16 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:17 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:18 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:19 : toUserDto()
activate UserConverter
return
return
return
```

## Sequence diagram for scenario 10.2

```plantuml
database Database order 30
"Front End" -> GasStationController:1 : getGasStationById()
activate GasStationController
GasStationController -> GasStationServiceimpl:2 : getGasStationById()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:3 : findById()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationServiceimpl:4 : getAllPriceReports()
activate GasStationServiceimpl
GasStationServiceimpl -> PriceReportRepository:5 : findAll()
activate PriceReportRepository
PriceReportRepository -> Database
activate Database
return
return
GasStationServiceimpl -> PriceReportConverter:6 : toPriceReportDto()
activate PriceReportConverter
return
deactivate GasStationServiceimpl
GasStationServiceimpl -> GasStationConverter:7 : toGasStationDto()
activate GasStationConverter
return
return
return

"Front End" -> UserController:8 : getUserById()
activate UserController
UserController -> UserServiceimpl:9 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:10 : findById()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:11 : toUserDto()
activate UserConverter
return
return
return

"Front End" -> UserController:12 : decreaseUserReputation()
activate UserController
UserController -> UserServiceimpl:13 : decreaseUserReputation()
activate UserServiceimpl
UserServiceimpl -> UserConverter:14 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:15 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
return
return

"Front End" -> UserController:16 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:17 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:18 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:19 : toUserDto()
activate UserConverter
return
return
return
```