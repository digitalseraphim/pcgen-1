/*
 * Copyright 2008 Connor Petty <cpmeister@users.sourceforge.net>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package pcgen.javafx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionUtils;
import pcgen.facade.core.CharacterFacade;
import pcgen.facade.core.SourceSelectionFacade;
import pcgen.facade.core.TempBonusFacade;
import pcgen.facade.util.DefaultListFacade;
import pcgen.facade.util.ListFacade;
import pcgen.facade.util.ReferenceFacade;
import pcgen.facade.util.SortedListFacade;
import pcgen.facade.util.event.ReferenceEvent;
import pcgen.facade.util.event.ReferenceListener;
import pcgen.javafx.util.AbstractRadioListMenu;
import pcgen.javafx.tools.CharacterSelectionListener;
import pcgen.javafx.util.AbstractListMenu;
import pcgen.system.CharacterManager;
import pcgen.system.FacadeFactory;
import pcgen.system.LanguageBundle;
import pcgen.util.Comparators;
import pcgen.util.Logging;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;


/**
 * The menu bar that is displayed in PCGen's main window.
 */
public final class PCGenMenuBar extends MenuBar implements CharacterSelectionListener {

    /**
     * The context indicating what items are currently loaded/being processed in the UI
     */
    private final UIContext uiContext;
    private final PCGenFxRoot root;
    private CharacterFacade character;
    private final TempBonusMenu tempMenu;

    private static MenuItem createMenuItem(final String actionStr) {
        return ActionUtils.createMenuItem(ActionMap.action(actionStr));
    }

    public PCGenMenuBar(PCGenFxRoot root, UIContext uiContext) {
        this.uiContext = Objects.requireNonNull(uiContext);
        this.root = root;
		this.tempMenu = new TempBonusMenu();
        initComponents();
    }
//
//	private void initComponents()
//	{
//		Menu m = new Menu("File");//$NON-NLS-1
//		PCGenActions.registerActions(root, uiContext);
//		PCGenActions actions = new PCGenActions(null, uiContext);
//		m.getItems().add(ActionUtils.createMenuItem(ActionMap.action("testAction")));//$NON-NLS-1$
////		m.getItems().add(ActionUtils.createMenuItem()
//		this.getMenus().add(m);
//	}

//	@Override
//	public void setCharacter(CharacterFacade character) {
//
//	}


    private void initComponents() {
        PCGenActions.registerActions(root, uiContext);
        this.getMenus().add(new FileMenu());
        this.getMenus().add(createEditMenu());
//        this.getMenus().add(createSourcesMenu());
        this.getMenus().add(createToolsMenu());
        this.getMenus().add(createHelpMenu());
    }

    private static class CopyAction extends Action implements Consumer<ActionEvent> {

        CopyAction() {
            super("%%copy");
        }

        @Override
        public void accept(ActionEvent actionEvent) {
            System.out.println("Copy Action Here");
        }
    }

    private static class CutAction extends Action implements Consumer<ActionEvent> {

        CutAction() {
            super("%%cut");
        }

        @Override
        public void accept(ActionEvent actionEvent) {
            System.out.println("Cut Action Here");
        }
    }

    private static class PasteAction extends Action implements Consumer<ActionEvent> {

        PasteAction() {
            super("%%paste");
        }

        @Override
        public void accept(ActionEvent actionEvent) {
            System.out.println("Paste Action Here");
        }
    }


