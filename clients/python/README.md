# python folder

Here are elements defining a client written in Python which can drive the bot (i.e. send to it requests)

## List of files
- _client.py_ The Python-written client to use so as to drive the robot, i.e. the "main" program
- _config.py_ The file having the configuration to use with for examples regular expresions, API's URL, etc.
- _glue.py_ Some Python glue so as to give logic to client and make the link with the _robot_api.py_ file
- _robot_api.py_ The file which uses the HTTP API of the robot
- _commands_parser.py_ The file providing methods to parse and check commands

## Usage
python client.py [-h] [--url ROBORSERVERURL] [--light COMMAND] []--file COMMANDSFILE] [--version]

## How to use the client
1. Define the configuration of your Tapster2 robot in _config.py_ if needed
2. Then...

To run the Python client in interactive / verbose mode, use:
```shell
  python client.py --url your-robot-url
```

To run the Python client in light mode using only a command, use:
```shell
  python client.py --url your-robot-url --light the-command-to-process
```

To run the Python client with a file of commands to process, use:
```shell
  python client.py --url your-robot-url --file path-to-the-command-file
```

If you want to get the version of the Python client, use:
```shell
  python client.py --version
```

## Note
The default value for _--url_ parameter  is a combination of variables defined in _config.py_ like **http://127.0.0.1:4242**.
If you want to use this client, you have to have ['requests'](docs.python-requests.org/en/latest/ "Requests module") module installed.
To do so, you can run the following command or read this [topic](https://stackoverflow.com/questions/17309288/importerror-no-module-named-requests "Stack Overflox post"):

```shell
  sudo pip install requests
```
