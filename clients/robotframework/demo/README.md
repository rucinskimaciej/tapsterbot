# demo folder

Here are files to use for demos and tests.
The following commands can be used in order to make demos;
They call keywords and configuration variables defined in the other files.

# Some commands

- Run all of test cases demo
```shell
    robot tapster_demo.robot
```

- Check if your test cases are reachable
```shell
    robot -t "Hey" tapster_demo.robot
```

- Check if sessions can be made with the robot
```shell
    robot -t "Session" tapster_demo.robot
```

- Check the connection of the robot
```shell
    robot -t "Test the connection of the robot" tapster_demo.robot
```

- Get the angles of the arms
```shell
    robot -t "Get angles of arms" tapster_demo.robot
```

- Set the angles of the arms
```shell
    robot -t "Set angles of arms" tapster_demo.robot
```

- Get the 3D position of the robot
```shell
  robot -t "Get position in 3D" tapster_demo.robot
```

- Set the 3D position of the robot
```shell
  robot -t "Set position in 3D" tapster_demo.robot
```

- Reset the robot
```shell
  robot -t "Reset robot" tapster_demo.robot
```

- Make the robot tap to a point
```shell
  robot -t "Tap to point" tapster_demo.robot
```

- Make the robot tap to a point with a bigger duration than a normal tap
```shell
  robot -t "Long tap to point" tapster_demo.robot
```

- Get the calibration data
```shell
  robot -t "Get calibration" tapster_demo.robot
```

- Get the status of the robot's server
```shell
  robot -t "Get status" tapster_demo.robot
```

- Dance or not!
```shell
  robot -t "Dance" tapster_demo.robot
  robot -r "Stop dancing" tapster_demo.robot
```

- Makes swipes
```shell
  robot -t "Swipes" tapster_demo.robot
```

- Tap n times on a point
```shell
  robot -t "N taps" tapster_demo.robot
```

- Position for a 2D point on screen
```shell
  robot -t "Pos for screen" tapster_demo.robot
```

- Angles for a 3D point
```shell
  robot -t "Ang for pos" tapster_demo.robot
```

- Contact Z-axis value
```shell
  robot -t "Z contact" tapster_demo.robot
```

- Stress taps the app
```shell
  robot -t "Stress taps" tapster_demo.robot
```

- Stress swipes the app
```shell
  robot -t "Stress swipes" tapster_demo.robot
```

- Swipe n times
```shell
  robot -t "N swipes" tapster_demo.robot
```
