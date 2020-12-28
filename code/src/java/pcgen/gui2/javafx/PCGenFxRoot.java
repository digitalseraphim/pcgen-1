package pcgen.gui2.javafx;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import pcgen.facade.core.SourceSelectionFacade;
import pcgen.gui2.UIContext;

import java.io.File;

public class PCGenFxRoot extends VBox {

    public PCGenFxRoot(UIContext uiContext) {
        MenuBar mb = new PCGenMenuBar(this, uiContext);
        getChildren().add(mb);
    }

    public void loadCharacterFromFile(File f){

    }


    public void loadPartyFromFile(File f) {
    }

    public boolean loadSourceSelection(SourceSelectionFacade userData) {
        return true;
    }
}
