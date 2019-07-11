package libraryApp;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EnrollScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelOK;
	private JPanel panelDefault;
	private JComboBox<String> comboBook;
	private JComboBox<String> comboMember;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public EnrollScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Enroll Students");
		setBounds(100, 100, 1024, 567);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLend = new JLabel("LEND BOOK");
		lblLend.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblLend.setBounds(50, 13, 370, 50);
		contentPane.add(lblLend);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setBounds(50, 85, 56, 16);
		contentPane.add(lblBook);
		
//		Integer[] arr = {2,3};
		comboBook = new JComboBox<String>();
		comboBook.setBounds(150, 82, 250, 22);
		comboBook.setEditable(false);
		comboBook.setActionCommand("CourseCombo");
		comboBook.addActionListener(this);
		contentPane.add(comboBook);
		
		panelOK = new JPanel();
		panelOK.setBounds(12, 462, 982, 45);
		contentPane.add(panelOK);
		panelOK.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(862, 0, 120, 45);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		panelOK.add(btnCancel);
		
		btnSave = new JButton("Done");
		btnSave.setBounds(730, 0, 120, 45);
		btnSave.addActionListener(this);
		btnSave.setActionCommand("Save");
		panelOK.add(btnSave);
		
		panelDefault = new JPanel();
		panelDefault.setBounds(12, 462, 982, 45);
		contentPane.add(panelDefault);
		panelDefault.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(862, 0, 120, 45);
		btnClose.addActionListener(this);
		btnClose.setActionCommand("Close");
		panelDefault.add(btnClose);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(730, 0, 120, 45);
		btnDelete.addActionListener(this);
		btnDelete.setActionCommand("Delete");
		panelDefault.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(598, 0, 120, 45);
		btnEdit.addActionListener(this);
		btnEdit.setActionCommand("Edit");
		panelDefault.add(btnEdit);
		
		btnNew = new JButton("New");
		btnNew.setBounds(466, 0, 120, 45);
		btnNew.addActionListener(this);
		btnNew.setActionCommand("New");
		panelDefault.add(btnNew);
		
		JLabel lblStudent = new JLabel("Member");
		lblStudent.setBounds(50, 137, 56, 16);
		contentPane.add(lblStudent);
		
		comboMember = new JComboBox<String>();
		comboMember.setEditable(false);
		comboMember.setBounds(150, 134, 250, 22);
		comboMember.setActionCommand("StudentCombo");
		comboMember.addActionListener(this);
		contentPane.add(comboMember);
		
		panelOK.setVisible(false);
		panelDefault.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Close")) {
			this.dispose();
			
		}else if(command.equals("New")) {
			panelOK.setVisible(true);
			panelDefault.setVisible(false);
			
			comboMember.setActionCommand("StudentCombo");
			comboBook.setActionCommand("CourseCombo");
			btnSave.setActionCommand("Save");
			btnSave.setText("Save");
			
			disableElements();
			updateCourseItems();
			updateStudentItems();
			
		}else if(command.equals("Save")) {
		
			if(comboBook.getSelectedIndex() !=0 ) {
				Member student;
				int course = comboBook.getSelectedIndex()-1;				
				
				if(comboMember.getSelectedIndex() != 0) {
					int studentindex = comboMember.getSelectedIndex()-1;
					student = App.members.get(studentindex); 
					App.books.get(course).enrollStudent(student);
				}else {
					JOptionPane.showMessageDialog(null, "Please select a student!");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Please Select a course!");
			}
			
			
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
		}else if(command.equals("Edit")) {
			panelOK.setVisible(true);
			panelDefault.setVisible(false);
			
			comboMember.setActionCommand("StudentComboEdit");
			comboBook.setActionCommand("CourseComboEdit");
			btnSave.setActionCommand("Save");
			btnSave.setText("Save");
			
			disableElements();
			updateCourseItems();

		}else if(command.equals("Delete")) {
			panelOK.setVisible(true);
			panelDefault.setVisible(false);
			
			btnSave.setActionCommand("SaveDelete");
			btnSave.setText("Delete");
			
			
			comboBook.setActionCommand("CourseComboEdit");
			comboMember.setActionCommand("StudentComboEdit");
			
			disableElements();
			updateCourseItems();

		}else if(command.equals("Cancel")) {
			
			disableElements();
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
		}else if(command.equals("CourseCombo")) {
			String item = (String) comboBook.getSelectedItem();
			
			if(item.equals("Courses") != true) {
				comboMember.setEnabled(true);
				updateStudentItems();
			}
			
		}else if(command.equals("StudentCombo")) {
			String item = (String) comboMember.getSelectedItem();
			
			if(item.equals("Student") != true) {

			}
		}else if(command.equals("StudentComboEdit")) {
			Books course = App.books.get(comboBook.getSelectedIndex()-1);
			String item = (String) comboMember.getSelectedItem();
			
			if(item.equals("Student") != true) {
				
				int studentindex = comboMember.getSelectedIndex()-1;
				Member student = course.getEnrolledStudents()[studentindex]; 
				
				int[] marks = course.getStudentMarks(student);
				
				int mid = marks[0],finalMarks = marks[1],seasional = marks[2];

				int totalMarks = mid + finalMarks + seasional;
				
			}

		}else if(command.equals("CourseComboEdit")) {
			String item = (String) comboBook.getSelectedItem();
			
			if(item.equals("Courses") != true) {
				comboMember.setEnabled(true);
				Books course = App.books.get(comboBook.getSelectedIndex()-1);
				updateEnrolledStudents(course);
			}
		}else if(command.equals("SaveDelete")) {
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
			if(comboBook.getSelectedIndex() !=0 ) {
				Member student;
				Books book = App.books.get(comboBook.getSelectedIndex()-1);				
				
				if(comboMember.getSelectedIndex() != 0) {
					student = book.getEnrolledStudents()[comboMember.getSelectedIndex()-1];
					book.unEnrollStudent(student);
					JOptionPane.showMessageDialog(null, "Book record Deleted!");
				}else {
					JOptionPane.showMessageDialog(null, "Please select a student!");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Please Select a course!");
			}
			
		}
	}

	private void disableElements() {
		comboMember.setEnabled(false);
	}
	
	private void updateEnrolledStudents(Books course) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Students");
		Member[] arr = course.getEnrolledStudents();
		for(Member student: arr) {
			model.addElement(student.getName());
		}
		comboMember.setModel(model);
		if(App.members.size()<1)
			comboMember.setEnabled(false);
		
	}

	private void updateCourseItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Courses");
		for(Books course: App.books) {
			model.addElement(course.getTitle());
		}
		comboBook.setModel(model);
		if(App.books.size()<1)
			comboBook.setEnabled(false);
		else
			comboBook.setEnabled(true);
		
	}	
	private void updateStudentItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Students");
		for(Member member: App.members) {
			model.addElement(member.getName());
		}
		comboMember.setModel(model);
		if(App.members.size()<1)
			comboMember.setEnabled(false);
		
	}
}
