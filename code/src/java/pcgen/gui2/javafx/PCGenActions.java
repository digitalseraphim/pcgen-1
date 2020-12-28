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
package pcgen.gui2.javafx;

import javafx.event.ActionEvent;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionProxy;
import pcgen.gui2.UIContext;
import pcgen.system.LanguageBundle;

import java.util.Objects;


/**
 * The PCGenActionMap is the action map for the PCGenFrame, and as such
 * hold all of the actions that the PCGenFrame uses. The purpose of this
 * class is to hold all of the regarding actions for the menubar, toolbar,
 * and accessory popup menus that may use them. Since all of the action
 * handlers are Action objects they can be disabled or enabled to cause
 * all buttons that use the actions to update themselves accordingly.
 */
public final class PCGenActions {

    //the File menu commands
    public static final String FILE_COMMAND = "file";
    public static final String NEW_COMMAND = FILE_COMMAND + ".new";
    public static final String OPEN_COMMAND = FILE_COMMAND + ".open";
    public static final String OPEN_RECENT_COMMAND = FILE_COMMAND + ".openrecent";
    public static final String CLOSE_COMMAND = FILE_COMMAND + ".close";
    public static final String CLOSEALL_COMMAND = FILE_COMMAND + ".closeall";
    public static final String SAVE_COMMAND = FILE_COMMAND + ".save";
    public static final String SAVEAS_COMMAND = FILE_COMMAND + ".saveas";
    public static final String SAVEALL_COMMAND = FILE_COMMAND + ".saveall";
    public static final String REVERT_COMMAND = FILE_COMMAND + ".reverttosaved";
    public static final String PARTY_COMMAND = FILE_COMMAND + ".party";
    public static final String OPEN_PARTY_COMMAND = PARTY_COMMAND + ".open";
    public static final String OPEN_RECENT_PARTY_COMMAND = PARTY_COMMAND + ".openrecent";
    public static final String CLOSE_PARTY_COMMAND = PARTY_COMMAND + ".close";
    public static final String SAVE_PARTY_COMMAND = PARTY_COMMAND + ".save";
    public static final String SAVEAS_PARTY_COMMAND = PARTY_COMMAND + ".saveas";
    public static final String PRINT_COMMAND = FILE_COMMAND + ".print";
    public static final String EXPORT_COMMAND = FILE_COMMAND + ".export";
    public static final String EXIT_COMMAND = FILE_COMMAND + ".exit";
    //the Edit menu commands
    public static final String EDIT_COMMAND = "edit";
    public static final String ADD_KIT_COMMAND = EDIT_COMMAND + ".addkit";
    public static final String TEMP_BONUS_COMMAND = EDIT_COMMAND + ".tempbonus";
    public static final String EQUIPMENTSET_COMMAND = EDIT_COMMAND + ".equipmentset";
    //the Source menu commands
    public static final String SOURCES_COMMAND = "sources";
    public static final String SOURCES_LOAD_COMMAND = SOURCES_COMMAND + ".load";
    public static final String SOURCES_LOAD_SELECT_COMMAND = SOURCES_COMMAND + ".select";
    public static final String SOURCES_RELOAD_COMMAND = SOURCES_COMMAND + ".reload";
    public static final String SOURCES_UNLOAD_COMMAND = SOURCES_COMMAND + ".unload";
    public static final String INSTALL_DATA_COMMAND = SOURCES_COMMAND + ".installData";
    //the tools menu commands
    public static final String TOOLS_COMMAND = "tools";
    public static final String PREFERENCES_COMMAND = TOOLS_COMMAND + ".preferences";
    public static final String LOG_COMMAND = TOOLS_COMMAND + ".log";
    public static final String LOGGING_LEVEL_COMMAND = TOOLS_COMMAND + ".loggingLevel";
    public static final String CALCULATOR_COMMAND = TOOLS_COMMAND + ".calculator";
    public static final String COREVIEW_COMMAND = TOOLS_COMMAND + ".coreview";
    public static final String SOLVERVIEW_COMMAND = TOOLS_COMMAND + ".solverview";
    //the help menu commands
    public static final String HELP_COMMAND = "help";
    public static final String HELP_DOCS_COMMAND = HELP_COMMAND + ".docs";
    public static final String HELP_OGL_COMMAND = HELP_COMMAND + ".ogl";
    public static final String HELP_TIPOFTHEDAY_COMMAND = HELP_COMMAND + ".tod";
    public static final String HELP_ABOUT_COMMAND = HELP_COMMAND + ".about";
    private final PCGenFxRoot root;

