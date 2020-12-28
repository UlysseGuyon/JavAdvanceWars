package org.ulysseguyon.aw.core.utils;

public final class CoreConsts {

	public static final String GAME_NAME = "Advance Wars";

	public final static class Window {

		public static final int	BASE_W	= 1024;
		public static final int	BASE_H	= 640;

	}

	public enum GameState {
		MAIN_MENU,
		EDITOR,
		INGAME;

		public int id () { return this.ordinal(); }
	}

}
