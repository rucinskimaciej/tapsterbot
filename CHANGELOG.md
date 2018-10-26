# Changelog


## Version "eve"

* In the Node.js backend (HTTP REST API), new features are provided:
	* long tap on coordinates
	* double tap on coordinates
	* triple tap on coordinates
	* draw a square with a dedicated length
	* draw a star
	* draw a triangle
	* draw a circle
	* draw a cross
	* draw a spiral
	* draw an SVG picture based on its XML definition
	* draw a random pattern (strokes-based)
	* swipe n times
	* tap n times
	* tap on n random points

* In Robot Framework side
	* long tap on coordinates
	* double tap on coordinates
	* triple tap on coordinates
	* draw a random pattern
	* draw a star
	* draw a circle
	* draw a cross
	* draw a square
	* draw a triangle
	* draw a spiral

* In Robot Framework wrapper side	
	* swipe from an element to another using ids, texts or xpath (wrapper)
	* long tap to element using XPath
	* double tap to elements using XPath, ID or Text
	* triple tap to elements using XPath, ID or Text
	* draw a random pattern
	* draw a star
	* draw a circle
	* draw a cross
	* draw a square
	* draw a triangle
	* draw a spiral

* Snips assistants
	* french - add intents to draw square, star, triangle, random pattern, circle and spiral, and for tap n times
	* english - add intents to draw square, star, triangle, random pattern and circle, and for tap n times

* Python client
	* long tap
	* double tap
	* triple tap
	* draw a square
	* draw a star
	* draw a triangle
	* draw a circle
	* draw a cross
	* draw a spiral
	* draw a random pattern

* Android app
	* refactor Android Studio project so as to produce 2 apps: without (light) and with Snips (full)
	* refactor GUI elements (settings, action buttons and cards, tabs, tap targets) and crash reports feature
	* draw a circle
	* draw a star
	* draw a spiral
	* draw a square
	* draw a cross
	* draw a triangle
	* draw a random pattern
	* tap n times
	* swipe n times
	* update assistant capabilities
	* clean sources

* Web app
	* refactor GUI elements (colors, elevations, radius, action buttons)
	* make GUI more responsive and improve a11y
	* trim parameters of commands
	* long tap
	* draw a star
	* draw a circle
	* draw a spiral
	* draw a square

* Documentation
	* more details in CURL samples
	* more details in Markdown file about robot API
	* more details in Robot Framework keywords and wrapper

* Other things
	* Clean up project by removing useless files

## Version eve-1

* Improve documentation and wiki with more details and less typo mistakes

## Version eve-2

* Calibration
	* Add missing file for calibration workflow
	* Fix path of capabilities files inside calibration script

* Robot Framework
	* Refactor syntax of wrapper library to match CamelCase

* Other things
	* Update licences headers so as to have a more suitable email address

