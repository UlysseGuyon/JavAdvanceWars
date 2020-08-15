package org.ulysseguyon.aw.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.ulysseguyon.aw.core.appManagement.Engine;
import org.ulysseguyon.aw.core.utils.Consts;


public class App {

	public static void main ( String[] args ) {
		// TODO arrange dll loading for the jar to work
		// try {
		// Class< ? > nativeLoaderClass = Class.forName( "NativeLoader" );
		// Method nativeLoaderMethod = nativeLoaderClass.getMethod( "loadNatives" );
		// nativeLoaderMethod.invoke( null );
		// } catch ( UnsatisfiedLinkError | ClassNotFoundException |
		// NoSuchMethodException | SecurityException
		// | IllegalAccessException | IllegalArgumentException |
		// InvocationTargetException e ) {
		// e.printStackTrace();
		// System.exit( 1 );
		// }

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
