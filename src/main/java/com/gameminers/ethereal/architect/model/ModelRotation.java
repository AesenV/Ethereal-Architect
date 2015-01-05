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

public class ModelRotation {
	public enum Axis {
		x, y, z
	}
	private float[] origin = {8, 8, 8};
	private Axis axis;
	private float angle = 0;
	private boolean rescale = false;
	public float[] getOrigin() {
		return origin;
	}
	public Axis getAxis() {
		return axis;
	}
	public void setAxis(Axis axis) {
		this.axis = axis;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public boolean isRescale() {
		return rescale;
	}
	public void setRescale(boolean rescale) {
		this.rescale = rescale;
	}
}
