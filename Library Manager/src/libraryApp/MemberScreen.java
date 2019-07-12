package libraryApp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberScreen extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045598217353111431L;
	private JPanel contentPane;
	private JPanel panelOK;
	private JPanel panelDefault;
	private JTextField textFieldName;
	private JTextField textFieldCMS;
	private JTextField textFieldFather;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JComboBox<Integer> comboBoxSemster;
	private ButtonGroup btngrp;
	private JButton btnAdd;
	private JButton btnEdit;

	/**
	 * Create the frame.
	 */
	public MemberScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Students");
		setBounds(100, 100, 1024, 567);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberInformation = new JLabel("Member Information");
		lblMemberInformation.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblMemberInformation.setBounds(50, 13, 370, 50);
		contentPane.add(lblMemberInformation);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(50, 121, 56, 16);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(154, 118, 272, 22);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblCms = new JLabel("CMS");
		lblCms.setBounds(50, 85, 56, 16);
		contentPane.add(lblCms);
		
		textFieldCMS = new JTextField();
		textFieldCMS.setBounds(154, 82, 160, 22);
		contentPane.add(textFieldCMS);
		textFieldCMS.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(50, 204, 56, 16);
		contentPane.add(lblGender);
		
		btngrp = new ButtonGroup();
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(223, 200, 78, 25);
		btngrp.add(rdbtnFemale);
		contentPane.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(154, 200, 65, 25);
		btngrp.add(rdbtnMale);
		contentPane.add(rdbtnMale);
		
		JLabel lblSemster = new JLabel("Semster");
		lblSemster.setBounds(50, 246, 56, 16);
		contentPane.add(lblSemster);
		
		Integer[] arr = {1,2,3,4,5,6,7,8};
		comboBoxSemster = new JComboBox<Integer>(arr);
		comboBoxSemster.setBounds(154, 243, 116, 22);
		comboBoxSemster.setEditable(false);
		contentPane.add(comboBoxSemster);
		
		textFieldFather = new JTextField();
		textFieldFather.setColumns(10);
		textFieldFather.setBounds(154, 159, 272, 22);
		contentPane.add(textFieldFather);
		
		JLabel lblFatherName = new JLabel("Father Name");
		lblFatherName.setBounds(50, 162, 83, 16);
		contentPane.add(lblFatherName);
		
		panelOK = new JPanel();
		panelOK.setBounds(12, 462, 982, 45);
		contentPane.add(panelOK);
		panelOK.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(862, 0, 120, 45);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		panelOK.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(730, 0, 120, 45);
		btnSave.addActionListener(this);
		btnSave.setActionCommand("Save");
		panelOK.add(btnSave);
		
		panelDefault = new JPanel();
		panelDefault.setBounds(12, 462, 982, 45);
		contentPane.add(panelDefault);
		panelDefault.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(0, 0, 120, 45);
		btnSearch.addActionListener(this);
		btnSearch.setActionCommand("Search");
		panelDefault.add(btnSearch);
		
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
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(466, 0, 120, 45);
		btnAdd.addActionListener(this);
		btnAdd.setActionCommand("Add");
		panelDefault.add(btnAdd);
		
		if(App.members.size()-1 >= 0) 
			fillForm(App.members.size()-1);
		else 
			btnAdd.doClick();
		
		panelOK.setVisible(false);
		panelDefault.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Close")) {
			this.dispose();
			
		}else if(command.equals("Add")) {
			panelOK.setVisible(true);
			panelDefault.setVisible(false);
			
			textFieldCMS.setText("");
			textFieldName.setText("");
			textFieldFather.setText("");
			btngrp.clearSelection();
			comboBoxSemster.setSelectedIndex(0);
			
		}else if(command.equals("Save")) {
			int cms = -1;
			try {
				cms = Integer.parseInt(textFieldCMS.getText());
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "CMS SHOULD BE A Number!");
				btnAdd.doClick();
				return;
			}
			String name = textFieldName.getText();
			String fatherName = textFieldFather.getText();
			String gender;
			if(rdbtnMale.isSelected())
				gender = "Male";
			else if(rdbtnFemale.isSelected())
				gender = "Female";
			else
				gender = "Not Specified";
			
			int semster = comboBoxSemster.getSelectedIndex()-1;
		
			App.members.add(new Member(name, fatherName, gender, cms, semster));
		
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
		}else if(command.equals("Edit")) {
			String input = ""; 
			input = JOptionPane.showInputDialog(null, "Enter Member CMS");

			
			if(input.equals("") != true) {
				int cms = Integer.parseInt(input);
				
				int index = searchMember(cms);
				if(index != -1) {
					fillForm(index);
					
					panelOK.setVisible(true);
					panelDefault.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "No student with CMS: "+cms+" exist in the database.");
				}
			}
			
		}else if(command.equals("Delete")) {
			String input = ""; 
			input = JOptionPane.showInputDialog(null, "Enter Students CMS to Delete");
			
			if(input != null) {
				int cms;
				
				try {
					cms= Integer.parseInt(input);
					
					int index = searchMember(cms);
					if(index != -1) {
						App.members.remove(index);
						JOptionPane.showMessageDialog(null, "Recored Deleted!");
					}else 
						JOptionPane.showMessageDialog(null, "No student with CMS: "+cms+" exist in the database.");
					
				}catch(NumberFormatException ex) {
					
				}
			}
			
		}else if(command.equals("Search")) {
			btnEdit.doClick();
			
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
			
		}else if(command.equals("Cancel")) {
			
			if(App.members.size()-1 >= 0) 
				fillForm(App.members.size()-1);
			else 
				btnAdd.doClick();
			
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
		}
		
		
		
	}
	private void fillForm(int index) {
		Member member = App.members.get(index);
		
		textFieldCMS.setText(""+member.getCMS());
		textFieldName.setText(member.getName());
		textFieldFather.setText(member.getFatherName());
		
		if(member.getGender().equals("Male")) {
			rdbtnMale.setSelected(true);
			rdbtnFemale.setSelected(false);
		}else if(member.getGender().equals("Female")) {
			rdbtnMale.setSelected(false);
			rdbtnFemale.setSelected(true);
		}else {
			rdbtnMale.setSelected(false);
			rdbtnFemale.setSelected(false);
		}
		
		comboBoxSemster.setSelectedIndex(member.getSemster());
		
	}
	private int searchMember(int cms) {
		for(int i = 0; i<App.members.size();i++) {
			if(App.members.get(i).getCMS()==cms) {
				return i;
			}
		}
		return -1;
	}
}
