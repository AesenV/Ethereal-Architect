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

public class ModelFaces {
	private ModelFace down;
	private ModelFace up;
	private ModelFace north;
	private ModelFace south;
	private ModelFace west;
	private ModelFace east;
	public ModelFace getDown() {
		return down;
	}
	public ModelFace getUp() {
		return up;
	}
	public ModelFace getNorth() {
		return north;
	}
	public ModelFace getSouth() {
		return south;
	}
	public ModelFace getWest() {
		return west;
	}
	public ModelFace getEast() {
		return east;
	}
	public void setDown(ModelFace down) {
		this.down = down;
	}
	public void setEast(ModelFace east) {
		this.east = east;
	}
	public void setNorth(ModelFace north) {
		this.north = north;
	}
	public void setSouth(ModelFace south) {
		this.south = south;
	}
	public void setUp(ModelFace up) {
		this.up = up;
	}
	public void setWest(ModelFace west) {
		this.west = west;
	}
}
