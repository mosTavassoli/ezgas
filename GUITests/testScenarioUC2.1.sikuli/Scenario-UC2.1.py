Settings.MinSimilarity=0.95
click("1590947081946.png")
type("1590945261221.png","admin@ezgas.com")
type("1590945277123.png","admin")
click("1590945284513.png")
click("1590945318464.png")
wait(1)


find("1590938938715.png")

region = exists(Pattern("1590948216720.png").similar(0.90),1)
if region:
    click("1590938970929.png")
    click("1590939708048.png")
    type("a", KeyModifier.CTRL) 
    type(Key.BACKSPACE)
    find("1590939796636.png")
    type("1590939796636.png", "updateName")

    click("1590949312984.png")
    type("a", KeyModifier.CTRL) 
    type(Key.BACKSPACE)
    find("1590949334360.png")
    type("1590949344161.png", "123456")
    
    find("1590939921944.png")
    click("1590939972671.png")
    type("a", KeyModifier.CTRL) 
    type(Key.BACKSPACE)
    find("1590939991056.png")
    type("1590939991056.png", "updateName@email.com")
    click("1590949453839.png")
    popup("Test Success!","test")
else:
    popup("user not found!","test")

