Settings.MinSimilarity=0.95
click("1590932210383.png")
type("1590933110126.png","admin@ezgas.com")
type("1590933126087.png","admin")
click("1590932402399.png")
click("1590932646225.png")
wait(1)
type(Key.PAGE_DOWN)
type(Key.PAGE_DOWN)
region = exists("1590941356239.png",1)

if region:
    region.click("1590932990760.png")
    wait(0.5)
    if exists("1590941356239.png"):
        popup("Failure!","Test")
    else:
        popup("Success!","Test")
else:
    
    popup("Test gas station not found!","Test")