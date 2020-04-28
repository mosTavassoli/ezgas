# Project Estimation  

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli 

Date: 27/04/2020

Version: 1.2

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
|**Requirements Planning** | |
|Perform workflow analysis | 8|
|Model process | 4|
|Identify user requirements | 24|
|Identify performance requirements | 3|
|Identify interface requirements |6|
|Design GUI | 64|
|Prepare software requirements specification | 32|
|**Requirements V&V**| |
|Requirements inspection | 16|
|**Design document**| |
|Analysis | 50|
|Formalization| 15|
|Verification | 10|
|**Design document V&V**| |
|Design document inspection |16|
|**Coding**| |
|Implement the code| 187|
|**Documentation V&V**| |
|Explain design document and code | 10|
|**System test**| |
|Test all units of application| 54|
|**Test plan**| |
|Prepare testing process | 16|
|**Unit test**| |
|Test each unit of the software| 80 |
|**Integration test**| |
|Combine individual units and test them as a group| 52 |


###
Gantt chart
```plantuml

printscale daily
Project starts the 6th of april 2020
[Requirements Planning] as [TASK1] lasts 15 days
[TASK1] is colored in Red

[Perform workflow analysis] lasts 1 day


[Model process] lasts 1 days
[Perform workflow analysis]->[Model process]

[Identify user requirements] lasts 3 days
[Model process]->[Identify user requirements]

[Identify performance requirements] lasts 1 days
[Identify user requirements]->[Identify performance requirements]

[Identify interface requirements] lasts 1 days
[Identify performance requirements]->[Identify interface requirements]

[Design GUI] lasts 4 days
[Identify interface requirements]->[Design GUI]

[Prepare software requirements specification] lasts 4 days
[Design GUI]->[Prepare software requirements specification]





[Requirements V&V] as [TASK1.1] lasts 2 days
[TASK1.1] is colored in LightBlue
[TASK1.1] starts at [TASK1]'s end

[Requirements inspection] lasts 2 days
[TASK1]->[Requirements inspection]








[Design document] as [TASK2] lasts 8 days
[TASK2] is colored in Red
[TASK2] starts at [TASK1]'s end

[Analysis] lasts 4 days
[TASK1]->[Analysis]

[Formalization] lasts 2 days
[Analysis]->[Formalization]

[Verification] lasts 2 days
[Formalization]->[Verification]

[Design document V&V] as [TASK2.1] lasts 2 days
[TASK2.1] is colored in LightBlue
[TASK2.1] starts at [TASK2]'s end

[Design document inspection] lasts 2 days
[TASK2]->[Design document inspection]



[Coding] as [TASK3] lasts 14 days
[TASK3] is colored in Red
[TASK3] starts at [TASK2]'s end

[Implement the code] lasts 14 days
[TASK2]->[Implement the code]

[Documentation] as [TASK3.1] lasts 4 days
[TASK3.1] is colored in LightBlue
[TASK3.1] starts at [TASK2]'s end

[Explain design document and code] lasts 4 days
[TASK2]->[Explain design document and code]



[System test] as [TASK4] lasts 8 days
[TASK4] is colored in Red
[TASK4] starts 2 days after [TASK3]'s end

[Test all units of application] lasts 8 days
[Test all units of application] starts 2 days after [TASK3]'s end



[Test plan] as [TASK1.2] lasts 8 days
[TASK1.2] is colored in LightBlue

[Prepare testing process] lasts 8 days


[Unit test] lasts 14 days
[Unit test] starts 15 days after [TASK1.2]'s end 
[Unit test] is colored in LightBlue

[Test each unit of the software] lasts 14 days
[Test each unit of the software] starts 15 days after [TASK1.2]'s end 

[Integration test] lasts 14 days
[Integration test] starts 2 days after [Unit test]'s start 
[Integration test] is colored in LightBlue
[Combine individual units and test them as a group] lasts 14 days
[Combine individual units and test them as a group] starts 2 days after [Unit test]'s start 


```
