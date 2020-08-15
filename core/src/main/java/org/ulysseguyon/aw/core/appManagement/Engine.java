package org.ulysseguyon.aw.core.appManagement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.ulysseguyon.aw.core.gameStates.Editor;
import org.ulysseguyon.aw.core.gameStates.MainMenu;


public class Engine extends StateBasedGame {

	public Engine ( String name ) {
		super( name );
	}

	@Override
	public void initStatesList ( GameContainer container ) throws SlickException {
		this.addState( new MainMenu() );
		this.addState( new Editor() );
		// TODO add the other game states

		this.enterState( MainMenu.ID );
	}

}
