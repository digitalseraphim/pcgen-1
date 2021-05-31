package pcgen.javafx;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import pcgen.facade.core.CharacterFacade;
import pcgen.facade.core.DataSetFacade;
import pcgen.facade.core.SourceSelectionFacade;
import pcgen.facade.util.ReferenceFacade;
import pcgen.javafx.UIContext;

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

    public ReferenceFacade<CharacterFacade> getSelectedCharacterRef() {
        return null;
    }

    public ReferenceFacade<DataSetFacade> getLoadedDataSetRef() {
        return null;
    }
}
