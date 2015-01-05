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
package com.gameminers.ethereal.architect;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.lwjgl.LWJGLException;

import com.gameminers.ethereal.architect.listener.CloseFileListener;
import com.gameminers.ethereal.architect.listener.MainWindowListener;
import com.gameminers.ethereal.architect.listener.NewFileListener;
import com.gameminers.ethereal.architect.listener.OpenFileListener;
import com.gameminers.ethereal.architect.listener.QuitListener;
import com.gameminers.ethereal.architect.model.loader.ModelLoader;
import com.gameminers.ethereal.lib.Components;
import com.gameminers.ethereal.lib.Frames;
import com.gameminers.ethereal.lib.Resources;
import com.google.gson.Gson;

public class EtherealArchitect {
	public static JFrame window;
	private static JTabbedPane tabs;
	public static final Gson gson = new Gson();
	public static void main(String[] args) throws LWJGLException {
		Frames.initLAF();
		window = Frames.create("Architect");
		window.addWindowListener(new MainWindowListener());
		window.setJMenuBar(createMenuBar());
		
		tabs = new JTabbedPane();
		window.add(tabs);
		
		ModelCanvas canvas = new ModelCanvas();
		try {
			canvas.setModel(new ModelLoader().load(new File("/home/aesen/WindogeShared/Andromeda-assets/assets/minecraft/models/item/cola_gun.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tabs.add("cola_gun.json", canvas);
		
		window.setVisible(true);
	}
	
	public static void quit() {
		System.exit(0); // TODO
	}
	
	private static JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(createFileMenu());
		bar.add(createEditMenu());
		bar.add(createViewMenu());
		bar.add(createToolsMenu());
		bar.add(createHelpMenu());
		return bar;
	}

	private static JMenu createToolsMenu() {
		JMenu tools = new JMenu("Tools");
		return tools;
	}

	private static JMenu createEditMenu() {
		JMenu edit = new JMenu("Edit");
		return edit;
	}

	private static JMenu createViewMenu() {
		JMenu view = new JMenu("View");
		ButtonGroup cam = new ButtonGroup();
		view.add(createRadioMenuItem("Orbit", "iface/orbit", null, "ctrl F", cam));
		view.add(createRadioMenuItem("First Person", "iface/firstperson", null, "F1", cam));
		view.add(createRadioMenuItem("Third Person", "iface/thirdperson", null, "F5", cam));
		view.addSeparator();
		view.add(createCheckMenuItem("Textures", "iface/textures", null, "ctrl T", true));
		view.add(createCheckMenuItem("Lighting", "iface/lighting", null, "ctrl L", true));
		view.addSeparator();
		view.add(createBordersMenu());
		return view;
	}

	private static JMenuItem createBordersMenu() {
		JMenu borders = new JMenu("Borders");
		
		return borders;
	}

	private static JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.add(Components.createAboutDialogMenuItem(window, "Architect"));
		return menu;
	}
	
	private static JMenu createFileMenu() {
		JMenu file = new JMenu("File");
		file.add(createMenuItem("New", "iface/new", new NewFileListener(), "control N"));
		file.add(createMenuItem("Open…", "iface/open", new OpenFileListener(), "control O"));
		file.addSeparator();
		file.add(createMenuItem("Close", "iface/close", new CloseFileListener(), "control W"));
		file.addSeparator();
		file.add(createMenuItem("Quit", "iface/quit", new QuitListener(), "control Q"));
		return file;
	}

	private static JMenuItem createRadioMenuItem(String name, String icon, ActionListener listener, String shortcut, ButtonGroup group) {
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name, Resources.loadPNGIconAsset(icon));
		item.addActionListener(listener);
		group.add(item);
		item.setAccelerator(KeyStroke.getKeyStroke(shortcut));
		return item;
	}
	
	private static JMenuItem createCheckMenuItem(String name, String icon, ActionListener listener, String shortcut, boolean selected) {
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(name, Resources.loadPNGIconAsset(icon));
		item.addActionListener(listener);
		item.setSelected(selected);
		item.setAccelerator(KeyStroke.getKeyStroke(shortcut));
		return item;
	}
	
	private static JMenuItem createMenuItem(String name, String icon, ActionListener listener, String shortcut) {
		JMenuItem item = new JMenuItem(name, Resources.loadPNGIconAsset(icon));
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke(shortcut));
		return item;
	}
}
