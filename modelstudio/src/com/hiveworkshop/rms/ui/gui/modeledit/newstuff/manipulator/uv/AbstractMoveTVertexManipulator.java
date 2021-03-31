package com.hiveworkshop.rms.ui.gui.modeledit.newstuff.manipulator.uv;

import com.hiveworkshop.rms.ui.application.edit.uv.types.TVertexEditor;
import com.hiveworkshop.rms.ui.gui.modeledit.UndoAction;
import com.hiveworkshop.rms.ui.gui.modeledit.newstuff.actions.util.GenericMoveAction;
import com.hiveworkshop.rms.ui.gui.modeledit.newstuff.manipulator.Manipulator;
import com.hiveworkshop.rms.ui.gui.modeledit.newstuff.manipulator.MoveDimension;
import com.hiveworkshop.rms.util.Vec3;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Double;

public abstract class AbstractMoveTVertexManipulator extends Manipulator {
	protected final TVertexEditor modelEditor;
	protected final Vec3 moveVector;
	private GenericMoveAction translationAction;
	MoveDimension dir;

	public AbstractMoveTVertexManipulator(final TVertexEditor modelEditor, MoveDimension dir) {
		this.modelEditor = modelEditor;
		moveVector = new Vec3(0, 0, 0);
		this.dir = dir;
	}

	@Override
	protected void onStart(MouseEvent e, final Double mouseStart, final byte dim1, final byte dim2) {
		super.onStart(e, mouseStart, dim1, dim2);
		translationAction = modelEditor.beginTranslation();
	}

	@Override
	public void update(MouseEvent e, final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		resetMoveVector();
		buildMoveVector(mouseStart, mouseEnd, dim1, dim2);
		translationAction.updateTranslation(moveVector.x, moveVector.y, moveVector.z);
	}

	@Override
	public UndoAction finish(MouseEvent e, final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		update(e, mouseStart, mouseEnd, dim1, dim2);
		resetMoveVector();
		return translationAction;
	}

	protected abstract void buildMoveVector(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2);

	private void resetMoveVector() {
		moveVector.set(0, 0, 0);
	}
}
