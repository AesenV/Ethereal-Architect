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
package com.gameminers.ethereal.architect.model.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.gameminers.ethereal.architect.model.Model;
import com.google.gson.Gson;

public class ModelLoader {
	private Gson gson = new Gson();
	public Model load(File file) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(in);
			Model model = gson.fromJson(reader, Model.class);
			if (model.getParentPath() != null) {
				model.setParent(load(file.toPath().resolveSibling("../"+model.getParentPath()).toFile()));
			}
			return model;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
