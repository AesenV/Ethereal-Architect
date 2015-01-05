/*
 *  Ethereal Architect
 *  Copyright (C) 2014-2015 Aesen Vismea
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gameminers.ethereal.architect.listener;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gameminers.ethereal.architect.EtherealArchitect;
import com.gameminers.ethereal.architect.utility.ModelExtensionFilter;
import com.gameminers.ethereal.lib.Resources;

public class OpenFileListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog dialog = new FileDialog(EtherealArchitect.window); // aww yiss, native file dialogs
		dialog.setDirectory(null);
		dialog.setTitle("Open Block/Item Model");
		dialog.setMode(FileDialog.LOAD);
		dialog.setIconImages(Resources.getPNGAsset("iface/icon", "iface/icon-32"));
		dialog.setFilenameFilter(new ModelExtensionFilter());
		dialog.setVisible(true);
	}

}
