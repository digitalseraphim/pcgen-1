package pcgen.gui2.javafx;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pcgen.gui2.UIContext;
import pcgen.system.Main;

public final class PCGenFXUIManager {
    private static Scene mainScene = null;
    private static Stage stage = null;

    public static void initializeGUI(Stage s)
    {
        PCGenFxRoot pcgenFxRoot = new PCGenFxRoot(new UIContext());
        stage = s;
        mainScene = new Scene(pcgenFxRoot);
        s.setScene(mainScene);
        s.setTitle("Testing!");
    }

    public static void startGUI()
    {
        stage.show();
    }

    public static void displayPreferencesDialog()
    {

    }

    public static void displayAboutDialog()
    {

    }

    public static void closePCGen()
    {
//        if (mainStage != null)
//        {
//            if (!mainStage.closeAllCharacters())
//            {
//                return;
//            }
//
//            mainStage.dispose();
//        }
        Main.shutdown();

    }

}
