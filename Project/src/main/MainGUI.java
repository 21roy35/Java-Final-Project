package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextPane;

/**
 * Date: June 12-2022
 * This is the Main GUI  class
 * @author Team1
 *
 */
public class MainGUI {
	/**
	 * this is frame windo
	 */
	private JFrame frame;
	/**
	 * this is the current deapartments
	 */
	Department currDepartment;
	/**
	 * this is the current major
	 */
	Major currMajor;

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
	 * Create the application .
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 *
	 */
	/**
	 * @param tree
	 * @param me
	 * @param jtp
	 * {@summary
	 * This code displays the number of students with ,
	 *  conflicts in a given department.
	 *   If the mouse is clicked on the root node,
	 *    it will display the total number of conflicts for all students.
	 *    If there are no students with conflicts in the selected department, it will display "0".
	 * }
	 *
	 */


	/**
	 * {@summary This method gets the current department selected in the GUI}
	 * @param tree
	 * @param me
	 */
	void getCurrentDepartment(JTree tree, MouseEvent me) {
		try {
			for(Department d : main.Department.getAllDepartments()) {
				if (tree.getLastSelectedPathComponent().toString().indexOf(d.getName())!=-1) {
					currDepartment = d;
				}
			}} catch (NullPointerException e) {
				//System.out.println("getCurrentDepartment@MainGUI: getLastSelectedPathComponent seems to be null. User probably clicked outside of the parent.");
			}
	}
	Major getCurrentMajor(JTree tree, MouseEvent me) {
		try {
			for(Major m : main.Major.getAllMajors()) {
				String format = tree.getLastSelectedPathComponent().toString() + "Engineering";
				if (format.indexOf(m.getName())!=-1) {
					currMajor = m;
				}
			}} catch (NullPointerException e) { // better to be an if condition
				//	System.out.println("getCurrentMajor@MainGUI: getLastSelectedPathComponent seems to be null. User probably clicked outside of the parent.");
			}
		return currMajor;
	}


	/**
	 * @summary: This method is called to show the data on the right in the TextBox, Info like department count etc.
	 * @param tree
	 * @param me
	 * @param jtp
	 */
	void showOnClick(JTree tree, MouseEvent me, JTextPane jtp) {
		getCurrentDepartment(tree,me);
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		int i = 0;
		if (tp != null){
			if(tp.getLastPathComponent().toString().equals("Departments")) {
				int num = 0;
				for( Department d : main.Department.allDepartments) {
					for(Major m : d.getMajors()) {
						num++;
					}
				}
				jtp.setText("There are a total of " + main.Department.allDepartments.size() + " departments, with " + num + " majors.");
			}else {
				if(tp.getParentPath()!=null) {
					for(Department de : main.Department.allDepartments) {
						if(!jtp.getText().contains(tree.getLastSelectedPathComponent().toString()) && !(tree.getLastSelectedPathComponent().toString().equals("Departments"))) {
							String majors = "";
							int depTotal = 0;
							getCurrentDepartment(tree, me);
							for(Major d : currDepartment.getMajors()) {
								String confNum = main.Main.getConflictInfo(d);
								majors = majors +"\n" + d.getName() + "\nResolved  conflicts in this major: " + main.Main.getConflictInfo(d);
								depTotal = depTotal + Integer.parseInt(confNum);
							}

							jtp.setText("Department name: "  + currDepartment.getName() + "\nCurrent unresolved conflicts: "+ main.StudentRegistrationConflictException.allStudentRegistrationConflictExceptions.size()+"\nNumber of student with resolved conflicts in " + tree.getLastSelectedPathComponent().toString() + ": " + depTotal + "\n"+
									"Majors in this department: " + majors );
						}
					}
					;}

				else {
					jtp.setText("All Students: " + main.Main.current_students.size() +"\nCurrent unresolved conflicts:  " +main.StudentRegistrationConflictException.allStudentRegistrationConflictExceptions.size()+ "\n[Root] All students with resolved conflicts: " + String.valueOf(main.Main.conflictedStudents +
							"\nFull sections count: " + main.FullSectionsException.allFullSectionsExceptions.size() + "\nUnavailable professors: " + main.NoAvailableProfessorException.allNoAvailableProfessorExceptions.size() + "\nFailed students number: "+
							main.Student.failed.size()) );
				}}}
		else {
			jtp.setText("All students with unresolved conflicts including duplicates: \n" + String.valueOf(main.StudentRegistrationConflictException.allStudentRegistrationConflictExceptions.size()));
		}
	}

	/**
	 * 
	 * {@summary:
	 * This is a method called to make an output file, and write all the students including the ones that had conflicts. }
	 * @throws IOException
	 */
	void btnClicked() throws IOException {
		File outputfile = new File("ouput.txt");
		if (outputfile.createNewFile()) {
			System.out.println("File created @" + outputfile.getAbsolutePath());
		} else {
			System.out.println("File already exists. Overwriting...");
			outputfile.delete();
			btnClicked();
		}

		FileWriter writer = new FileWriter(outputfile);;
		BufferedWriter bf = new BufferedWriter(writer);

		bf.write("Students: \n");
		int i = 0 ;
		for(Student s : main.Main.current_students) {
			i++;
			bf.write("\n Index: " + i + "\n Student name: \n" + s.name + "\n Student ID:  "
					+ s.ID + " Student credits: "+ s.creditsCompleted + " Student major: "+ s.getMajor().getName() + " [" + s.getMajor().getSym()+"]\n");
		}

		bf.write("\n Conflicted students: ");
		int j = 0;
		for(Entry<Student, Department> entry : main.StudentRegistrationConflictException.students_info.entrySet()) {
			Student stu = entry.getKey();
			bf.write("\n Index: " + j +"\n [Conflict resolved] \nStudent name: \n" + stu.name + "\n Student ID:  "
					+ stu.ID + " Student credits: "+ stu.creditsCompleted + " Student major: "+ stu.getMajor().getName() + " [" + stu.getMajor().getSym()+"]\n");
			j++;
		}
		bf.write("Output end. Info: \n");
		bf.write(" \nYears completed: " + main.Main.NUMBER_OF_YEARS + " \nStudent count: \n" + main.Main.current_students.size());
		bf.write("\n---------------------------------------END---------------------------------");
		bf.close();
		Desktop opener = Desktop.getDesktop();
		opener.open(outputfile);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JButton button = new JButton();
		button.setText("View students & professors");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnClicked();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});

		JTextPane textPane = new JTextPane();

		JTree tree = new JTree();


		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(tree, v, h);
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				showOnClick(tree, me, textPane);
			}
		});



		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("All") {
					{
						DefaultMutableTreeNode node_1;
						DefaultMutableTreeNode node_2;
						node_1 = new DefaultMutableTreeNode("Departments");
						for(Department d : main.Department.allDepartments) {
							node_2 = new DefaultMutableTreeNode(d.getName());

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
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(tree, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button)
										))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(21)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textPane)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(tree, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
								.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
						.addContainerGap())
				);
		frame.getContentPane().setLayout(groupLayout);
	}
}