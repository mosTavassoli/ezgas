Settings.MinSimilarity=0.95
click("1590932210383.png")
type("1590933110126.png","admin@ezgas.com")
type("1590933126087.png","admin")
click("1590932402399.png")
click("1590932646225.png")
region = exists("1590933801783.png",1)
if region:
    region.click("1590932990760.png")
    wait(0.5)
    if exists("1590933801783.png"):
        popup("Failure!","Test")
    else:
        popup("Success!","Test")
else:
    
    popup("Test user not found!","Test")