    public static final String MNU_TOOLS = "mnuTools"; //$NON-NLS-1$
    public static final String MNU_TOOLS_PREFERENCES = "mnuToolsPreferences"; //$NON-NLS-1$
    public static final String MNU_EDIT = "mnuEdit"; //$NON-NLS-1$
    public static final String MNU_FILE = "mnuFile"; //$NON-NLS-1$
    private static final String MNEMONIC_SUFFIX = LanguageBundle.KEY_PREFIX + "mn_"; //$NON-NLS-1$
    private static final String TIP_SUFFIX = "Tip"; //$NON-NLS-1$
    private static final String KEY_PREFIX = LanguageBundle.KEY_PREFIX;

    /**
     * The context indicating what items are currently loaded/being processed in the UI
     */
    private final UIContext uiContext;

    private static PCGenActions singleton = null;


    private PCGenActions(PCGenFxRoot root, UIContext uiContext) {
        this.uiContext = Objects.requireNonNull(uiContext);
        this.root = root;
        ActionMap.setActionFactory(new MyActionFactory());
        ActionMap.register(this);
    }

    public static void registerActions(PCGenFxRoot root, UIContext uiContext) {
        if (singleton == null) {
            singleton = new PCGenActions(root, uiContext);
        }
    }

    @PCGenActionProxy(enabled = false, type = "CharacterAction")
    @ActionProxy(id = ADD_KIT_COMMAND,
            text = KEY_PREFIX + "mnuEditAddKit",
            longText = KEY_PREFIX + "mnuEditAddKit" + TIP_SUFFIX)
    public void editAddKitAction(ActionEvent e) {
        //TODO: create FX KitSelectionDialog
//		KitSelectionDialog kitDialog = new KitSelectionDialog(frame, frame.getSelectedCharacterRef().get());
//		kitDialog.setLocationRelativeTo(frame);
//		kitDialog.setVisible(true);
        System.out.println("Kit Selection Dialog Here!");
    }

    @ActionProxy(id = PREFERENCES_COMMAND,
            text = KEY_PREFIX + MNU_TOOLS_PREFERENCES,
            longText = KEY_PREFIX + MNU_TOOLS_PREFERENCES + TIP_SUFFIX,
            graphic = "Preferences16"
    )
    public void displayPreferencesDialog(ActionEvent e) {
        //TODO: implement display Prefs dialog
//        PCGenUIManager.displayPreferencesDialog();
        System.out.println("Display Preferences Dialog Here");
    }

    @ActionProxy(id = LOG_COMMAND,
            text = KEY_PREFIX + "mnuToolsLog",
            longText = KEY_PREFIX + "mnuToolsLog" + TIP_SUFFIX,
            accelerator = "F10"
    )
    public void debugDialog(ActionEvent e) {
        // TODO: implement Debug dialog
//        GuiAssertions.assertIsNotJavaFXThread();
//        Platform.runLater(() -> {
//            if (dialog == null)
//            {
//                dialog = new DebugDialog();
//            }
//            dialog.show();
//        });
        System.out.println("Debug Dialog Here");
    }

    @ActionProxy(id = CALCULATOR_COMMAND,
            text = KEY_PREFIX + "mnuToolsCalculator",
            longText = KEY_PREFIX + "mnuToolsCalculator" + TIP_SUFFIX,
            accelerator = "F11"
    )
    public void toolsCalculator(ActionEvent e) {
        //TODO: Implement Calculator dialog
//        if (dialog == null)
//        {
//            dialog = new JFXPanelFromResource<>(CalculatorDialogController.class, "CalculatorDialog.fxml");
//        }
//        dialog.showAsStage(LanguageBundle.getString("mnuToolsCalculator"));
        System.out.println("Calculator Dialog Here");
    }

