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
package pcgen.javafx.tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import org.controlsfx.control.action.Action;

public class PCGenAction extends Action
{
	public PCGenAction(String prop)
	{
		this(prop, null, null, null);
	}

	public PCGenAction(String prop, Icons icon)
	{
		this(prop, null, null, icon);
	}

	public PCGenAction(String prop, String command)
	{
		this(prop, command, null, null);
	}

	public PCGenAction(String prop, String command, Icons icon)
	{
		this(prop, command, null, icon);
	}

	public PCGenAction(String prop, String command, String accelerator)
	{
		this(prop, command, accelerator, null);
	}

	public PCGenAction(String prop, String command, String accelerator, Icons icon, Object... substitutes)
	{
		super(prop);

		CommonMenuText.name(this, prop, substitutes);

//		if (command != null)
//		{
//			putValue(ACTION_COMMAND_KEY, command);
//		}
		if (accelerator != null)
		{
			setAccelerator(KeyCombination.valueOf(accelerator));
		}
		if (icon != null)
		{
			Image img = icon.getImageIcon();
			setGraphic(new ImageView(img));
		}
	}
}
