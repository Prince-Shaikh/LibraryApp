package libraryApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

public class App extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ArrayList<Member> members = new ArrayList<>(); 
	protected static ArrayList<Books> books = new ArrayList<>();
	
	private JPanel contentPane;
	private JButton btnStudent,btnCourses,btnReports;
	private JButton buttonCourseRegister;
	private JPanel panelTop;
	private JPanel panelBottom;
	private JButton btnReports_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		
		setTitle("Library Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 567);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 10, 0));
		
		panelTop = new JPanel();
		contentPane.add(panelTop);
		panelTop.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblStudentInformaionSystem = new JLabel("Library Manager");
		panelTop.add(lblStudentInformaionSystem);
		lblStudentInformaionSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentInformaionSystem.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		panelBottom = new JPanel();
		contentPane.add(panelBottom);
		panelBottom.setLayout(new GridLayout(0, 5, 10, 0));
		
		btnStudent = new JButton("Members");
		panelBottom.add(btnStudent);
		btnStudent.setForeground(SystemColor.text);
		btnStudent.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnStudent.setBackground(SystemColor.inactiveCaption);
		btnStudent.addActionListener(this);
		btnStudent.setActionCommand("Members");
		
		btnCourses = new JButton("Books");
		panelBottom.add(btnCourses);
		btnCourses.setForeground(SystemColor.text);
		btnCourses.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCourses.setBackground(SystemColor.inactiveCaption);
		btnCourses.addActionListener(this);
		btnCourses.setActionCommand("Books");
		
		buttonCourseRegister = new JButton("Lend Book");
		panelBottom.add(buttonCourseRegister);
		buttonCourseRegister.setForeground(Color.WHITE);
		buttonCourseRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonCourseRegister.setBackground(SystemColor.inactiveCaption);
		buttonCourseRegister.setActionCommand("Lend");
		buttonCourseRegister.addActionListener(this);
		
		btnReports = new JButton("Book Return");
		panelBottom.add(btnReports);
		btnReports.setForeground(SystemColor.text);
		btnReports.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReports.setBackground(SystemColor.inactiveCaption);
		btnReports.addActionListener(this);
		btnReports.setActionCommand("Return");
		
		btnReports_1 = new JButton("Reports");
		btnReports_1.setForeground(Color.WHITE);
		btnReports_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReports_1.setBackground(SystemColor.inactiveCaption);
		btnReports_1.setActionCommand("Reports");
		btnReports_1.addActionListener(this);
		panelBottom.add(btnReports_1);
		
		addDummyData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Members")) {
			MemberScreen screen = new MemberScreen();
			screen.setVisible(true);
			
		}else if(command.equals("Books")) {
			BooksScreen screen = new BooksScreen();
			screen.setVisible(true);
			
		}else if(command.equals("Lend")) {
			LendScreen screen = new LendScreen();
			screen.setVisible(true);
			
		}else if(command.equals("Return")) {
			ReciveScreen screen = new ReciveScreen();
			screen.setVisible(true);
		}else if(command.equals("Reports")) {
			ReportsScreen screen = new ReportsScreen();
			screen.setVisible(true);
		}
		
	}
	private void addDummyData() {
		members.add(new Member("Moiz Uddin Shaikh", "Faheem Uddin Shaikh", "Male", 46774, 2 ));
		members.add(new Member("ALi Haider", "Shoukat Khan", "Male", 2, 6 ));
		members.add(new Member("Zara Shaheen", "Shaheen Ali", "Female", 4, 5 ));
		members.add(new Member("Sana Zubair", "Zubair Khan", "Female", 8, 2 ));
		
		
		books.add(new Books("HUM-101", "Object Orented Programming", "Dr. Bakhtiar", 3));
		books.add(new Books("HUM-102", "Pak Studies", "Mr. Naeem", 2));
		books.add(new Books("HUM-103", "ECC", "Afzal Khan", 2));
		books.add(new Books("HUM-104", "ICT", "Mr. Mirza Amir", 3));
		books.add(new Books("HUM-105", "PF", "Dr. Bakhtiar", 3));
	}
}
