type(Key.PAGE_DOWN)
find("1590954476109.png")
type("1590953748546.png", "Via Paolo Sarpi Turin Piemont Italy")
wait(1)
click("1590954278286.png")
click("1590954316487.png")
if exists("1590954337558.png"):
    popup("Success!", "Test")
else:
    popup("Failure!", "Test")