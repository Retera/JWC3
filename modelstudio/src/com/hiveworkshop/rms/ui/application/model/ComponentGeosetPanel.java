package com.hiveworkshop.rms.ui.application.model;

import com.hiveworkshop.rms.editor.model.EditableModel;
import com.hiveworkshop.rms.editor.model.Geoset;
import com.hiveworkshop.rms.editor.wrapper.v2.ModelViewManager;
import com.hiveworkshop.rms.ui.application.edit.ModelStructureChangeListener;
import com.hiveworkshop.rms.ui.application.edit.mesh.activity.UndoActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ComponentGeosetPanel extends JPanel implements ComponentPanel<Geoset> {
	private Geoset geoset;
	private ModelViewManager modelViewManager;
	private UndoActionListener undoActionListener;
	private ModelStructureChangeListener modelStructureChangeListener;
	private ComponentGeosetMaterialPanel materialPanel;
	private final Map<Geoset, ComponentGeosetMaterialPanel> materialPanels;
	private boolean listenersEnabled = true;


	public ComponentGeosetPanel(final ModelViewManager modelViewManager,
	                            final UndoActionListener undoActionListener,
	                            final ModelStructureChangeListener modelStructureChangeListener) {
		this.undoActionListener = undoActionListener;
		this.modelViewManager = modelViewManager;
		this.modelStructureChangeListener = modelStructureChangeListener;
		materialPanels = new HashMap<>();
		setLayout(new MigLayout());

		add(new JLabel("Material:"), "wrap");
		materialPanel = new ComponentGeosetMaterialPanel();
		add(materialPanel);
	}

	@Override
	public void setSelectedItem(final Geoset geoset) {
		this.geoset = geoset;

		remove(materialPanel);

		materialPanels.putIfAbsent(geoset, new ComponentGeosetMaterialPanel());
		materialPanel = materialPanels.get(geoset);

		materialPanel.setMaterialChooser(geoset, modelViewManager, undoActionListener, modelStructureChangeListener);
		add(materialPanel);
		revalidate();
		repaint();
	}


	@Override
	public void save(final EditableModel model, final UndoActionListener undoListener,
	                 final ModelStructureChangeListener changeListener) {
	}

}