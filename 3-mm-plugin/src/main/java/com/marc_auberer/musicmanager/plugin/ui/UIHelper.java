package com.marc_auberer.musicmanager.plugin.ui;

import javax.swing.*;
import java.awt.*;

public class UIHelper {

    // Private constructor to hide the public one (Good according to SonarCloud)
    private UIHelper() {}

    public static void placeUIComp(JPanel rootPanel, JComponent component, int gridX, int gridY,
                                   int gridWidth, int gridHeight, int gridWeightX) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = gridWeightX;
        rootPanel.add(component, constraints);
    }
}
