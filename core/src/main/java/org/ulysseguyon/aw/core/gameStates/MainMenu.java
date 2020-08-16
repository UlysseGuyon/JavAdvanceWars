package org.ulysseguyon.aw.core.gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.ulysseguyon.aw.core.utils.CoreConsts;
import org.ulysseguyon.aw.gui.utils.GuiConsts;
import org.ulysseguyon.aw.gui.utils.SmoothAnimation;


public class MainMenu extends BasicGameState {

	public static final int ID = CoreConsts.GameState.MAIN_MENU.id();

	private SmoothAnimation background;

	@Override
	public void init ( GameContainer container, StateBasedGame game ) throws SlickException {
		background = new SmoothAnimation( new Image( GuiConsts.Paths.Backgrounds.MAIN_MENU ), -1, -1, 1 );
	}

	@Override
	public void render ( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException {
		background.draw( 0, 0, container.getWidth(), container.getHeight() );
	}

	@Override
	public void update ( GameContainer container, StateBasedGame game, int delta ) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID () {
		return MainMenu.ID;
	}

}
