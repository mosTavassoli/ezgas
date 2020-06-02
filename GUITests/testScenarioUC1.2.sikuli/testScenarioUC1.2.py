click("1590934489435.png")
type("1590934513525.png", "admin@ezgas.com")
type("1590934580147.png", "admin")
click("1590934624123.png")
click("1590934666071.png")
type("1590924818039.png", "user")
type("1590924846906.png", "password")
type("1590924871311.png", "user@example.com")
click("1590928067444.png")
if exists("1590934869891.png"):
    popup("Success!", "Test")
else:
    popup("Failure!", "Test")