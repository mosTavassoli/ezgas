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
      -gasStationRepository
      -userService
   }
   class "UserServiceimpl"{
      -userRepository
   }
   "GasStationServiceimpl" -|> "GasStationService"
   "UserServiceimpl" -|> "UserService"

}


package "it.polito.ezgas.controller" as pc{
   class "UserController"{
      -userService
      +getUserById(userId)
      +getAllUsers()
      +saveUser(userDto)
      +deleteUser(userId)
      +increaseUserReputation(userId)
      +decreaseUserReputation(userId)
      +login(credentials)
   }
   class "GasStationController"{
      -gasStationService
      +getGasStationById(gasStationId)
      +getAllGasStations()
      +saveGasStation(gasStationDto)
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
      +toDto(user)
      +toDto(userList)
      +toEntity(userDto)
   }
   class "GasStationConverter"{
      +toDto(entity)
      +toDto(entityList)
      +toEntity(dto)
   }
   class "LoginConverter"{
      +toDto(user)
   }
}

package "it.polito.ezgas.dto" {
   class "PriceReportDto"{
      -dieselPrice
      -gasPrice
      -gasStationId
      -methanePrice
      -premiumDieselPrice
      -superPlusPrice
      superPrice
      -userId
      +PriceReportDto()
      +PriceReportDto(Integer, Double, Double, Double, Double, Double, Double, Integer)
      getDieselPrice()
      +getGasPrice()
      +getGasStationId()
      +getMethanePrice()
      +getPremiumDieselPrice()
      +getSuperPlusPrice()
      +getSuperPrice()
      +getUserId()
      +setDieselPrice(Double)
      +setGasPrice(Double)
      +setGasStationId(Integer)
      +setMethanePrice(Double)
      +setPremiumDieselPrice(Double)
      +setSuperPlusPrice(Double)
      +setSuperPrice(Double)
      +setUserId(Integer)
   }
   class "UserDto"{
      -userId
      -userName
      -password
      -email
      -reputation
      -admin
      +editUserReputation(modifier)
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
      +checkCoordinates(lat, lon)
      +checkPrices()
      +computeReportDependability()

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
      -serialVersionUID
      -userId
      -userName
      -password
      -email
      -reputation
      -admin

   }
   class "GasStation"{
      -serialVersionUID
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
      -user
   }

}

package "it.polito.ezgas.repository" {
   class "UserRepository"{
      +findByEmail(user)
      +findByUserId(userId)
      +save(user)
      +findAll()
      +exists(userId)
      +delete(userId)
   }
   class "GasStationRepository"{
      +findByCarSharingOrderByGasStationName(carSharing)
      +findByHasDieselOrderByDieselPriceAsc(hasDiesel)
      +findByHasGasOrderByGasPriceAsc(hasGas)
      +findByHasMethaneOrderByMethanePriceAsc(hasMethane)
      +findByHasSuperOrderBySuperPriceAsc(hasSuper)
      +findByHasSuperPlusOrderBySuperPlusPriceAsc(hasSuperPlus)
      
      +findByProximity(lat, lon)
      +save(gasStation)
      +findAll()
      +exists(gasStationId)
      +delete(gasStationId)
      +count(gasStationId)
      +findOne(gasStationId)
   }
}

}

"UserController" "1"-----"1" "UserService"
"UserServiceimpl" "1"-----"1" "UserRepository"
"UserServiceimpl" "1"-----"1" "UserConverter"
"UserConverter" "1"-----"1" "UserDto"
"LoginConverter" "1"-----"1" "LoginDto"
"UserServiceimpl" "1"-----"1" "IdPw"


"GasStationController" "1"-----"1" "GasStationService"
"GasStationServiceimpl" "1"-----"1" "GasStationRepository"
"GasStationServiceimpl" "1"-----"1" "GasStationConverter"
"GasStationConverter" "1"-----"1" "GasStationDto"
"UserServiceimpl" "1"-----"1" "LoginConverter"
"GasStationController" "1"-----"1" "PriceReportDto"

