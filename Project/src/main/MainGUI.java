package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, ArrayList<Student> stus) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI(stus);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI(ArrayList<Student> stus) {
		initialize(stus);
	}

	
		public static void updateJList(ArrayList<Student> newContents) { //WIP
			DefaultListModel model = new DefaultListModel();
		    for (Student s : newContents) {
		        model.addElement(s);
		    }
		    JList list= new JList(model);
			 list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			 list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			 list.setVisibleRowCount(-1);
			 JScrollPane listScroller = new JScrollPane(list);
			 listScroller.setPreferredSize(new Dimension(250, 80));
		}
	
	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(ArrayList<Student> stus) {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JProgressBar progressBar = new JProgressBar();
		
		
		DefaultListModel dList = new DefaultListModel();
		for(Student s: stus) {
			dList.addElement(s.name +" : " + s.id);
		}
		 JList list = new JList(dList);
		 list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		 list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		 list.setVisibleRowCount(-1);
		 JScrollPane listScroller = new JScrollPane(list);
		 listScroller.setPreferredSize(new Dimension(250, 80));

		
		JLabel lblNewLabel = new JLabel("Students:");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addGap(18)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
