package com.github.jakaarl.mands.server

import io.javalin.Javalin

class Server constructor(val port: Int){

	fun start() {
		Javalin.create().start(port);
	}
	
}