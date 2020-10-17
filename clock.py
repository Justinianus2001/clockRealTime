#!/usr/bin/env python3
import os

try:
	from py4j.java_gateway import JavaGateway, Py4JNetworkError
except:
	os.system('start cmd /c pip3 install py4j')
	from py4j.java_gateway import JavaGateway, Py4JNetworkError

if __name__ == '__main__':
	try:
		gateway = JavaGateway.launch_gateway(classpath = 'clock.jar')
		main = gateway.jvm.controller.Main()
		print('JVM accepting connection')
		main.run()
	except Py4JNetworkError:
		print('No JVM listening')
	except:
		print('Another error... maybe with the JVM')