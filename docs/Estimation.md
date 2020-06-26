# Project Estimation  

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli 

Date: 03/05/2020

Version: 2

|Version|Changes|
|----|----|
|2| Updated estimation document with actual productivity observed during the project|

# Contents
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition](#estimate-by-activity-decomposition)




# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   |    19                         |             
|  A = Estimated average size per class, in LOC       |              200              | 
| S = Estimated size of project, in LOC (= NC * A) | 3800 |
| E = Estimated effort, in person hours (here use productivity 7 LOC per person hour)  |  543                                    |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 16290 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) |        3.4            |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
|**Requirements Planning** | |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Perform workflow analysis | 8|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Model process | 4|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Identify user requirements | 24|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Identify performance requirements | 3|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Identify interface requirements |6|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Design GUI | 32|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prepare software requirements specification | 26|
|**Requirements V&V**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Requirements inspection | 16|
|**Design document**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Analysis | 24|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Formalization| 16|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Verification | 8|
|**Design document V&V**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Design document inspection |10|
|**Coding**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Implement the code| 168|
|**Documentation**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Explain design document and code | 10|
|**System test**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test all units of application| 40|
|**Test plan**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prepare testing process | 16|
|**Unit test**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test each unit of the software| 80 |
|**Integration test**| |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Combine individual units and test them as a group| 52 |




### Gantt chart
```plantuml

printscale daily
Project starts the 6th of april 2020
[Requirements Planning] as [TASK1] lasts 9 days
[TASK1] is colored in Red

[Perform workflow analysis] lasts 1 day


[Model process] lasts 1 days
[Perform workflow analysis]->[Model process]

[Identify user requirements] lasts 1 days
[Model process]->[Identify user requirements]

[Identify performance requirements] lasts 1 days
[Identify user requirements]->[Identify performance requirements]

[Identify interface requirements] lasts 1 days
[Identify performance requirements]->[Identify interface requirements]

[Design GUI] lasts 2 days
[Identify interface requirements]->[Design GUI]

[Prepare software requirements specification] lasts 2 days
[Design GUI]->[Prepare software requirements specification]





[Requirements V&V] as [TASK1.1] lasts 1 days
[TASK1.1] is colored in LightBlue
[TASK1.1] starts at [TASK1]'s end

[Requirements inspection] lasts 1 days
[TASK1]->[Requirements inspection]








[Design document] as [TASK2] lasts 3 days
[TASK2] is colored in Red
[TASK2] starts at [TASK1]'s end

[Analysis] lasts 1 days
[TASK1]->[Analysis]

[Formalization] lasts 1 days
[Analysis]->[Formalization]

[Verification] lasts 1 days
[Formalization]->[Verification]

[Design document V&V] as [TASK2.1] lasts 1 days
[TASK2.1] is colored in LightBlue
[TASK2.1] starts at [TASK2]'s end

[Design document inspection] lasts 1 days
[TASK2]->[Design document inspection]



[Coding] as [TASK3] lasts 6 days
[TASK3] is colored in Red
[TASK3] starts at [TASK2.1]'s end

[Implement the code] lasts 6 days
[TASK2.1]->[Implement the code]

[Documentation] as [TASK3.1] lasts 1 days
[TASK3.1] is colored in LightBlue
[TASK3.1] starts at [TASK2.1]'s end

[Explain design document and code] lasts 1 days
[TASK2.1]->[Explain design document and code]



[System test] as [TASK4] lasts 2 days
[TASK4] is colored in Red
[TASK4] starts 2 days after [TASK3]'s end

[Test all units of application] lasts 2 days
[Test all units of application] starts 2 days after [TASK3]'s end



[Test plan] as [TASK1.2] lasts 1 days
[TASK1.2] is colored in LightBlue

[Prepare testing process] lasts 1 days


[Unit test] lasts 4 days
[Unit test] starts 15 days after [TASK1.2]'s end 
[Unit test] is colored in LightBlue

[Test each unit of the software] lasts 4 days
[Test each unit of the software] starts 15 days after [TASK1.2]'s end 

[Integration test] lasts 3 days
[Integration test] starts 2 days after [Unit test]'s start 
[Integration test] is colored in LightBlue
[Combine individual units and test them as a group] lasts 3 days
[Combine individual units and test them as a group] starts 2 days after [Unit test]'s start 


```


This new version of the estimation document was developed by taking advantage of all the experience we acquired during this course.
The difference between what we expected before starting the project and what we would expect now is quite remarkable.
The time required is slightly more than double of what we expected when decomposing the project by product.
The decomposition by activity and the Gantt chart, however, were surprisingly accurate.
This is due to the fact that we decided to keep the two decompositions independent, since the decomposition by product required to use normal working hours, while for the decomposition by activity nothing was specified, so we assumed to use our student free time as working hours.
This time the two decompositions are much easier to match, probably because of the much better product decomposition estimate.
To read the comments on how we derived the activity decomposition estimates, please refer to a previous version of this document.
