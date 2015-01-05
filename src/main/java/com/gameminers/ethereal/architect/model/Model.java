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
package com.gameminers.ethereal.architect.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gameminers.ethereal.architect.model.ext.EtherealModelMetadata;
import com.google.gson.internal.LinkedHashTreeMap;

public class Model {
	// Fields are named as such for easy loading and saving with Gson
	private String parent;
	private boolean ambientocclusion = true;
	private Map<String, String> textures = new LinkedHashTreeMap<String, String>();
	private List<ModelElement> elements = new ArrayList<ModelElement>();
	private ModelDisplayProperties display = new ModelDisplayProperties();
	private EtherealModelMetadata ethereal = new EtherealModelMetadata();
	
	private transient Model parentModel;
	
	public EtherealModelMetadata getExtras() {
		return ethereal;
	}
	public String getParentPath() {
		return parent;
	}
	public void setParentPath(String parent) {
		this.parent = parent;
	}
	public Map<String, String> getTextures() {
		return textures;
	}
	public List<ModelElement> getElements() {
		return elements;
	}
	public ModelDisplayProperties getDisplayProperties() {
		return display;
	}
	public boolean isAmbientOcclusionEnabled() {
		return ambientocclusion;
	}
	public void setAmbientOcclusionEnabled(boolean ambientocclusion) {
		this.ambientocclusion = ambientocclusion;
	}
	public Model getParent() {
		return parentModel;
	}
	public void setParent(Model parentModel) {
		this.parentModel = parentModel;
	}
}
