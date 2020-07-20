click("1593170862186.png")
type("1593170925120.png", "user@ezgas.com")
type("1593170951479.png", "password")
click("1593170966708.png")
wait(1)
type(Key.PAGE_DOWN)
type(Key.PAGE_DOWN)
if exists("1593174631646.png"):
    click("1593174670545.png")
    type(Key.PAGE_DOWN)
    type("1593174731910.png", "1.29")
    type("1593174718745.png", "3.44")
    click("1593174741572.png")
    type(Key.F5)
    wait(1)
    type(Key.PAGE_DOWN)
    if exists("1593174825327.png"):
        popup("Success!", "Test")
    else:
        popup("Failure!", "Test")
else:
    popup("Failure!", "Test")