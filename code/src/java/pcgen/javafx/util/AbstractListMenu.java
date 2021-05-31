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
package pcgen.javafx.util;

import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.action.Action;
import pcgen.facade.util.ListFacade;
import pcgen.facade.util.event.ListEvent;
import pcgen.facade.util.event.ListListener;
import javafx.scene.control.SeparatorMenuItem;
import pcgen.javafx.PCGenActions;
import pcgen.system.LanguageBundle;

import java.util.Objects;

public abstract class AbstractListMenu<E> extends Menu implements ListChangeListener<E>, ListListener<E>
{

	private ListFacade<E> listModel;
	private int oldSize = 0;
	private int offset = 0;

	protected AbstractListMenu(Action action)
	{
		this(action.getText(), null);
	}

	protected AbstractListMenu(Action action, ListFacade<E> listModel)
	{
		this(action.getText(), listModel);
	}

	protected AbstractListMenu(String title){
		this(title, null);
	}

	protected AbstractListMenu(String title, ListFacade<E> listModel){
		super(LanguageBundle.getString(title));
		setListModel(listModel);
	}

	public void add(MenuItem menuItem){
		getItems().add(menuItem);
	}

	public void addSeparator(){
		getItems().add(new SeparatorMenuItem());
	}

	@Override
	public void onChanged(Change<? extends E> change) {

	}

	public void elementAdded(ListEvent<E> e)
	{
		rebuildListMenu();
	}

	public void elementRemoved(ListEvent<E> e)
	{
		rebuildListMenu();
	}

	public void elementsChanged(ListEvent<E> e)
	{
		rebuildListMenu();
	}

	public void elementModified(ListEvent<E> e)
	{
	}

//	@Override
//	public Point getToolTipLocation(MouseEvent event)
//	{
//		Dimension size = getSize();
//		double oneRowUpHeight = (size.getHeight() * -1) - 5;
//		return new Point((int) size.getWidth(), (int) oneRowUpHeight);
//	}

	private void rebuildListMenu()
	{
		for (int i = 0; i < oldSize; i++)
		{
			getItems().remove(offset);
		}
		oldSize = listModel.getSize();
		for (int i = 0; i < oldSize; i++)
		{
			getItems().add(i + offset, createMenuItem(listModel.getElementAt(i), i));
		}
		checkEnabled();
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	public void setListModel(ListFacade<E> listModel)
	{
		ListFacade<E> oldModel = this.listModel;
		if (oldModel != null)
		{
			oldModel.removeListListener(this);
			for (int x = 0; x < oldSize; x++)
			{
				getItems().remove(offset);
			}
		}
		this.listModel = listModel;
		if (listModel != null)
		{
			oldSize = listModel.getSize();
			for (int x = 0; x < oldSize; x++)
			{
				getItems().add(x + offset, createMenuItem(listModel.getElementAt(x), x));
			}
			listModel.addListListener(this);
		}
		checkEnabled();
	}

	/**
	 * Create a new dynamic menu item. The menu can optionally have a number at the 
	 * start of the menu item to allow quick selection.
	 * @param item The item to create a menu for.
	 * @param index The 0 based index of the items position in the dynamic item list.
	 * @return A menu item.
	 */
	protected abstract MenuItem createMenuItem(E item, int index);

	private void checkEnabled()
	{
		setDisable(getItems().size() == 0);
	}

	protected static final class CheckBoxMenuItem extends CustomMenuItem
	{
		public CheckBoxMenuItem(Object item, boolean selected, ChangeListener<Boolean> listener)
		{
			super();
			CheckBox checkBox = new CheckBox(Objects.requireNonNull(item).toString());
			setContent(checkBox);
			checkBox.setSelected(selected);
			checkBox.selectedProperty().addListener(Objects.requireNonNull(listener));
			setUserData(Objects.requireNonNull(item));
		}
	}

}
