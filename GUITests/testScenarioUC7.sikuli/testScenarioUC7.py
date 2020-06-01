
click("1591030185750.png")
wait(1)
find("1591030219072.png")
type("1591030410711.png","updateName@email.com")
wait(1)
type("1591030527418.png","123456")
wait(1)
click("1591030571982.png")
wait(2)

# scroll down
for i in range(12):
    type(Key.DOWN)
    
# search for a gas station
type("1591030668030.png","Via Paolo Sarpi Turin Piemont Italy")
wait(1)
click("1591031151477.png")
click("1591030695453.png")
wait(2)

# add report 
click("1591030721370.png")
wait(2)
for i in range(20):
    type(Key.DOWN)
wait(1)
type("1591030765816.png","1.6")
wait(1)
type("1591030791360.png","1.3")
wait(1)
click("1591030834008.png")

# check the report
wait(3)
click("1591030852849.png")
wait(2)
find("1591030870928.png")
popup("Success!", "Test")