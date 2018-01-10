# client folder

Here are elements defining a client written in Python which can drive the bot (i.e. send to it requests)
**Work in progres...**

## List of files
- _client.py_ The Python-written client  to use so as to drive the robot
- _cofig.py_ The file having the configuraiton to use with indeed the IP address, the PORT and the URL of the robot
- _glue.py_ Some Python glue so as to give logic to client and make the link with the _robot_api.py_ file
- _robot_api.py_ The file which uses the API of the robot

## How to use the client
1. Define the configuration of your Tapster2 robot in _config.py_
2. Run
```python
  python client.py
```