    @ActionProxy(id = COREVIEW_COMMAND,
            text = KEY_PREFIX + "mnuToolsCoreView",
            longText = KEY_PREFIX + "mnuToolsCoreView" + TIP_SUFFIX,
            accelerator = "Shift-F11"
    )
    public void toolCoreView(ActionEvent e) {
        //TODO: implement core view
//        CharacterFacade cf = frame.getSelectedCharacterRef().get();
//        CoreViewFrame cvf = new CoreViewFrame(cf);
//        cvf.setVisible(true);
        System.out.println("Core View Here");
    }

    @PCGenActionProxy(type = "CharacterAction")
    @ActionProxy(id = SOLVERVIEW_COMMAND,
            text = KEY_PREFIX + "mnuToolsSolverView",
            longText = KEY_PREFIX + "mnuToolsSolverView" + TIP_SUFFIX,
            accelerator = "Ctrl-F11"
    )
    public void solverView(ActionEvent e) {
        //TODO: implement solver view
//        SolverViewFrame svf = new SolverViewFrame();
//        svf.setVisible(true);
        System.out.println("Implement Solver View");
    }


    /*
     * The tools menu action to open the install data dialog.
     */
    @ActionProxy(id = INSTALL_DATA_COMMAND,
            text = KEY_PREFIX + "mnuSourcesInstallData",
            longText = KEY_PREFIX + "mnuSourcesInstallData" + TIP_SUFFIX,
            accelerator = "Ctrl-F11"
    )
    public void installData(ActionEvent e) {
        //TODO: Implement Data Installer
//        DataInstaller di = new DataInstaller();
//        di.setVisible(true);
        System.out.println("Data Installer Here");
    }

    @ActionProxy(id = NEW_COMMAND,
            text = KEY_PREFIX + "mnuFileNew",
            longText = KEY_PREFIX + "mnuFileNew" + TIP_SUFFIX,
            accelerator = "shortcut N",
            graphic = "New16"
    )
    public void createNewCharacter(ActionEvent e) {
        //TODO: Figure out how to do this
//		    private NewAction()
//            {
//                super("mnuFileNew", NEW_COMMAND, "shortcut N", Icons.New16);
//                ref = frame.getLoadedDataSetRef();
//                ref.addReferenceListener(new SourceListener());
//                setEnabled(ref.get() != null);
//            }

//            private final class SourceListener implements ReferenceListener<Object>
//            {
//
//                @Override
//                public void referenceChanged(ReferenceEvent<Object> e)
//                {
//                    setEnabled(e.getNewReference() != null);
//                }
//
//            }

        //code for action
//			frame.createNewCharacter(null);
        System.out.println("New Character Here");
    }

