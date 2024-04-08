SET location=%CD%
SET selenium=%location%\selenium-server-4.1.2.jar
SET chromedriver=%location%\chromedriver.exe

java -Dwebdriver.chrome.driver="%chromedriver%" -jar "%selenium%" node --detect-drivers true


