package pcgen.gui2.javafx;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import org.controlsfx.control.action.ActionProxy;
import org.controlsfx.control.action.AnnotatedAction;
import org.controlsfx.control.action.AnnotatedActionFactory;
import pcgen.gui2.tools.Icons;
import pcgen.system.LanguageBundle;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

class MyActionFactory implements AnnotatedActionFactory {
    @Override
    public AnnotatedAction createAction(ActionProxy proxy, Method method, Object target) {
        String text = LanguageBundle.getString(proxy.text());
        String graphic = proxy.graphic();
        String longText = LanguageBundle.getString(proxy.longText());
        String accelerator = proxy.accelerator();

        AnnotatedAction action = new AnnotatedAction(text, method, target);
        if (!graphic.isBlank()) {
            action.setGraphic(new ImageView(Icons.valueOf(graphic).asJavaFX()));
        }

        if (!longText.isBlank()) {
            action.setLongText(longText);
        }

        if (!accelerator.isBlank()) {
            // accelerator has three possible forms:
            // 1) shortcut +
            // 2) shortcut-alt +
            // 3) F1
            // (error checking is for the weak!)
            StringTokenizer aTok = new StringTokenizer(accelerator);
            KeyCombination.ModifierValue shift = KeyCombination.ModifierValue.UP;
            KeyCombination.ModifierValue control = KeyCombination.ModifierValue.UP;
            KeyCombination.ModifierValue alt = KeyCombination.ModifierValue.UP;
            KeyCombination.ModifierValue meta = KeyCombination.ModifierValue.UP;
            KeyCombination.ModifierValue shortcut = KeyCombination.ModifierValue.UP;


            // get the first argument
            String aString = aTok.nextToken();

            if (aString.equalsIgnoreCase("shortcut")) {
                shortcut = KeyCombination.ModifierValue.DOWN;
            } else if (aString.equalsIgnoreCase("alt")) {
                alt = KeyCombination.ModifierValue.DOWN;
            } else if (aString.equalsIgnoreCase("shift-shortcut")) {
                shift = KeyCombination.ModifierValue.DOWN;
                shortcut = KeyCombination.ModifierValue.DOWN;
            }

            if (aTok.hasMoreTokens()) {
                // get the second argument
                aString = aTok.nextToken();
            }

            action.setAccelerator(new KeyCharacterCombination(aString, shift, control, alt, meta, shortcut));
        }

        return action;
    }
}


/*
    private abstract class CharacterAction extends PCGenAction {

        private final ReferenceFacade<?> ref;

        private CharacterAction(String prop) {
            this(prop, null, null, null);
        }

        private CharacterAction(String prop, String command, String accelerator) {
            this(prop, command, accelerator, null);
        }

        private CharacterAction(String prop, String command, Icons icon) {
            this(prop, command, null, icon);
        }

        private CharacterAction(String prop, String command, String accelerator, Icons icon) {
            super(prop, command, accelerator, icon);
            ref = frame.getSelectedCharacterRef();
            ref.addReferenceListener(new CharacterListener());
            setEnabled(ref.get() != null);
        }

        private final class CharacterListener implements ReferenceListener<Object> {

            @Override
            public void referenceChanged(ReferenceEvent<Object> e) {
                setEnabled(e.getNewReference() != null);
            }

        }

    }
*/