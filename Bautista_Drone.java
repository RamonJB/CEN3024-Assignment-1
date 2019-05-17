
// Author: Ramon Bautista
// Date: 5/9/2019
// Program Name: Bautista_Drone
// Purpose: Simulating the controlling of a drone and displaying its' coordinates in (x,y,z) form.

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Bautista_Drone {

	// Drone coordinates
	static int x = 0, y = 0, z = 0;

	public static void main(String[] args) {
		makeGUI();
	}

	// Makes the GUI in which all of the drone functionality takes place.
	public static void makeGUI() {
		JFrame frame = new JFrame("Drone Controller Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		addButtons(frame.getContentPane());

		frame.setVisible(true);
	}

	// Adds the directional buttons to the GUI and their respective actions when
	// clicked.
	public static void addButtons(Container framePane) {
		framePane.setLayout(null);

		// JButton array that will contain all of the directional buttons.
		JButton[] buttons = new JButton[6];

		// JLabels that will contain the drone coordinates and whether the drone is on
		// or off the ground, respectively.
		JLabel coordinateLabel = new JLabel();
		JLabel groundState = new JLabel();

		buttons[0] = new JButton("FORWARD");
		buttons[1] = new JButton("BACKWARD");
		buttons[2] = new JButton("LEFT");
		buttons[3] = new JButton("RIGHT");
		buttons[4] = new JButton("UP");
		buttons[5] = new JButton("DOWN");

		// Adding each button to the pane of the frame GUI.
		for (JButton button : buttons) {
			framePane.add(button);
		}

		framePane.add(groundState);
		framePane.add(coordinateLabel);

		// Getting the size of the borders of the frame.
		Insets insets = framePane.getInsets();

		// Scaling button sizes of FORWARD, BACKWARD, LEFT, and RIGHT based on the
		// backwardsButton since backwards is the longest directional word.
		Dimension buttonSize = buttons[1].getPreferredSize();

		// Setting the location of each button.
		buttons[0].setBounds(160 + insets.left, 10 + insets.top, buttonSize.width, buttonSize.height);
		buttons[1].setBounds(160 + insets.left, 160 + insets.top, buttonSize.width, buttonSize.height);
		buttons[2].setBounds(70 + insets.left, 85 + insets.top, buttonSize.width, buttonSize.height);
		buttons[3].setBounds(250 + insets.left, 85 + insets.top, buttonSize.width, buttonSize.height);

		// Scaling button sizes of UP and DOWN based on the downButton.
		buttonSize = buttons[5].getPreferredSize();

		buttons[4].setBounds(5 + insets.left, 10 + insets.top, buttonSize.width, buttonSize.height);
		buttons[5].setBounds(5 + insets.left, 160 + insets.top, buttonSize.width, buttonSize.height);

		// Setting the labels location and sizes and their default values.
		groundState.setBounds(175 + insets.left, 200 + insets.top, 200, buttonSize.height);
		groundState.setText("ON THE GROUND");

		coordinateLabel.setBounds(195 + insets.left, 220 + insets.top, buttonSize.width, buttonSize.height);
		coordinateLabel.setText("(" + x + "," + y + "," + z + ")");

		addButtonAction(buttons, coordinateLabel, groundState);
	}

	// Adding functionality to the buttons which modifies the drone's coordinates.
	// The drone can move in all directions except when it's on the ground in which
	// it cannot move downwards. Also keeps track of whether the drone is on the
	// ground or above ground.
	public static void addButtonAction(JButton[] buttons, JLabel coordinateLabel, JLabel groundState) {

		// Moving the drone forward, increment y-coordinate.
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				y++;
				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});

		// Moving the drone backward, if drone is heading into negative y-region, keep
		// current position, else decrement y-coordinate.
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				y--;
				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});

		// Moving the drone left, if drone is heading into negative x-region, keep
		// current position, else decrement x-coordinate.
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				x--;
				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});

		// Moving the drone right, increment x-coordinate.
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				x++;
				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});

		// Moving the drone up, increment z-coordinate.
		buttons[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				z++;
				if (z >= 1) {
					groundState.setText("ABOVE GROUND");
				}

				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});

		// Moving the drone down, if drone is heading into negative z-region, keep
		// current position, else decrement z-coordinate.
		buttons[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (z - 1 < 0) {
					groundState.setText("CANNOT MOVE BELOW GROUND");
				}

				else if (z - 1 == 0) {
					z--;
					groundState.setText("ON THE GROUND");
				}

				else
					z--;

				coordinateLabel.setText("(" + x + "," + y + "," + z + ")");
			}
		});
	}

}
