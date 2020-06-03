type(Key.PAGE_DOWN)
type("1590939860213.png", "Corso Inghilterra Turin Piemont Italy")
click("dropdown.png")
click("1590940371904.png")
click("1590940444995.png")
if exists("1590940388875.png"):
    popup("Success!", "Test")
else:
    popup("Failure!", "Test")