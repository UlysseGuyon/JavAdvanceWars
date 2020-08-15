package org.ulysseguyon.aw.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.ulysseguyon.aw.core.appManagement.Engine;
import org.ulysseguyon.aw.core.utils.Consts;


public class App {

	public static void main ( String[] args ) {
		try {
			AppGameContainer app = new AppGameContainer( new Engine( Consts.GAME_NAME ) );
			app.setDisplayMode( Consts.Window.BASE_W, Consts.Window.BASE_H, false );
			app.setIcon( Consts.Window.ICON_PATH );
			app.start();
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}
}
