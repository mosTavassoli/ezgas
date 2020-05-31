click("1590924630591.png")
click("1590924660206.png")
type("1590924691142.png","testname")
type("1590925091014.png","user@ezgas.com")
type("1590925128393.png","testpassword")
click("1590925180975.png")
type("1590925956097.png","user@ezgas.com")
type("1590926028585.png","testpassword")
click("1590926056531.png")
if exists("1590926164177.png",0.5):
    popup("Success!","Test")
else:
    popup("Failure!","Test")
