Settings.MinSimilarity=0.95
click("1590932210383.png")
type("1590933110126.png","user@ezgas.com")
type("1590933126087.png","user")
click("1590932402399.png")
wait(0.5)
type(Key.PAGE_DOWN)
wait(0.5)
type(Key.PAGE_DOWN)
wait(0.5)
region = exists("1591018929166.png",1)

if region:
    region.click("1591018975310.png")
    wait(0.5)
    if exists("1591019056953.png"):
        popup("Success!","Test")
    else:
        popup("Failure!","Test")
else:
    
    popup("Test gas station not found!","Test")