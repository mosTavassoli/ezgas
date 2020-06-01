click("1590934489435.png")
type("1590934513525.png", "admin@ezgas.com")
type("1590934580147.png", "admin")
click("1590934624123.png")
type(Key.PAGE_DOWN)
if exists("1590938934908.png"):
    click("1590938332277.png")
    type(Key.PAGE_DOWN)
    type("1590938468280.png", "1.09")
    type("1590938493774.png", "2.80")
    click("1590938533739.png")
    type(Key.F5)
    type(Key.PAGE_DOWN)
    if exists("1590938796052.png"):
        popup("Success!", "Test")
    else:
        popup("Failure!", "Test")
else:
    popup("Failure!", "Test")