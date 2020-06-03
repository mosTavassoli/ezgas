click("1590934489435.png")
type("1590934513525.png", "admin@ezgas.com")
type("1590934580147.png", "admin")
click("1590934624123.png")
click("1590934666071.png")
if exists("1590937386783.png"):
    click("1590937423492.png")
    if not exists("1590937386783.png"):
        popup("Success!", "Test")
    else:
        popup("Failure!", "Test")
else:
    popup("Failure!", "Test")