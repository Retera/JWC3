package com.hiveworkshop.rms.ui.application.actions.mesh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hiveworkshop.rms.ui.gui.modeledit.UndoAction;
import com.hiveworkshop.rms.editor.model.Vertex;

/**
 * Undoable snap action.
 *
 * Eric Theller 6/11/2012
 */
public class SnapAction implements UndoAction {
	private final List<Vertex> oldSelLocs;
	private final List<Vertex> selection;
	private final Vertex snapPoint;

	public SnapAction(final Collection<? extends Vertex> selection, final List<Vertex> oldSelLocs,
			final Vertex snapPoint) {
		this.selection = new ArrayList<>(selection);
		this.oldSelLocs = oldSelLocs;
		this.snapPoint = new Vertex(snapPoint);
	}

	@Override
	public void undo() {
		for (int i = 0; i < selection.size(); i++) {
			selection.get(i).set(oldSelLocs.get(i));
		}
	}

	@Override
	public void redo() {
		for (int i = 0; i < selection.size(); i++) {
			selection.get(i).set(snapPoint);
		}
	}

	@Override
	public String actionName() {
		return "snap verteces";
	}
}
