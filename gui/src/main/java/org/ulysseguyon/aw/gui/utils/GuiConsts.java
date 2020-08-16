package org.ulysseguyon.aw.gui.utils;

import java.io.File;


public final class GuiConsts {

	public static final class Strings {
		public static final String LOADING = "Loading ...";
	}

	public static final class Paths {

		public static final String	RES_DIR		= "resources";
		public static final String	IMAGE_DIR	= RES_DIR + File.separator + "img";

		public static final class Fonts {

			public static final String	FONTS_DIR			= RES_DIR + File.separator + "fonts";
			public static final String	FONTS_BASE_PATH		= FONTS_DIR + File.separator + "newcomictitle";
			public static final String	FONTS_ITAL_EFFECT	= "ital";
			public static final String	FONTS_BOLD_EFFECT	= "bold";
			public static final String	FONTS_EXT			= ".ttf";

			public static final String	BASE				= FONTS_BASE_PATH + FONTS_EXT;
			public static final String	BASE_ITAL			= FONTS_BASE_PATH + FONTS_ITAL_EFFECT + FONTS_EXT;
			public static final String	BASE_BOLD			= FONTS_BASE_PATH + FONTS_BOLD_EFFECT + FONTS_EXT;
			public static final String	BASE_BOLD_ITAL		= FONTS_BASE_PATH + FONTS_BOLD_EFFECT + FONTS_ITAL_EFFECT
				+ FONTS_EXT;

			public static final String	VOLUME				= FONTS_BASE_PATH + "3d" + FONTS_EXT;
			public static final String	VOLUME_ITAL			= FONTS_BASE_PATH + "3d" + FONTS_ITAL_EFFECT + FONTS_EXT;

			public static final String	LASER				= FONTS_BASE_PATH + "laser" + FONTS_EXT;
			public static final String	LASER_ITAL			= FONTS_BASE_PATH + "laser" + FONTS_ITAL_EFFECT + FONTS_EXT;
		}

		public static final class Backgrounds {

			public static final String	BACKGROUNDS_DIR	= IMAGE_DIR + File.separator + "backgrounds";

			public static final String	MAIN_MENU		= BACKGROUNDS_DIR + File.separator + "Menu_Background.png";
		}

		public static final class Logos {

			public static final String	LOGOS_DIR		= IMAGE_DIR + File.separator + "logos";

			public static final String	GAME_ICON		= LOGOS_DIR + File.separator + "Logo.png";
			public static final String	GAME_BIG_ICON	= LOGOS_DIR + File.separator + "Big_Logo.png";
		}

	}

	public static enum SimpleImage {
		BACK_MAIN_MENU ( Paths.Backgrounds.MAIN_MENU ),
		LOGO_GAME_ICON ( Paths.Logos.GAME_ICON ),
		LOGO_GAME_BIG_ICON ( Paths.Logos.GAME_BIG_ICON );

		private String path;

		SimpleImage ( String path ) { this.path = path; }

		public String getPath () { return path; }
	}

	public static enum Font {

		BASE ( Paths.Fonts.BASE ),
		BASE_ITAL ( Paths.Fonts.BASE_ITAL ),
		TITLE ( Paths.Fonts.BASE_BOLD ),
		TITLE_ITAL ( Paths.Fonts.BASE_BOLD_ITAL ),
		TITLE_ITAL_LASER ( Paths.Fonts.LASER_ITAL ),
		BIG_TITLE ( Paths.Fonts.VOLUME ),
		BIG_TITLE_ITAL ( Paths.Fonts.VOLUME_ITAL );

		private String path;

		Font ( String path ) { this.path = path; }

		public String getPath () { return path; }
	}

}
