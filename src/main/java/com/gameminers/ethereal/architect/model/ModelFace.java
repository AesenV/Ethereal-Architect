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

public class ModelFace {
	public enum Face {
		up, down, north, east, south, west
	}
	private float[] uv = {0, 0, 0, 0};
	private String texture;
	private Face cullface;
	private int rotation = 0;
	private int tintindex = -1;
	private boolean cull = false;
	public float[] getUv() {
		return uv;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public Face getCullface() {
		return cullface;
	}
	public void setCullface(Face cullface) {
		this.cullface = cullface;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getTintindex() {
		return tintindex;
	}
	public void setTintindex(int tintindex) {
		this.tintindex = tintindex;
	}
	public void setCull(boolean cull) {
		this.cull = cull;
	}
	public boolean isCull() {
		return cull;
	}
}
