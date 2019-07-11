package libraryApp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BooksScreen extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2081777787153380646L;
	private JPanel contentPane;
	private JPanel panelOK;
	private JPanel panelDefault;
	private JTextField textFieldTitle;
	private JTextField textFieldCODE;
	private JTextField textFieldAuthor;
	private JButton btnAdd;
	private JButton btnEdit;
	private JTextField textFieldQty;
	/**
	 * Create the frame.
	 */
	public BooksScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Courses");
		setBounds(100, 100, 1024, 567);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCoursetInformation = new JLabel("Book Information");
		lblCoursetInformation.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCoursetInformation.setBounds(50, 13, 370, 50);
		contentPane.add(lblCoursetInformation);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(50, 121, 56, 16);
		contentPane.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(154, 118, 272, 22);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(50, 85, 56, 16);
		contentPane.add(lblID);
		
		textFieldCODE = new JTextField();
		textFieldCODE.setBounds(154, 82, 160, 22);
		contentPane.add(textFieldCODE);
		textFieldCODE.setColumns(10);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(154, 159, 272, 22);
		contentPane.add(textFieldAuthor);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(50, 162, 83, 16);
		contentPane.add(lblAuthor);
		
		JLabel lblSemster = new JLabel("Books in Library");
		lblSemster.setBounds(50, 206, 103, 16);
		contentPane.add(lblSemster);
		
		textFieldQty = new JTextField();
		textFieldQty.setColumns(10);
		textFieldQty.setBounds(154, 203, 116, 22);
		contentPane.add(textFieldQty);
		
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
		
		if(App.books.size()-1 >= 0) 
			fillForm(App.books.size()-1);
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
			
			textFieldCODE.setText("");
			textFieldTitle.setText("");
			textFieldAuthor.setText("");
			textFieldQty.setText("");;
			
		}else if(command.equals("Save")) {

			String code;
			
			if(textFieldCODE.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Book must have a code!");
				btnAdd.doClick();
				
				panelOK.setVisible(false);
				panelDefault.setVisible(true);
				
				return;
			}else {
				code = textFieldCODE.getText();
			}
			String title = textFieldTitle.getText();
			String teacher = textFieldAuthor.getText();
			int qty = 0;
			try {
			qty = Integer.parseInt(textFieldQty.getText());
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Quantity should be a Number!!");
			}
			App.books.add(new Books(code, title, teacher, qty));
		
			panelOK.setVisible(false);
			panelDefault.setVisible(true);
			
		}else if(command.equals("Edit")) {
			String code = ""; 
			code = JOptionPane.showInputDialog(null, "Enter Course Code");
			if(code != null) {
				int index = searchBook(code);
				if(index != -1) {
					fillForm(index);
					
					panelOK.setVisible(true);
					panelDefault.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "No Course with CODE: "+code+" exist in the database.");
				}
			}

		}else if(command.equals("Delete")) {
			String code = ""; 
			code = JOptionPane.showInputDialog(null, "Enter Course CODE to Delete");
			
			if(code != null) {
				int index = searchBook(code);
				if(index != -1) {
					App.books.remove(index);
					JOptionPane.showMessageDialog(null, "Recored Deleted!");
				}else 
					JOptionPane.showMessageDialog(null, "No Book with CODE: "+code+" exist in the database.");
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
		Books book = App.books.get(index);
		
		textFieldCODE.setText(""+book.getCode());
		textFieldTitle.setText(book.getTitle());
		textFieldAuthor.setText(book.getauthor());
		textFieldQty.setText(""+book.getQuantity());
		
	}
	private int searchBook(String code) {
		for(int i = 0; i<App.books.size();i++) {
			if(App.books.get(i).getCode().equals(code)) {
				return i;
			}
		}
		return -1;
	}
}
