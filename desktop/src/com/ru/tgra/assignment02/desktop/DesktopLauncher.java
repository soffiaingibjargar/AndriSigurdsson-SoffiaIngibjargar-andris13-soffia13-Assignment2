package com.ru.tgra.assignment02.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ru.tgra.assignment02.Assignment02Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Andri Sigurdsson - Soffia Ingibjargar - Assignment02"; // or whatever you like
		config.width = 1024;  //experiment with
		config.height = 768;  //the window size
		config.x = 150;
		config.y = 250;

		new LwjglApplication(new Assignment02Game(), config);
	}
}
