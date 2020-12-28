/*
 * Copyright 2010 Connor Petty <cpmeister@users.sourceforge.net>
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
package pcgen.gui2.javafx.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.action.Action;
import pcgen.facade.util.event.ListEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRadioListMenu<E> extends AbstractListMenu<E> implements ChangeListener<Toggle> {

    private final ToggleGroup group = new ToggleGroup();
    private final Map<E, RadioMenuItem<E>> menuMap = new HashMap<>();
    @SuppressWarnings("FieldHasSetterButNoGetter")
    private E selectedItem = null;

    protected AbstractRadioListMenu(Action action) {

        super(action);

        group.selectedToggleProperty().addListener(this);
    }

    @Override
    protected MenuItem createMenuItem(E item, int index) {
        RadioMenuItem<E> menuItem = new RadioMenuItem<>(item, item == selectedItem);
        group.getToggles().add(menuItem);
        menuMap.put(item, menuItem);
        return menuItem;
    }

    @Override
    public void elementRemoved(ListEvent<E> e) {
        group.getToggles().remove(group.getToggles().get(e.getIndex()));
        menuMap.remove(e.getElement());
        super.elementRemoved(e);
    }

    public void setSelectedItem(E item) {
        RadioMenuItem<E> menuItem = menuMap.get(item);
        if (menuItem != null) {
            group.selectToggle(menuItem);
        }
        selectedItem = item;
    }

    /**
     * Update the menu so that no entries are selected.
     */
    protected void clearSelection() {
        group.selectToggle(null);
        selectedItem = null;
    }

    private static final class RadioMenuItem<E> extends javafx.scene.control.RadioMenuItem {
        private RadioMenuItem(E item, boolean selected) {
            super(item.toString());
            setSelected(selected);
            setUserData(item);
        }
    }

}
