package libraryApp;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LendScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBook;
	private JComboBox<String> comboMember;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public LendScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Enroll Students");
		setBounds(100, 100, 460, 340);
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
		
		comboBook = new JComboBox<String>();
		comboBook.setBounds(150, 82, 250, 22);
		comboBook.setEditable(false);
		comboBook.setActionCommand("CourseCombo");
		comboBook.addActionListener(this);
		contentPane.add(comboBook);
		
		JLabel lblStudent = new JLabel("Member");
		lblStudent.setBounds(50, 137, 56, 16);
		contentPane.add(lblStudent);
		
		comboMember = new JComboBox<String>();
		comboMember.setEditable(false);
		comboMember.setBounds(150, 134, 250, 22);
		comboMember.setActionCommand("StudentCombo");
		comboMember.addActionListener(this);
		contentPane.add(comboMember);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.setBounds(280, 227, 120, 45);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Close");
		
		btnSave = new JButton("Done");
		btnSave.setBounds(148, 227, 120, 45);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		btnSave.setActionCommand("Save");
		
		
		
		comboMember.setEnabled(false);
		updateBooks();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Close")) {
			this.dispose();
			
		}else if(command.equals("Save")) {
		
			if(comboBook.getSelectedIndex() !=0 ) {
				Member member;
				int book = comboBook.getSelectedIndex()-1;				
				
				if(comboMember.getSelectedIndex() != 0) {
					int memberindex = comboMember.getSelectedIndex()-1;
					member = App.members.get(memberindex); 
					App.books.get(book).lendBook(member);
					comboMember.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Database Updated!");
				}else {
					JOptionPane.showMessageDialog(null, "Please select a Library Member!");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Please Select a Book First!");
			}
			
			
		}else if(command.equals("CourseCombo")) {
			String item = (String) comboBook.getSelectedItem();
			
			if(item.equals("Books") != true) {
				comboMember.setEnabled(true);
				updateMemberItems();
			}else {
				comboMember.setEnabled(false);
			}
			
		}
	}
	
//	private void updateEnrolledStudents(Books course) {
//		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
//		model.addElement("Students");
//		Member[] arr = course.getEnrolledStudents();
//		for(Member student: arr) {
//			model.addElement(student.getName());
//		}
//		comboMember.setModel(model);
//		if(App.members.size()<1)
//			comboMember.setEnabled(false);
//		
//	}

	private void updateBooks() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Books");
		for(Books b : App.books) {
			model.addElement(b.getTitle());
		}
		comboBook.setModel(model);
		if(App.books.size()<1)
			comboBook.setEnabled(false);
		
	}
	
	private void updateMemberItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Members");
		for(Member member: App.members) {
			model.addElement(member.getName());
		}
		comboMember.setModel(model);
		if(App.members.size()<1)
			comboMember.setEnabled(false);
		
	}
}
