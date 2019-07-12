package libraryApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

public class ReportsScreen extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnA;
	private JButton btnB;
	private JButton btnC;
	private JPanel panelMain;
	private JPanel panelA;
	private JComboBox<String> comboA;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public ReportsScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Students");
		setBounds(100, 100, 1024, 567);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1006, 520);
		contentPane.add(panelMain);
		panelMain.setLayout(null);
		
		JLabel lblReports = new JLabel("REPORTS");
		lblReports.setBounds(429, 54, 148, 30);
		panelMain.add(lblReports);
		lblReports.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		btnA = new JButton("Students Enrolled in a Course");
		btnA.setBounds(275, 138, 455, 72);
		panelMain.add(btnA);
		btnA.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnA.addActionListener(this);
		btnA.setActionCommand("A");
		
		btnB = new JButton("Students Enrolled in a Course (with marks)");
		btnB.setBounds(275, 264, 455, 72);
		panelMain.add(btnB);
		btnB.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnB.addActionListener(this);
		btnB.setActionCommand("B");
		
		btnC = new JButton("Information Related to Student");
		btnC.setBounds(275, 390, 455, 72);
		panelMain.add(btnC);
		btnC.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnC.addActionListener(this);
		btnC.setActionCommand("C");
		
		panelA = new JPanel();
		panelA.setLayout(null);
		panelA.setBounds(0, 0, 1006, 520);
		contentPane.add(panelA);
		
		comboA = new JComboBox();
		comboA.setBounds(305, 27, 395, 29);
		comboA.addActionListener(this);
		comboA.setActionCommand("ComboA");
		panelA.add(comboA);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(874, 27, 97, 29);
		btnBack.setActionCommand("Back");
		btnBack.addActionListener(this);
		panelA.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 81, 982, 426);
		panelA.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panelA.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("A")) {
			panelMain.setVisible(false);
			panelA.setVisible(true);
			
			comboA.setActionCommand("ComboA");
			updateCourseItems();
			
		}else if(command.equals("B")) {
			panelMain.setVisible(false);
			panelA.setVisible(true);
			
			updateCourseItems();
			comboA.setActionCommand("ComboB");
			
			
		}else if(command.equals("C")) {
			panelMain.setVisible(false);
			panelA.setVisible(true);
			
			comboA.setActionCommand("ComboC");
			updateStudentItems();
			
			
		}else if(command.equals("Back")) {
			panelMain.setVisible(true);
			panelA.setVisible(false);
			
			
		}else if(command.equals("ComboA")) {
			int index = comboA.getSelectedIndex();
			if(index != 0) {
				Books course = App.books.get(index-1);
				
				Member[] enrolledStudents = course.getBooksGiven();
				
				String[] studentNames = new String[enrolledStudents.length];
				int i = 0;
				for(Member student : enrolledStudents) {
					studentNames[i] = student.getName();
					i++;
				}
			    DefaultTableModel model = (DefaultTableModel) table.getModel();

			    model.setRowCount(0);
			    model.setColumnCount(0);
			    
			    String[] colNames = {"Enrolled Students"};
			    model.setColumnIdentifiers(colNames);

			    // Append a row
			    for(String str : studentNames) {
			    	model.addRow(new Object[] {str});
			    }
			    
			    table.setModel(model);
			}
			
		}else if(command.equals("ComboB")) {
			int index = comboA.getSelectedIndex();
			if(index != 0) {
				Books course = App.books.get(index-1);
				
				Member[] enrolledStudents = course.getBooksGiven();
				
			    DefaultTableModel model = (DefaultTableModel) table.getModel();

			    model.setRowCount(0);
			    model.setColumnCount(0);
			    
			    String[] colNames = {"Enrolled Students","Mid Exams","Final Exams","Sessional","Total"};
			    model.setColumnIdentifiers(colNames);

			    int marks[];
			    
			    // Append a row
			    for(Member student : enrolledStudents) {
//			    	marks= course.getStudentMarks(student);
//			    	model.addRow(new Object[] {student.getName(),marks[0],marks[1],marks[2], marks[0]+marks[1]+marks[2]});
			    }
			    
			    table.setModel(model);
			}
			
		}else if(command.equals("ComboC")) {
			int index = comboA.getSelectedIndex();
			if(index != 0) {
				Member student = App.members.get(index-1);
				
				Books[] courses = new Books[App.books.size()];
				
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    
			    model.setRowCount(0);
			    model.setColumnCount(0);
				

				int i = 0;
				for(Books course : App.books) {
					courses[i] = course;
					i++;
				}
				
			    String[] colNames = {"Courses","Mid Exams", "Final Exams", "Seasional","Total"};
			    model.setColumnIdentifiers(colNames);
				
				for(int j = 0; j<courses.length; j++) {
					
					try {
					if(courses[j].getBooksGiven().length != 0) {

						Member[] s = courses[j].getBooksGiven();
						for(i = 0; i<s.length;i++) {
							if(s[i] == student) {
//								int[] studentMarks = courses[j].getStudentMarks(student);
//								int total = studentMarks[0]+studentMarks[1]+studentMarks[2];
//								model.addRow(new Object[] {courses[j].getTitle(),studentMarks[0],studentMarks[1],studentMarks[2],total});
							}
						}
					}
					}catch(NullPointerException exception) {
						
					}
				}
			    table.setModel(model);
			}
			
		}
		
	}
	
	private void updateCourseItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Courses");
		for(Books course: App.books) {
			model.addElement(course.getTitle());
		}
		comboA.setModel(model);
		if(App.books.size()<1)
			comboA.setEnabled(false);
		else
			comboA.setEnabled(true);
		
	}
	private void updateStudentItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Students");
		for(Member student: App.members) {
			model.addElement(student.getName());
		}
		comboA.setModel(model);
		if(App.members.size()<1)
			comboA.setEnabled(false);
		
	}
}
