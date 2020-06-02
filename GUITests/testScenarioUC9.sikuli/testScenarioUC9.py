Settings.MinSimilarity=0.95
click("1590947081946.png")
type("1590945261221.png","admin@ezgas.com")
type("1590945277123.png","admin")
click("1590945284513.png")
click("1590945318464.png")
wait(1)

wheel(WHEEL_DOWN,2)

find("1591034469126.png")
type("1591034515301.png", "Via Paolo Sarpi" )
type("1591034555021.png", "Via Paolo Sarpi Turin Piemont Italy ")
type(Key.DOWN)
click("1591036150719.png")
click ("1591034596077.png")
wait(1)
click("1591034670667.png")

find("1591034713684.png").click() 
find("1591034730042.png").click()     
click("1591034773103.png")

type(Key.UP)
click("1591035677707.png")
wheel(WHEEL_DOWN,2)
type("1591030668030.png","Via Paolo Sarpi Turin Piemont Italy")
wait(1)
click("1591036165379.png")
click("1591030695453.png")
wait(2)
click("1591030721370.png")
wait(2)
for i in range(20):
    type(Key.DOWN)
wait(1)
type("1591036567945.png","1.6")
wait(1)
type("1591030791360.png","1.3")
wait(1)
click("1591030834008.png")
# check the report
click("1591030852849.png")
wait(2)
find("1591036601085.png")
popup("Success!", "Test")
