@enduml
```


# Verification traceability matrix


|       | UserController | GasStationController | HomeController | UserService | GasStationService | UserRepository | GasStationRepository | User          | GasStation | UserConverter | GasStationConverter  | UserDto         | GasStationDto      | LoginDto | IdPw |PriceReportDto|
| :---: |:--------------:| :-------------:      | :---------: |:-------------:    | :-----:        | :-------------:      |:-------------:| :-------------: |:-------------:| :-------------: |:-------------:| :-------------: |:------------------:|:---:|:---:|:----:|
| FR1   |X| | |X| |X| |X| |X| |X| |X| | |
| FR1.1 |X| | |X| |X| |X| |X| |X| |X| | |
| FR1.2 |X| | |X| |X| |X| |X| |X| |X| | |
| FR1.3 |X| | |X| |X| |X| |X| |X| |X| | |
| FR1.4 |X| | |X| |X| |X| |X| |X| |X| | |
| FR2   |X| | |X| |X| |X| |X| |X| |X|X| |
| FR3   | |X| | |X| |X| |X| |X| |X| | | |
| FR3.1 | |X| | |X| |X| |X| |X| |X| | | |
| FR3.2 | |X| | |X| |X| |X| |X| |X| | | |
| FR3.3 | |X| | |X| |X| |X| |X| |X| | | |
| FR4   | |X| | |X| |X| |X| |X| |X| | | |
| FR4.1 | |X| | |X| |X| |X| |X| |X| | | |
| FR4.2 | |X| | |X| |X| |X| |X| |X| | | |
| FR4.3 | |X| | |X| |X| |X| |X| |X| | | |
| FR4.4 | |X| | |X| |X| |X| |X| |X| | | |
| FR4.5 | |X| | |X| |X| |X| |X| |X| | | |
| FR5   |X|X| |X|X|X|X|X|X|X|X|X|X| | |X|
| FR5.1 |X|X| |X|X|X|X|X|X|X|X|X|X| | |X|
| FR5.2 |X|X| |X|X|X|X|X|X|X|X|X|X| | | |
| FR5.3 |X|X| |X|X|X|X|X|X|X|X|X|X| | | |

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
UserConverter -> UserConverter:11 : toDto()
activate UserConverter
return
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
UserConverter -> UserConverter:11 : toDto()
activate UserConverter
return
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
UserConverter -> UserConverter:10 : toDto()
activate UserConverter
return
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
GasStationServiceimpl -> GasStationDto: 3 : checkPrices()
activate GasStationDto
return
GasStationServiceimpl -> GasStationDto: 4 : checkCoordinates()
activate GasStationDto
return
GasStationServiceimpl -> GasStationConverter:5 : toEntity()
activate GasStationConverter
GasStationConverter -> UserConverter:6 : toEntity()
activate UserConverter
return
return
GasStationServiceimpl -> GasStationRepository:7 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:8 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:9 : computeReportDependability()
activate GasStationDto

return
return
return
return

"Front End" -> GasStationController:10 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:11 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:12 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:13 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:14 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter:15 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:16 : computeReportDependability()
activate GasStationDto
return
return
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
GasStationServiceimpl -> GasStationDto: 3 : checkPrices()
activate GasStationDto
return
GasStationServiceimpl -> GasStationDto: 4 : checkCoordinates()
activate GasStationDto
return
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:3 : exists()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:5 : toEntity()
activate GasStationConverter
GasStationConverter -> UserConverter:6 : toEntity()
activate UserConverter
return
return
GasStationServiceimpl -> GasStationRepository:7 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:8 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:9 : computeReportDependability()
activate GasStationDto

return
return
return
return

"Front End" -> GasStationController:10 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:11 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:12 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:13 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:14 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter:15 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:16 : computeReportDependability()
activate GasStationDto
return
return
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
GasStationServiceimpl -> GasStationRepository:5 : exists()
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
GasStationConverter -> GasStationConverter:11 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:12 : computeReportDependability()
activate GasStationDto
return
return
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
GasStationServiceimpl -> GasStationRepository:9 : findOne()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:10 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:11 : computeReportDependability()
activate GasStationDto
return
return
return

GasStationServiceimpl -> UserServiceimpl:12 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:13 : findByUserId()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:14 : toDto()
activate UserConverter
return
return





GasStationServiceimpl -> GasStationDto:15 computeReportDependability()
activate GasStationDto

return

GasStationServiceimpl -> GasStationDto:16 checkPrices()
activate GasStationDto

return

GasStationServiceimpl -> GasStationConverter:17 : toEntity()
activate GasStationConverter
GasStationConverter -> UserConverter:18 : toEntity()
activate UserConverter
return
return

GasStationServiceimpl -> GasStationRepository:19 : save()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
return
return

"Front End" -> GasStationController:20 : getAllGasStations()
activate GasStationController
GasStationController -> GasStationServiceimpl:21 : getAllGasStations()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository:22 : count()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:23 : findAll()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return

GasStationServiceimpl -> GasStationConverter:24 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter:25 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:26 : computeReportDependability()
activate GasStationDto
return
return
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
GasStationServiceimpl -> GasStationDto: 4 : checkCoordinates()
activate GasStationDto
return


GasStationServiceimpl -> GasStationRepository: 5 : findByProximity()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter: 6 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter: 7 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:8 : computeReportDependability()
activate GasStationDto
return
return
return
return

GasStationServiceimpl -> GasStationServiceimpl: 9 : getGasStationsWithoutCoordinates()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationServiceimpl: 10 : getGasStationsByGasolineType()
activate GasStationServiceimpl
GasStationServiceimpl ->GasStationRepository: 11 : findByHas<Fuel>OrderBy<Fuel>PriceAsc()
activate GasStationRepository
return
GasStationServiceimpl -> GasStationConverter: 12 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter: 13 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:14 : computeReportDependability()
activate GasStationDto
return
return
return

return
GasStationServiceimpl -> GasStationServiceimpl: 15 : getGasStationByCarSharing()
activate GasStationServiceimpl
GasStationServiceimpl -> GasStationRepository: 16 : findByCarSharingOrderByGasStationName()
activate GasStationRepository
return
GasStationServiceimpl -> GasStationConverter: 17 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationConverter: 18 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:19 : computeReportDependability()
activate GasStationDto
return
return
return
return
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
UserConverter -> UserConverter:5 : toDto()
activate UserConverter
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
GasStationConverter -> GasStationConverter:11 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:12 : computeReportDependability()
activate GasStationDto
return
return
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
GasStationServiceimpl -> GasStationRepository:3 : exists()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:4 : findOne()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:5 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:6 : computeReportDependability()
activate GasStationDto
return
return
return
return


"Front End" -> UserController:7 : getUserById()
activate UserController
UserController -> UserServiceimpl:8 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:9 : findByUserId()
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


"Front End" -> UserController:11 : increaseUserReputation()
activate UserController
UserController -> UserServiceimpl:12 : increaseUserReputation()
activate UserServiceimpl
UserServiceimpl -> UserServiceimpl:13 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:14 : findByUserId()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:15 : toDto()
activate UserConverter
return
return

UserServiceimpl -> userDto:16 : editUserReputation()
activate userDto
return

UserServiceimpl -> UserServiceimpl:17 : saveUser()
activate UserServiceimpl
UserServiceimpl -> UserConverter:18 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:19 : findByEmail()
activate UserRepository
UserRepository -> Database:
activate Database
return
return
UserServiceimpl -> UserRepository:20 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:21 : toDto()
activate UserConverter
return
return

return
return

"Front End" -> UserController:22 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:23 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:24 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:25 : toDto()
activate UserConverter
UserConverter -> UserConverter:26 : toDto()
activate UserConverter
return
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
GasStationServiceimpl -> GasStationRepository:3 : exists()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationRepository:4 : findOne()
activate GasStationRepository
GasStationRepository -> Database
activate Database
return
return
GasStationServiceimpl -> GasStationConverter:5 : toDto()
activate GasStationConverter
GasStationConverter -> GasStationDto:6 : computeReportDependability()
activate GasStationDto
return
return
return
return


"Front End" -> UserController:7 : getUserById()
activate UserController
UserController -> UserServiceimpl:8 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:9 : findByUserId()
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


"Front End" -> UserController:11 : decreaseUserReputation()
activate UserController
UserController -> UserServiceimpl:12 : decreaseUserReputation()
activate UserServiceimpl
UserServiceimpl -> UserServiceimpl:13 : getUserById()
activate UserServiceimpl
UserServiceimpl -> UserRepository:14 : findByUserId()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:15 : toDto()
activate UserConverter
return
return

UserServiceimpl -> userDto:16 : editUserReputation()
activate userDto
return

UserServiceimpl -> UserServiceimpl:17 : saveUser()
activate UserServiceimpl
UserServiceimpl -> UserConverter:18 : toEntity()
activate UserConverter
return
UserServiceimpl -> UserRepository:19 : findByEmail()
activate UserRepository
UserRepository -> Database:
activate Database
return
return
UserServiceimpl -> UserRepository:20 : save()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:21 : toDto()
activate UserConverter
return
return

return
return

"Front End" -> UserController:22 : getAllUsers()
activate UserController
UserController -> UserServiceimpl:23 : getAllUsers()
activate UserServiceimpl
UserServiceimpl -> UserRepository:24 : findAll()
activate UserRepository
UserRepository -> Database
activate Database
return
return
UserServiceimpl -> UserConverter:25 : toDto()
activate UserConverter
UserConverter -> UserConverter:26 : toDto()
activate UserConverter
return
return
return
return
```