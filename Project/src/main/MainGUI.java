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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextPane;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
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
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
//	void showOnClick(JTree tree, MouseEvent me, JTextPane jtp) {
//	    TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
//	    if (tp != null) {
//	    	for(Student stu : main.Main.current_students) {
//	    	    TreePath tps = tree.getPathForLocation(me.getX(), me.getY());
//	    		if(tps.toString().substring(19, 23).equalsIgnoreCase(stu.major.getName().strip().substring(0,4))){
//	    		      jtp.setText(tps.toString() +"\n" + "Number of failed students: " + stu.major.failedStudentsNumber);
//	    		}
//	    		else {
//	    		
//	    		}
//	    	}
//
//	    }
//	    else {
//	      jtp.setText("");
//	    }
//	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JProgressBar progressBar = new JProgressBar();

		JLabel lblNewLabel = new JLabel("Students:");
		JTextPane textPane = new JTextPane();
		
		JTree tree = new JTree();


//	    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
//	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
//	    JScrollPane jsp = new JScrollPane(tree, v, h);
//	    tree.addMouseListener(new MouseAdapter() {
//	        public void mouseClicked(MouseEvent me) {
//	          showOnClick(tree, me, textPane);
//	        }
//	      });
	    
	    
	    
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("All") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("Departments");
					for(Department d : main.Department.allDepartments) {
						node_2 = new DefaultMutableTreeNode(d.getName());
						
						
					for(Major j:d.getMajors()) {

						
//	for(ArrayList<Course> courses:j.getPlan()) {
//	for(Course course_:courses) {
//node_2.add(new DefaultMutableTreeNode(course_.getName()));
//	}
//} 
					}
						node_1.add(node_2);
					}
					add(node_1);
				}
			}
		));
		
		JScrollBar scrollBar = new JScrollBar();
		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tree, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textPane)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(tree, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
						.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
