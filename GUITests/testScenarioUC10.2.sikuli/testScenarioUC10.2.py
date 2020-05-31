click("1590934489435.png")
type("1590934513525.png", "admin@ezgas.com")
type("1590934580147.png", "admin")
click("1590934624123.png")
wait("1590940924748.png", 1)
type(Key.PAGE_DOWN)
if exists("1590940676536.png"):
    click("1590940705498.png")
    if exists("1590940760964.png"):
        popup("Success!", "Test")
    else:
        popup("Failure!", "Test")
else:
    popup("Failure!", "Test")