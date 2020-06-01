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
type("1591016314353.png","Via Roma")
wait(1)
click("1591016634785.png")
click("1591016854447.png")
click("1591016909973.png")
click("1591017280258.png")
wait(1)
if exists("1591017182446.png",1) and not exists("1591018282585.png",1):
    click("1591017959674.png")
    click("1591018044744.png")
    click("1591017280258.png")
    wait(1)
    if exists("1591018247256.png",1) and not exists("1591017182446.png",1):
        popup("Success!","Test")
    else:
        popup("Failure!","Test")    
else:
    popup("Failure!","Test")