# Project Estimation  

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli 

Date: 03/05/2020

Version: 1.4

# Contents
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition](#estimate-by-activity-decomposition)




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
|Implement the code| 168|
|**Documentation**| |
|Explain design document and code | 10|
|**System test**| |
|Test all units of application| 54|
|**Test plan**| |
|Prepare testing process | 16|
|**Unit test**| |
|Test each unit of the software| 80 |
|**Integration test**| |
|Combine individual units and test them as a group| 52 |




### Gantt chart
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


Considering the fact that we are students, the calculations made were not based on 8 hours of work per day. And the calculations did not take into consideration weekends. Weekends were considered as normal work days (since we're students).

The red activities are our main critical activities. They cannot overlap each other, and they should always be executed in this order. Red activities describe Tier1 activities.

The light-blue activities are also main activities, but they can overlap other main activities. They can be executed in parallel with other activities. Light-blue activities describe Tier2 activities.

The green activities are the sub activities for the main activities (the red activities). The green activities give a general overview on the type of actions and sub activities that are executed during its main activity.

First, we have Requirements Planning which takes a total of 141 hours, and that is the first phase of the project. 

After that, we have the Requirements document verification and validation, which is just inspecting the Requirements document in order to find any errors so we could fix them before moving to the next stages. This phase takes a total of 16 hours.

In parallel to the Requirements Planning activity, we have the Test plan activity which takes 16 hours. It is made such that it starts with the Requirements Planning and ends a bit before the end of Requirements Planning. This is made so that we could design the best Test plan for our model according to the Requirements document. 

Then we have the design document, which is an activity running in parallel with the Requirements document verification and validation that starts right after the end of Requirements Planning activity. It took 75 hours. 

And as always we have the design document verification and validation which is only an inspection of the design document because we need to catch errors before continuing our project in order to minimize future costs. This phase takes a total of 16 hours. 

Then we have the coding, which is an activity running in parallel with the design document verification and validation and starts right after the end of the Design document because the code is implementing the design mentioned in the Design document. It took 168 hours, because in this activity we are implementing our software.

When the coding starts, we have Unit test activity which is running in parallel with coding. Unit test takes parts of the code individually, and tests them. We need to test the whole code, that's why Unit test activity ends with the coding activity. Unit test takes 80 hours.

And, running in parallel to the coding, we have the documentation which takes 10 hours. The interval of time of the documentation activity is made such that the documentation starts with the coding activity and ends way before the end of the coding activity, because during the first stages of our coding, we are defining functions, classes, variables and getting a clear idea of the structure of our software, so we immediately document them in the documentation.

Shortly after the start of the Unit test activity, we have Integration testing which is run in parallel with unit test because it uses the results of the unit test activity, and ends shortly after the end of unit test activity. Integration testing takes 52 hours.

At the end we have system test which is 54 hours. It starts right after Integration testing because system test is going to be our final testing activity and it uses the results from integration testing and unit testing.

