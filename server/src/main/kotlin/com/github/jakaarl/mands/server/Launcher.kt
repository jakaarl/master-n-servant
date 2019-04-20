package com.github.jakaarl.mands.server

import kotlin.jvm.JvmStatic

class Launcher {
	
	companion object {
		val PORT_ENV_VARIABLE = "PORT";
		val DEFAULT_PORT = "0"; // randomly assigned
		
		@JvmStatic fun main(args: Array<String>) {
			val port = Integer.parseInt(System.getProperty(PORT_ENV_VARIABLE, DEFAULT_PORT));
			val server = Server(port);
			server.start();
		}
	}
	
}