    private Menu createEditMenu() {
        Menu menu = new Menu();
        menu.setText(LanguageBundle.getString("in_mnuEdit"));
//		menu.setMnemonic(KeyEvent.VK_E);
        menu.getItems().add(createMenuItem(PCGenActions.ADD_KIT_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(tempMenu);
        menu.getItems().add(new SeparatorMenuItem());

        menu.getItems().add(ActionUtils.createMenuItem(new CutAction()));
        menu.getItems().add(ActionUtils.createMenuItem(new CopyAction()));
        menu.getItems().add(ActionUtils.createMenuItem(new PasteAction()));

        return menu;
    }

    private Menu createSourcesMenu() {
        Menu menu = new Menu(LanguageBundle.getString("in_mnuSources"));
//		menu.setToolTipText(LanguageBundle.getString("in_mnuSourcesTip"));

        menu.getItems().add(createMenuItem(PCGenActions.SOURCES_LOAD_SELECT_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new QuickSourceMenu());
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(createMenuItem(PCGenActions.SOURCES_RELOAD_COMMAND));
        menu.getItems().add(createMenuItem(PCGenActions.SOURCES_UNLOAD_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(createMenuItem(PCGenActions.INSTALL_DATA_COMMAND));

        return menu;
    }

    private Menu createToolsMenu() {
        Menu menu = new Menu(LanguageBundle.getString("in_mnuTools"));
        menu.getItems().add(createMenuItem(PCGenActions.PREFERENCES_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(createMenuItem(PCGenActions.LOG_COMMAND));
        menu.getItems().add(new LoggingLevelMenu());
        menu.getItems().add(createMenuItem(PCGenActions.CALCULATOR_COMMAND));
        menu.getItems().add(createMenuItem(PCGenActions.COREVIEW_COMMAND));
        menu.getItems().add(createMenuItem(PCGenActions.SOLVERVIEW_COMMAND));
        return menu;
    }

    private Menu createHelpMenu() {
        Menu menu = new Menu(LanguageBundle.getString("in_mnuHelp"));
        menu.getItems().add(createMenuItem(PCGenActions.HELP_DOCS_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(createMenuItem(PCGenActions.HELP_OGL_COMMAND));
        menu.getItems().add(createMenuItem(PCGenActions.HELP_TIPOFTHEDAY_COMMAND));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(createMenuItem(PCGenActions.HELP_ABOUT_COMMAND));
        return menu;
    }

    @Override
    public void setCharacter(CharacterFacade character) {
        this.character = character;
//        tempMenu.setListModel(character.getAvailableTempBonuses());
    }

    private static abstract class AbstractFileListMenu extends AbstractListMenu<File> implements EventHandler<ActionEvent> {

        protected AbstractFileListMenu(Action action) {
            super(action);
        }

        protected AbstractFileListMenu(String title) {
            super(title);
        }

        @Override
        protected MenuItem createMenuItem(File item, int index) {
            MenuItem menuItem = new MenuItem();
            menuItem.setText((index + 1) + " " + item.getName()); //$NON-NLS-1$
//			menuItem.setToolTipText(
//				LanguageBundle.getFormattedString("in_OpenRecentCharTip", item.getAbsolutePath())); //$NON-NLS-1$
//			menuItem.setActionCommand(item.getAbsolutePath());
//			menuItem.setMnemonic(String.valueOf(index + 1).charAt(0));
//			menuItem.addActionListener(this);
            menuItem.setUserData(item.getAbsolutePath());
            menuItem.addEventHandler(ActionEvent.ACTION, this);
            return menuItem;
        }

        @Override
        public void handle(ActionEvent event) {
            loadFile(new File((String) ((MenuItem) event.getTarget()).getUserData()));
        }

        public abstract void loadFile(File f);
    }


    private class FileMenu extends AbstractFileListMenu {

        public FileMenu() {
            super("in_" + PCGenActions.MNU_FILE);
            add(PCGenMenuBar.createMenuItem(PCGenActions.NEW_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.OPEN_COMMAND));
            addSeparator();
            add(PCGenMenuBar.createMenuItem(PCGenActions.CLOSE_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.CLOSEALL_COMMAND));
            addSeparator();

            add(PCGenMenuBar.createMenuItem(PCGenActions.SAVE_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.SAVEAS_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.SAVEALL_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.REVERT_COMMAND));
            addSeparator();
            add(new PartyMenu());
            addSeparator();

            add(PCGenMenuBar.createMenuItem(PCGenActions.PRINT_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.EXPORT_COMMAND));
            addSeparator();
            setOffset(16);
            setListModel(CharacterManager.getRecentCharacters());
            addSeparator();

            add(PCGenMenuBar.createMenuItem(PCGenActions.EXIT_COMMAND));
        }

        @Override
        public void loadFile(File f) {
            root.loadCharacterFromFile(f);
        }
    }

    private class PartyMenu extends AbstractFileListMenu {

        public PartyMenu() {
            super(PCGenActions.PARTY_COMMAND);
            add(PCGenMenuBar.createMenuItem(PCGenActions.OPEN_PARTY_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.CLOSE_PARTY_COMMAND));
            addSeparator();

            add(PCGenMenuBar.createMenuItem(PCGenActions.SAVE_PARTY_COMMAND));
            add(PCGenMenuBar.createMenuItem(PCGenActions.SAVEAS_PARTY_COMMAND));
            addSeparator();
            setOffset(6);
            setListModel(CharacterManager.getRecentParties());
        }

        @Override
        public void loadFile(File f) {
            root.loadPartyFromFile(f);
        }
    }

    private final class QuickSourceMenu extends AbstractRadioListMenu<SourceSelectionFacade>
            implements ReferenceListener<SourceSelectionFacade> {

        private QuickSourceMenu() {
            super("in_mnuSourcesLoad");

            ReferenceFacade<SourceSelectionFacade> ref = uiContext.getCurrentSourceSelectionRef();
            setSelectedItem(ref.get());
            ListFacade<SourceSelectionFacade> sources = FacadeFactory.getDisplayedSourceSelections();
            setListModel(new SortedListFacade<>(Comparators.toStringIgnoreCaseCollator(), sources));
            ref.addReferenceListener(this);
        }

        @Override
        public void referenceChanged(ReferenceEvent<SourceSelectionFacade> e) {
            clearSelection();
            setSelectedItem(e.getNewReference());
        }

        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
             if (root.loadSourceSelection((SourceSelectionFacade) newValue.getUserData())) {
                setSelectedItem(uiContext.getCurrentSourceSelectionRef().get());
            }
        }
    }


	private final class TempBonusMenu extends AbstractListMenu<TempBonusFacade> implements ChangeListener<Boolean>
	{

		private TempBonusMenu()
		{
			super("in_mnuEditTempBonus");
		}

		@Override
		protected MenuItem createMenuItem(TempBonusFacade item, int index)
		{
			Objects.requireNonNull(item);
			return new CheckBoxMenuItem(item, character.getTempBonuses().containsElement(item), this);
		}

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            TempBonusFacade bonus = (TempBonusFacade)((CheckBox)((BooleanProperty)observable).getBean()).getUserData();
            if (newValue)
            {
                character.addTempBonus(bonus);
            }
            else
            {
                character.removeTempBonus(bonus);
            }
        }
    }

    /**
     * The Class {@code LoggingLevelMenu} provides a menu to control the
     * level of logging output.
     */

	private static class LoggingLevelMenu extends AbstractRadioListMenu<LoggingLevelWrapper>
	{
		public LoggingLevelMenu()
		{
			super("in_mnuLoggingLevel");
			DefaultListFacade<LoggingLevelWrapper> levels = new DefaultListFacade<>();
			Level currentLvl = Logging.getCurrentLoggingLevel();
			LoggingLevelWrapper current = null;
			for (Level level : Logging.getLoggingLevels())
			{
				LoggingLevelWrapper levelWrapper = new LoggingLevelWrapper(level);
				levels.addElement(levelWrapper);
				if (level == currentLvl)
				{
					current = levelWrapper;
				}
			}
			setListModel(levels);
			setSelectedItem(current);
		}

        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            if (newValue != null)
            {
                Object item = newValue.getUserData();
                Level level = ((LoggingLevelWrapper) item).getLevel();
                Logging.setCurrentLoggingLevel(level);
            }

        }
    }


    /**
     * The Class {@code LoggingLevelWrapper} provides a display wrapper
     * around a Level.
     */

	private static class LoggingLevelWrapper {
        private final Level level;

        public LoggingLevelWrapper(Level level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return LanguageBundle.getString("in_loglvl" + level.getName());
        }

        /**
         * @return the level
         */
        public Level getLevel() {
            return level;
        }

    }
}
