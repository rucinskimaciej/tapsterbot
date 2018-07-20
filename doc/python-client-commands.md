# Commands to use with Python client

**Tap on (x, y)**  
```shell
  tap x y
```

**Long tap on (x, y) during duration d**  
```shell
  long-tap x y d
```

**Double tap on (x, y) with duration d between each tap**  
```shell
  double-tap x y d
```

**Triple tap on (x, y) with duration d between each tap**  
```shell
  triple-tap x y d
```

**Tap n times on (x, y)**  
```shell
  n-tap n x y
```

**Stress the app with n fast taps on (x, y)**  
```shell
  stress-tap n x y
```

**Swipe/scroll from (x1, y1) to (x2, y2)**  
```shell
  swipe x1 y1 x2 y2
```

**Swipe n times from (a,b) to (c,d)**  
```shell
  n-swipe n a b c d
```

**Stress the app with n fast swipes from (x1, y1) to (x2, y2)**  
```shell
  stress-swipe n x1 y1 x2 y2
```

**Reset position of the arms**  
```shell
  reset
```

**Get angles of the robot's arms**  
```shell
  get-angles
```

**Defines angles of the arms to a, b and c**  
```shell
  set-angles a b c
```

**Get the position of the robot (i.e. 3D coordinates in use)**  
```shell
  get-position
```

**Define the 3D position the robot should set**  
```shell
  set-position x y z
```

**Get the configuration of the robot**  
```shell
  config
```

**Get the status of the robot**  
```shell
  status
```

**Check if the connection to the robot server is made**  
```shell
  check
```

**Repeat the last executed command**  
```shell
  repeat
```

**Make the robot dance!**  
```shell
  dance
```

**Make the robot stop dancing**  
```python
  stop-dance
```

**Get calibration data in use**  
```shell
  get-calibration
```

**Set the calibration (JSON-data) the robot must use**  
```shell
  set-calibration JSON-data
```

**Get the position of the bot for (x, y) coordinates**  
```python
  posForScreen x y
```

**Get the angles of the bot arms for the (x, y, z) coordinates**  
```python
  angForPos x y z
```

**Get the Z-axis valiue where the contact between the bot's finger and the device's screen is amde**  
```python
  contactZ
```

**Draw a square with a length l, and draw each n point**
```python
  square n l
```

**Draw a triangle using 3 points (x1, y1) (x2, y2) (x3, y3)**
```python
  triangle x1 y1 x2 y2 x3 y3
```

**Draw a circle centered on (x,y) with a radius r**
```python
  circle x y r
```

**Draw a cross using 4 points, drawing two strokes, with strokes (x1, y1) -> (x4, y4) and (x2, y2) -> (x3, y3)**
```python
  cross x1, y1 x2 y2 x3 y3 x4 y4.
```

** Draw a spiral centered on (x,y) using n rings with an r radius**
```python
  spiral x y n r
```

**Draw a random pattern with (n-1) strokes in a defined area (points picked with [minW ; maxW] and [minH; maxH])**
```python
  random n minW minH maxW mawH
```

**I wanna help please!**  
```python
  help
```

**Quit the client**  
```python
  bye
```