    @ActionProxy(id = OPEN_COMMAND,
            text = KEY_PREFIX + "mnuFileOpen",
            longText = KEY_PREFIX + "mnuFileOpen" + TIP_SUFFIX,
            accelerator = "shortcut O",
            graphic = "Open16"
    )
    public void showOpenCharacterChooser(ActionEvent e) {
        //TODO: implement open character chooser
//            frame.showOpenCharacterChooser();
        System.out.println("Open Character Here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = CLOSE_COMMAND,
            text = KEY_PREFIX + "mnuFileClose",
            longText = KEY_PREFIX + "mnuFileClose" + TIP_SUFFIX,
            accelerator = "shortcut W",
            graphic = "Close16"
    )
    public void closeCharacter(ActionEvent e) {
        //TODO: implement close character
//        frame.closeCharacter(frame.getSelectedCharacterRef().get());
        System.out.println("Close Character Here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = CLOSEALL_COMMAND,
            text = KEY_PREFIX + "mnuFileCloseAll",
            longText = KEY_PREFIX + "mnuFileCloseAll" + TIP_SUFFIX,
            graphic = "CloseAll16"
    )
    public void closeAllCharacters(ActionEvent e) {
        //TODO: implement close all chars
//        frame.closeAllCharacters();
        System.out.println("Close All Chars here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = SAVE_COMMAND,
            text = KEY_PREFIX + "mnuFileSave",
            longText = KEY_PREFIX + "mnuFileSave" + TIP_SUFFIX,
            accelerator = "shortcut S",
            graphic = "Save16"
    )
    public void saveCharacter(ActionEvent e)
    {
/* TODO: figure out how to do all this

    private final class SaveAction extends PCGenAction implements ReferenceListener<CharacterFacade> {

        private final FileRefListener fileListener = new FileRefListener();

        private SaveAction() {
            super("mnuFileSave", SAVE_COMMAND, "shortcut S", Icons.Save16);
            ReferenceFacade<CharacterFacade> ref = frame.getSelectedCharacterRef();
            ref.addReferenceListener(this);
            checkEnabled(ref.get());
        }
        @Override
        public void referenceChanged(ReferenceEvent<CharacterFacade> e) {
            CharacterFacade oldRef = e.getOldReference();
            if (oldRef != null) {
                oldRef.getFileRef().removeReferenceListener(fileListener);
            }
            checkEnabled(e.getNewReference());
        }

        private void checkEnabled(CharacterFacade character) {
            if (character != null) {
                ReferenceFacade<File> file = character.getFileRef();
                file.addReferenceListener(fileListener);
                setEnabled(file.get() != null);
            } else {
                setEnabled(false);
            }
        }

        private final class FileRefListener implements ReferenceListener<File> {

            @Override
            public void referenceChanged(ReferenceEvent<File> e) {
                setEnabled(e.getNewReference() != null);
            }

        }

*/

//         Action code
//            final CharacterFacade pc = frame.getSelectedCharacterRef().get();
//            if (pc == null) {
//                return;
//            }
//            frame.saveCharacter(pc);
        System.out.println("save character here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = SAVEAS_COMMAND,
            text = KEY_PREFIX + "mnuFileSaveAs",
            longText = KEY_PREFIX + "mnuFileSaveAs" + TIP_SUFFIX,
            accelerator = "shift-shortcut S",
            graphic = "SaveAs16"
    )
    public void showSaveCharacterChooser(ActionEvent e) {
        //TODO: implement save as
//        frame.showSaveCharacterChooser(frame.getSelectedCharacterRef().get());
        System.out.println("Save As");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = SAVEALL_COMMAND,
            text = KEY_PREFIX + "mnuFileSaveAll",
            longText = KEY_PREFIX + "mnuFileSaveAll" + TIP_SUFFIX,
            graphic = "SaveAll16"
    )
    public void saveAllCharacters(ActionEvent e) {
        //TODO: implement save all
//        frame.saveAllCharacters();
        System.out.println("Save all here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = REVERT_COMMAND,
            text = KEY_PREFIX + "mnuFileRevertToSaved",
            longText = KEY_PREFIX + "mnuFileRevertToSaved" + TIP_SUFFIX,
            accelerator = "shortcut R"
    )
    public void revertCharacter(ActionEvent e) {
        //TODO: implement revert character
//        frame.revertCharacter(frame.getSelectedCharacterRef().get());
        System.out.println("Revert Character");
    }


    @ActionProxy(id = OPEN_PARTY_COMMAND,
            text = KEY_PREFIX + "mnuFilePartyOpen",
            longText = KEY_PREFIX + "mnuFilePartyOpen" + TIP_SUFFIX,
            graphic = "Open16"
    )
    public void showOpenPartyChooser(ActionEvent e) {
        //TODO: implement Open Party
//        frame.showOpenPartyChooser();
        System.out.println("Open Party Chooser Here");
    }

    @ActionProxy(id = CLOSE_PARTY_COMMAND,
            text = KEY_PREFIX + "mnuFilePartyClose",
            longText = KEY_PREFIX + "mnuFilePartyClose" + TIP_SUFFIX,
            graphic = "Close16"
    )
    public void closeParty(ActionEvent e) {
        //TODO: implement close party
//        frame.closeAllCharacters();
        System.out.println("Close Party Here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = SAVE_PARTY_COMMAND,
            text = KEY_PREFIX + "mnuFilePartySave",
            longText = KEY_PREFIX + "mnuFilePartySave" + TIP_SUFFIX,
            graphic = "Save16"
    )
    public void saveParty(ActionEvent e) {
        //TODO: implement show party chooser
//        if (frame.saveAllCharacters() && !CharacterManager.saveCurrentParty()) {
//            frame.showSavePartyChooser();
//        }
        System.out.println("Save Party Chooser here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = SAVEAS_PARTY_COMMAND,
            text = KEY_PREFIX + "mnuFilePartySaveAs",
            longText = KEY_PREFIX + "mnuFilePartySaveAs" + TIP_SUFFIX,
            graphic = "SaveAs16"
    )
    public void showSavePartyChooser(ActionEvent e) {
        //Todo: Implement save party chooser
//        frame.showSavePartyChooser();
        System.out.println("Save Party here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = PRINT_COMMAND,
            text = KEY_PREFIX + "mnuFilePrint",
            longText = KEY_PREFIX + "mnuFilePrint" + TIP_SUFFIX,
            accelerator = "shortcut P",
            graphic = "Print16"
    )
    public void showPrintPreviewDialog(ActionEvent e) {
        // TODO: implement print preview
        //        PrintPreviewDialog.showPrintPreviewDialog(frame);
        System.out.println("Show Print Preview Here");
    }

    @PCGenActionProxy(type="CharacterAction")
    @ActionProxy(id = EXPORT_COMMAND,
            text = KEY_PREFIX + "mnuFileExport",
            longText = KEY_PREFIX + "mnuFileExport" + TIP_SUFFIX,
            accelerator = "shift-shortcut P",
            graphic = "Export16"
    )
    public void showExportDialog(ActionEvent e) {
        //TODO: implement export
//        ExportDialog.showExportDialog(frame);
        System.out.println("Export dialog here");
    }

    @ActionProxy(id = EXIT_COMMAND,
            text = KEY_PREFIX + "mnuFileExit",
            longText = KEY_PREFIX + "mnuFileExit" + TIP_SUFFIX,
            accelerator = "shortcut Q"
    )
    public void closePCGen(ActionEvent e) {
        //TODO: Implement close
//        PCGenUIManager.closePCGen();
        System.out.println("Close PCGEN Here");
    }

    @ActionProxy(id = SOURCES_LOAD_COMMAND,
            text = KEY_PREFIX + "mnuSourcesLoadSelect",
            longText = KEY_PREFIX + "mnuSourcesLoadSelect" + TIP_SUFFIX,
            accelerator = "shortcut L"
    )
    public void showSourceSelectionDialog(ActionEvent e) {
        //TODO: implement source selection
//        frame.showSourceSelectionDialog();
        System.out.println("Select source here");
    }

    @ActionProxy(id = SOURCES_RELOAD_COMMAND,
            text = KEY_PREFIX + "mnuSourcesReload",
            longText = KEY_PREFIX + "mnuSourcesReload" + TIP_SUFFIX,
            accelerator = "shift-shortcut R"
    )
    public void reloadSources(ActionEvent e) {
        /* TODO: Implement this

            private final class ReloadSourcesAction extends PCGenAction implements ReferenceListener<SourceSelectionFacade> {

        private ReloadSourcesAction() {
            super("mnuSourcesReload", SOURCES_RELOAD_COMMAND, "shift-shortcut R");
            ReferenceFacade<SourceSelectionFacade> currentSourceSelectionRef =
                    uiContext.getCurrentSourceSelectionRef();
            currentSourceSelectionRef.addReferenceListener(this);
            checkEnabled(currentSourceSelectionRef.get());
        }
        @Override
        public void referenceChanged(ReferenceEvent<SourceSelectionFacade> e) {
            checkEnabled(e.getNewReference());
        }

        private void checkEnabled(SourceSelectionFacade sources) {
            setEnabled(sources != null && !sources.getCampaigns().isEmpty());
        }

        */

        //action code
//            SourceSelectionFacade sources =
//                    uiContext.getCurrentSourceSelectionRef().get();
//            if (sources != null) {
//                frame.unloadSources();
//                frame.loadSourceSelection(sources);
//            }
        System.out.println("Reload Sources Here");
    }


    @ActionProxy(id = SOURCES_UNLOAD_COMMAND,
            text = KEY_PREFIX + "mnuSourcesUnload",
            longText = KEY_PREFIX + "mnuSourcesUnload" + TIP_SUFFIX,
            accelerator = "shortcut U"
    )
        public void actionPerformed(ActionEvent e) {
            /* TODO: Implement this
                private final class UnloadSourcesAction extends PCGenAction implements ReferenceListener<SourceSelectionFacade> {

        private UnloadSourcesAction() {
            super("mnuSourcesUnload", SOURCES_UNLOAD_COMMAND, "shortcut U");
            ReferenceFacade<SourceSelectionFacade> currentSourceSelectionRef =
                    uiContext.getCurrentSourceSelectionRef();
            currentSourceSelectionRef.addReferenceListener(this);
            checkEnabled(currentSourceSelectionRef.get());
        }
        @Override
        public void referenceChanged(ReferenceEvent<SourceSelectionFacade> e) {
            checkEnabled(e.getNewReference());
        }

        private void checkEnabled(SourceSelectionFacade sources) {
            setEnabled(sources != null && !sources.getCampaigns().isEmpty());
        }


            */

            // Action code
//            frame.unloadSources();
        System.out.println("unload sources here");
    }

    @ActionProxy(id = HELP_DOCS_COMMAND,
            text = KEY_PREFIX + "mnuHelpDocumentation",
            longText = KEY_PREFIX + "mnuHelpDocumentation" + TIP_SUFFIX,
            accelerator = "F1",
            graphic = "Help16"
    )
    public void showHelp(ActionEvent e) {
        //TODO: implement show help
//        try {
//            DesktopBrowserLauncher.viewInBrowser(new File(ConfigurationSettings.getDocsDir(), "index.html"));
//        } catch (IOException ex) {
//            Logging.errorPrint("Could not open docs in external browser", ex);
//            JOptionPane.showMessageDialog(frame, LanguageBundle.getString("in_menuDocsNotOpenMsg"),
//                    LanguageBundle.getString("in_menuDocsNotOpenTitle"), JOptionPane.ERROR_MESSAGE);
//        }
        System.out.println("Show Help Here");
    }

    @ActionProxy(id = HELP_OGL_COMMAND,
            text = KEY_PREFIX + "mnuHelpOGL",
            longText = KEY_PREFIX + "mnuHelpOGL" + TIP_SUFFIX
    )
    public void showOGLDialog(ActionEvent e) {
        //TODO: implement oGL Dialog
//        frame.showOGLDialog();
        System.out.println("Show OGL Dialog HERE");
    }


    @ActionProxy(id = HELP_TIPOFTHEDAY_COMMAND,
            text = KEY_PREFIX + "mnuHelpTipOfTheDay",
            longText = KEY_PREFIX + "mnuHelpTipOfTheDay" + TIP_SUFFIX,
            graphic = "TipOfTheDay16"
    )
    public void showTipsOfTheDay(ActionEvent e) {
        //TODO: Implement tips of the day
//        PCGenFrame.showTipsOfTheDay();
        System.out.println("Tips of the Day here");
    }

    @ActionProxy(id = HELP_ABOUT_COMMAND,
            text = KEY_PREFIX + "mnuHelpAbout",
            longText = KEY_PREFIX + "mnuHelpAbout" + TIP_SUFFIX,
            graphic = "About16"
    )
    public void showAboutDialog(ActionEvent e) {
        //Implement about dialog
//        PCGenFrame.showAboutDialog();
        System.out.println("Show About Dialog here");
    }
}
