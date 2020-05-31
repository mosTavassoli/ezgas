Settings.MinSimilarity=0.95
click("1590947081946.png")
type("1590945261221.png","admin@ezgas.com")
type("1590945277123.png","admin")
click("1590945284513.png")
click("1590945318464.png")
wait(1)

type(Key.PAGE_DOWN)

find("1590938597038.png")

region = exists("1590947451211.png",1)
if region:
    region.click("1590934672605.png")
    wheel(WHEEL_UP,3)

    find("1590935863576.png")
    click("1590938781636.png")
    type("a", KeyModifier.CTRL) 
    type(Key.BACKSPACE) 
    type("1590936079932.png", "newName")


    find("1590936274920.png").click()
    type("a", KeyModifier.CTRL)
    type(Key.BACKSPACE) 
    type("1590936294491.png", "Via Roma Turin Piemont Italy")
    find("1590936894715.png").click()
    click("1590937113176.png")
    popup("Test Success!","test")
else:
    popup("gas station not found!","test")

















