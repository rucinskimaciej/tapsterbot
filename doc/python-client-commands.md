# Commands to sue with Python client

**Tap on (x, y)**  
```python
  tap x y
```

**Tap n times on (x, y)**  
```python
  n-tap n x y
```

**Stress the app with n fast taps on (x, y)**  
```python
  stress-tap n x y
```

**Swipe/scroll from (x1, y1) to (x2, y2)**  
```python
  swipe x1 y1 x2 y2
```

**Stress the app with n fast swipes from (x1, y1) to (x2, y2)**  
```python
  stress-swipe n x1 y1 x2 y2
```

**Reset position of the arms**  
```python
  reset
```

**Get angles of the robot's arms**  
```python
  get-angles
```

**Defines angles of the arms to a, b and c**  
```python
  set-angles a b c
```

**Get the position of the robot (i.e. 3D coordinates in use)**  
```python
  get-position
```

**Define the 3D position the robot should set**  
```python
  set-position x y z
```

**Get the configuration of the robot**  
```python
  config
```

**Get the status of the robot**  
```python
  status
```

**Check if the connection to the robot server is made**  
```python
  check
```

**Repeat the last executed command**  
```python
  repeat
```

**Make the robot dance!**  
```python
  dance
```

**Make the robot stop dancing**  
```python
  stop-dance
```

**Get calibration data in use**  
```python
  get-calibration
```

**Set the calibration (JSON-data) the robot must use**  
```python
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

**I wanna help please!**  
```python
  help
```

**Quit the client**  
```python
  bye
```
