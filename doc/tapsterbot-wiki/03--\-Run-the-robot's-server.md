# Commands to use to start the server

## Run the server with the basic configuration (127.0.0.1:4242):
```shell
  node server.js
```

## Run the server with the basic configuration (127.0.0.1:4242) and the calibration data:
```shell
  node server.js --calibration path-to-JSON-file
```

## Run the server with a dedicated IP address (a.b.c.d) and a dedicated port (p):
```shell
  node server.js --address a.b.c.d --port p
```

## The most suitable commands may be:
```shell
  node server.js --address a.b.c.d --port p --calibration path-to-JSON-file
```
or
```shell
  node server.js -a a.b.c.d -p p -c path-to-JSON-file
```
