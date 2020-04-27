# Project Estimation  

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli 

Date: 27/04/2020

Version: 1

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   |    11                         |             
|  A = Estimated average size per class, in LOC       |              170              | 
| S = Estimated size of project, in LOC (= NC * A) | 1870|
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  |  187                                    |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 5610 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) |        1.5            |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
|Perform workflow analysis | 8|
|Model process | 4|
|Identify user requirements | 24|
|Identify performance requirements | 3|
|Identify interface requirements |6|
|Prepare software requirements specification | 32|
|Design GUI | 64|



###
Gantt chart
```plantuml

printscale daily
Project starts the 6th of april 2020
[Requirements Planning] as [TASK1] lasts 11 days
[TASK1] is colored in Lavender/LightBlue

[Perform workflow analysis] lasts 1 day


[Model process] lasts 1 days
[Perform workflow analysis]->[Model process]

[Identify user requirements] lasts 3 days
[Model process]->[Identify user requirements]

[Identify performance requirements] lasts 1 days
[Identify user requirements]->[Identify performance requirements]

[Identify interface requirements] lasts 1 days
[Identify performance requirements]->[Identify interface requirements]

[Prepare software requirements specification] lasts 4 days
[Identify interface requirements]->[Prepare software requirements specification]

[Prototype design] as [TASK2] lasts 4 days
[TASK2] is colored in Lavender/LightBlue
[TASK2] starts at [TASK1]'s end

[Design GUI] lasts 4 days
[Prepare software requirements specification]->[Design GUI]

```
