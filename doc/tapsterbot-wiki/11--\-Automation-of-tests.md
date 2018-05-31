## Automation of tests

So as to improve quality of sources or products, you may apply principles of Test-Driven-Development (TDD), Behaviour-Driven-Development (BDD) or DevOps with continous integration (#buzzwords ^_^)

You may use _Robot Framework_, a powerful tool which provides API to describe test cases.  
_Robot Framework_ works well with other tools like _Appium_ if you want to process tests in mobile or web apps.  

It is also possible to use the Tapster robot in such tests: you just have to include the wrapper: it provides _Robot Framework_ keywords which gather Tapster-dedicated and Appium-dedicated keywords. It will avoid you to know on which (X,Y) coordinates you have to tap by allowing you to tap on "this button" or "the widget with that id".