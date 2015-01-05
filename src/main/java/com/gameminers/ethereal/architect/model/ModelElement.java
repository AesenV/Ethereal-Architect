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

import com.gameminers.ethereal.architect.model.ext.EtherealElementMetadata;

public class ModelElement {
	private float[] from = {0, 0, 0};
	private float[] to = {0, 0, 0};
	private ModelRotation rotation = new ModelRotation();
	private boolean shade = true;
	private ModelFaces faces = new ModelFaces();
	private EtherealElementMetadata ethereal = new EtherealElementMetadata();

	public float[] getFrom() {
		return from;
	}
	public float[] getTo() {
		return to;
	}
	public ModelRotation getRotation() {
		return rotation;
	}
	public boolean isShade() {
		return shade;
	}
	public ModelFaces getFaces() {
		return faces;
	}
	public EtherealElementMetadata getExtras() {
		return ethereal;
	}
}
