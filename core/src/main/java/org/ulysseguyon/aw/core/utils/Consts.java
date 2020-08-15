package org.ulysseguyon.aw.core.utils;

import java.io.File;


public final class Consts {

	public static final String GAME_NAME = "Advance Wars";

	public final static class Window {

		public static final int BASE_W = 1024;
		public static final int BASE_H = 640;
		public static final String ICON_PATH = "resources" + File.separator + "img" + File.separator + "logos"
				+ File.separator + "Logo.png";

	}

	public enum GameState {
		MAIN_MENU, EDITOR, INGAME;

		public int id () {
			return this.ordinal();
		}
	}

}
