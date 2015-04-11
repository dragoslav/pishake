# PiShake

**PiShake** is Scala AKKA project to access the I/O capabilities of the Raspberry Pi. 
The project is in its early stage and only limited I/O capabilities are provided.

* [Pi4J](http://pi4j.com) wrapper (basic I/O)
* [1-Wire](http://en.wikipedia.org/wiki/1-Wire) support, additional resources for 1-Wire on Raspberry Pi: [1](http://www.modmypi.com/blog/loading-i2c-spi-and-1-wire-drivers-on-the-raspberry-pi-under-raspbian-wheezy) & [2](http://www.raspberrypi-spy.co.uk/2013/03/raspberry-pi-1-wire-digital-thermometer-sensor/)

AKKA remoting allows running PiShake on the Raspberry Pi as a peer-to-peer server.
Client app (e.g. during development) can control Raspberry Pi I/O remotely by establishing standard AKKA remote connection.

