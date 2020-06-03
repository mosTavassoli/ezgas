click("1590936732940.png")
while(1):
    type(Key.PAGE_DOWN) 
    if exists("1590935463805.png"):
        break;
type("1590935479504.png", "mrouj")
click("1590935585144.png")
click("1591088709368.png")
click("1590935651914.png")
click("1590935754198.png")
i = 0
while(1):
    i = i+1
    type(Key.PAGE_DOWN) 
    if exists("1590937442886.png"):
        popup("Success!", "Test")
        break;
    if (i>5):
        popup("Failure!", "Test")
        break;
