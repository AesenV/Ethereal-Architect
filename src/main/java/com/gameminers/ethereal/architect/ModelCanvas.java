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

import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.FloatBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.gameminers.ethereal.architect.model.Model;
import com.gameminers.ethereal.architect.model.ModelElement;
import com.gameminers.ethereal.architect.model.ModelFace;

public class ModelCanvas extends AWTGLCanvas {
	public class ModelMouseListener implements MouseMotionListener, MouseWheelListener {
		private int lastX;
		private int lastY;
		private int lastWheel;
		@Override
		public void mouseDragged(MouseEvent e) {
			int dX = e.getX() - lastX;
			int dY = e.getY() - lastY;
			lastX = e.getX();
			lastY = e.getY();
			angle += dX;
			tilt += dY;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			lastX = e.getX();
			lastY = e.getY();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			zoom -= (e.getWheelRotation()*2);
		}

	}
	private static final long serialVersionUID = -3869139942416547162L;
	public ModelCanvas() throws LWJGLException {
		super();
		ModelMouseListener listener = new ModelMouseListener();
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
	}
	private int current_width;
	private int current_height;
	
	private static final float[] vertices = {
		// North
		-1.0f, -1.0f, 1.0f,
		1.0f, -1.0f, 1.0f,
		1.0f, 1.0f, 1.0f,
		-1.0f, 1.0f, 1.0f,
		// South
		-1.0f, -1.0f, -1.0f,
		1.0f, -1.0f, -1.0f,
		1.0f, 1.0f, -1.0f,
		-1.0f, 1.0f, -1.0f,
		// Top
		-1.0f, 1.0f, 1.0f,
		1.0f, 1.0f, 1.0f,
		1.0f, 1.0f, -1.0f,
		-1.0f, 1.0f, -1.0f,
		// Bottom
		-1.0f, -1.0f, -1.0f,
		1.0f, -1.0f, -1.0f,
		1.0f, -1.0f, 1.0f,
		-1.0f, -1.0f, 1.0f,
		// West
		1.0f, -1.0f, 1.0f,
		1.0f, -1.0f, -1.0f,
		1.0f, 1.0f, -1.0f,
		1.0f, 1.0f, 1.0f,
		// East
		-1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f, 1.0f,
		-1.0f, 1.0f, 1.0f,
		-1.0f, 1.0f, -1.0f
		}; 
	
	private Model model;
	private boolean lit = true;
	private boolean textured = false;
	private FloatBuffer lightPosition;
	private FloatBuffer lightAmbient;
	
	private float angle = -45;
	private float tilt = -20;
	private float zoom = -100;

	public void setModel(Model model) {
		this.model = model;
	}
	public Model getModel() {
		return model;
	}
	
	public boolean isLit() {
		return lit;
	}
	public boolean isTextured() {
		return textured;
	}
	public void setTextured(boolean textured) {
		this.textured = textured;
	}
	public void setLit(boolean lit) {
		this.lit = lit;
	}
	
	@Override
	protected void initGL() {
		setVSyncEnabled(true);
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.mark();
		lightPosition.put(-4f);
		lightPosition.put(2f);
		lightPosition.put(1f);
		lightPosition.put(5f);
		lightPosition.reset();
		lightAmbient = BufferUtils.createFloatBuffer(4);
		lightAmbient.mark();
		lightAmbient.put(3.0f);
		lightAmbient.put(3.0f);
		lightAmbient.put(3.0f);
		lightAmbient.put(1f);
		lightAmbient.reset();
	}
	@Override
	protected void paintGL() {
		try {
			if (getWidth() != current_width || getHeight() != current_height) {
				current_width = getWidth();
				current_height = getHeight();
				GL11.glViewport(0, 0, current_width, current_height);
			}
			GL11.glClearColor(0.0f, 0.6f, 0.5f, 1.0f);
			GL11.glClearDepth(1.0);
			GL11.glColor3f(1, 1, 1);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GL11.glLoadIdentity();
			GLU.gluPerspective(
				45.0f,
				(float) getWidth() / (float) getHeight(),
				0.1f,
				1000.0f);
			GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, zoom);
			GL11.glRotatef(angle, 0f, 1f, 0f);
			GL11.glRotatef(tilt, 1f, 0f, 0f);
			GL11.glTranslatef(-16, -16, -16);
			if (lit) {
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_LIGHT0);
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, lightAmbient);
			} else {
				GL11.glDisable(GL11.GL_LIGHTING);
			}
			if (textured) {
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			} else {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
			if (model != null) {
				if (model.isAmbientOcclusionEnabled()) {
					GL11.glShadeModel(GL11.GL_SMOOTH);
				} else {
					GL11.glShadeModel(GL11.GL_FLAT);
				}
				for (ModelElement ele : model.getElements()) {
					GL11.glPushMatrix();
					if (ele.isShade()) {
						GL11.glEnable(GL11.GL_LIGHTING);
					} else {
						GL11.glDisable(GL11.GL_LIGHTING);
					}
					float fromX = ele.getFrom()[0];
					float fromY = ele.getFrom()[1];
					float fromZ = ele.getFrom()[2];
					float toX = ele.getTo()[0];
					float toY = ele.getTo()[1];
					float toZ = ele.getTo()[2];
					
					float fX = (fromX > toX ? fromX : toX);
					float fY = (fromY > toY ? fromY : toY);
					float fZ = (fromZ > toZ ? fromZ : toZ);
					float tX = (fromX > toX ? toX : fromX);
					float tY = (fromY > toY ? toY : fromY);
					float tZ = (fromZ > toZ ? toZ : fromZ);
					
					GL11.glTranslatef(fX, fY, fZ);
					float scaleX = tX-fX;
					float scaleY = tY-fY;
					float scaleZ = tZ-fZ;
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glNormal3f(0, 0, -1f);
					for (int i = 0; i < vertices.length/3; i++) {
						int faceIdx = i/4;
						ModelFace face;
						switch (faceIdx) {
							case 0:
								face = ele.getFaces().getNorth();
								break;
							case 1:
								face = ele.getFaces().getSouth();
								break;
							case 2:
								face = ele.getFaces().getUp();
								break;
							case 3:
								face = ele.getFaces().getDown();
								break;
							case 4:
								face = ele.getFaces().getWest();
								break;
							case 5:
								face = ele.getFaces().getEast();
								break;
							default:
								face = null;
								break;
						}
						int idx = i*3;
						float vX = vertices[idx] * scaleX;
						float vY = vertices[idx+1] * scaleY;
						float vZ = vertices[idx+2] * scaleZ;
						/*float u;
						float v;
						GL11.glTexCoord2f(u, v);*/
						GL11.glVertex3f(vX, vY, vZ);
					}
					GL11.glEnd();
					GL11.glPopMatrix();
				}
			}
			GL11.glPopMatrix();
			swapBuffers();
			repaint();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
	}
}
