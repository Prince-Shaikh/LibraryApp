package libraryApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReportsScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnA;
	private JButton btnB;
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
		setTitle("Reports");
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
		
		btnA = new JButton("Lending Details");
		btnA.setBounds(275, 174, 455, 72);
		panelMain.add(btnA);
		btnA.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnA.addActionListener(this);
		btnA.setActionCommand("A");
		
		btnB = new JButton("Books Details");
		btnB.setBounds(275, 300, 455, 72);
		panelMain.add(btnB);
		btnB.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnB.addActionListener(this);
		btnB.setActionCommand("B");
		
		panelA = new JPanel();
		panelA.setLayout(null);
		panelA.setBounds(0, 0, 1006, 520);
		contentPane.add(panelA);
		
		comboA = new JComboBox<>();
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
			updateBookItems();
			
		}else if(command.equals("B")) {
			panelMain.setVisible(false);
			panelA.setVisible(true);
			
			comboA.setVisible(false);
				
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			model.setRowCount(0);
			model.setColumnCount(0);
			    
			String[] colNames = {"Books","Quantity"};
			model.setColumnIdentifiers(colNames);

			for(Books b: App.books) {
				model.addRow(new Object[] {b.getTitle(),b.getQuantity()});
			}
	    
			table.setModel(model);
			
		}else if(command.equals("Back")) {
			panelMain.setVisible(true);
			panelA.setVisible(false);
			
			
		}else if(command.equals("ComboA")) {
			int index = comboA.getSelectedIndex();
			if(index != 0) {
				Books book = App.books.get(index-1);
				
				Member[] members = book.getBooksGiven();
				
				String[] names = new String[members.length];
				int i = 0;
				for(Member m : members) {
					names[i] = m.getName();
					i++;
				}
			    DefaultTableModel model = (DefaultTableModel) table.getModel();

			    model.setRowCount(0);
			    model.setColumnCount(0);
			    
			    String[] colNames = {"Member"};
			    model.setColumnIdentifiers(colNames);

			    // Append a row
			    for(String str : names) {
			    	model.addRow(new Object[] {str});
			    }
			    
			    table.setModel(model);
			}
			
		}
			
}
		

	
	private void updateBookItems() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Books");
		for(Books course: App.books) {
			model.addElement(course.getTitle());
		}
		comboA.setModel(model);
		if(App.books.size()<1)
			comboA.setEnabled(false);
		else
			comboA.setEnabled(true);
		
	}
//	private void updateStudentItems() {
//		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
//		model.addElement("Students");
//		for(Member student: App.members) {
//			model.addElement(student.getName());
//		}
//		comboA.setModel(model);
//		if(App.members.size()<1)
//			comboA.setEnabled(false);
//		
//	}
}
