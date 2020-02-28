import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;





public class Form extends JFrame {
	private JPanel contentPane;
	//initialize tickets as 9999
	int ticketNum = 9999;
	String status = "In Progress";
	//ticket object to show the use of get and set with a ticket class. 
	ticket[] ticketObj;
 	JTextField nameTxtBx;
	JTextField titleTxtBx;
	JTextArea commentTxtArea;
	JLabel nameLbl;
	JLabel dateLbl;
	JLabel currentDateLbl;
	JLabel ticketNumberLbl;
	JLabel incrementTicketLbl;
	JLabel titleLbl;
	JLabel commentsLbl;
	JLabel appHeaderLbl;
	JButton editBtn;
	JButton submitBtn;
	JScrollPane commentScrollPane;
	JScrollPane listBxScrollPane;
	JList <String> listBx;
	
	
	
	// database url
	private static String url = "jdbc:mysql://localhost:3306/******?useSSL=false";
	//database username
	private static String username = "******";
	//database password
	private static String password = "******";
	private static String insertTicket = "insert into tickets(date,name,ticketNum,title,comment,status)" +  "values(?,?,?,?,?,?)";
	private static String getTicket = "SELECT * FROM tickets WHERE ticketNum=(SELECT MAX(ticketNum) FROM tickets)";
	private static String fillListBx = "SELECT * FROM tickets";
	
	


	// launch the application and create a new Form object named frame
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	public Form() {
		
		setTitle("Ticket Application");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy" + " hh:mm aa" + " 'CST'");
		String date = dateFormat.format(calendar.getTime());
		
	
		
		// create a new list object name list
		DefaultListModel<String> list = new DefaultListModel<String>();
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameLbl = new JLabel("Name:");
		nameLbl.setForeground(Color.WHITE);
		nameLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		nameLbl.setBounds(18, 90, 83, 20);
		contentPane.add(nameLbl);
		
		
		titleTxtBx = new JTextField();
		titleTxtBx.setToolTipText("Enter Name");
		titleTxtBx.setForeground(Color.WHITE);
		titleTxtBx.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		titleTxtBx.setBackground(Color.GRAY);
		titleTxtBx.setBounds(70, 189, 648, 22);
		contentPane.add(titleTxtBx);
		
		
		nameTxtBx = new JTextField();
		nameTxtBx.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		nameTxtBx.setToolTipText("Enter Name");
		nameTxtBx.setForeground(Color.WHITE);
		nameTxtBx.setBackground(Color.GRAY);
		nameTxtBx.setBounds(70, 89, 285, 22);
		contentPane.add(nameTxtBx);
		nameTxtBx.setColumns(10);
		
		
		commentTxtArea = new JTextArea();
		commentTxtArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		commentTxtArea.setForeground(Color.WHITE);
		commentTxtArea.setLineWrap(true);
		commentTxtArea.setBackground(Color.GRAY);
		commentTxtArea.setBounds(18, 255, 1163, 127);
		
		
		dateLbl = new JLabel("Date:");
		dateLbl.setForeground(Color.WHITE);
		dateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		dateLbl.setBounds(18, 125, 83, 20);
		contentPane.add(dateLbl);
		
		currentDateLbl = new JLabel("");
		currentDateLbl.setText(date);
		currentDateLbl.setForeground(Color.WHITE);
		currentDateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		currentDateLbl.setBounds(70, 125, 260, 20);
		contentPane.add(currentDateLbl);
		
		ticketNumberLbl = new JLabel("Ticket Number:");
		ticketNumberLbl.setForeground(Color.WHITE);
		ticketNumberLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ticketNumberLbl.setBounds(18, 157, 123, 20);
		contentPane.add(ticketNumberLbl);
		
		incrementTicketLbl = new JLabel("");
		incrementTicketLbl.setText(String.valueOf(ticketNum));
		incrementTicketLbl.setForeground(Color.WHITE);
		incrementTicketLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		incrementTicketLbl.setBounds(146, 157, 123, 20);
		contentPane.add(incrementTicketLbl);
		
		submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		submitBtn.setBackground(Color.GRAY);
		submitBtn.setForeground(Color.BLACK);
		submitBtn.setBounds(6, 800, 147, 37);
		contentPane.add(submitBtn);
		
		titleLbl = new JLabel("Title:");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		titleLbl.setBounds(18, 189, 83, 20);
		contentPane.add(titleLbl);
		
		
		
		commentsLbl = new JLabel("Comments:");
		commentsLbl.setForeground(Color.WHITE);
		commentsLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		commentsLbl.setBounds(18, 223, 105, 20);
		contentPane.add(commentsLbl);
		
		appHeaderLbl = new JLabel("Enter Ticket Information");
		appHeaderLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		appHeaderLbl.setBackground(Color.WHITE);
		appHeaderLbl.setForeground(Color.WHITE);
		appHeaderLbl.setBounds(575, 6, 285, 37);
		contentPane.add(appHeaderLbl);
		
		
	
		editBtn = new JButton("Edit");
		editBtn.setForeground(Color.BLACK);
		editBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editBtn.setBackground(Color.GRAY);
		editBtn.setBounds(1287, 800, 147, 37);
		contentPane.add(editBtn);
		
		commentScrollPane = new JScrollPane(commentTxtArea);
		commentScrollPane.setBounds(18, 255, 1163, 127);
		contentPane.add(commentScrollPane);
		
	
		
		// list box setup for displaying names. No content.add for the listBx because it is passed to the scroll pane object to display properly.
		
		listBx = new JList<String>(list);
		listBx.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		listBx.setForeground(Color.WHITE);
		listBx.setBackground(Color.GRAY);
		
		
		listBxScrollPane = new JScrollPane(listBx);
		listBxScrollPane.setSize(1405, 372);
		listBxScrollPane.setLocation(18, 418);
		contentPane.add(listBxScrollPane);
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//create the connection to my local mysql database.
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    Statement stmt = connection.createStatement();
		    ResultSet rsCurrentTicket = stmt.executeQuery(getTicket);
		
		    
		    //loops through the result set to look for the last ticket created and increment it.
		    while(rsCurrentTicket.next()) {
		       ticketNum = rsCurrentTicket.getInt(3);
		       ticketNum++;
		       incrementTicketLbl.setText(String.valueOf(ticketNum));
		    }
		 rsCurrentTicket.close();
		 
		 
		 
		 //result set to connect to database and fill the listbox with previous tickets
		 
		 ResultSet rs = stmt.executeQuery(fillListBx);
		  while(rs.next()) {
			   String dbDate = rs.getString(2);
		       int ticketNumFill = rs.getInt(3);
		       String dbName = rs.getString(4);
		       String dbTitle = rs.getString(5);
		       String dbTicketStatus = rs.getString(7);
		       
		       list.addElement(dbDate + " - " + "Ticket Number: " + ticketNumFill + " - " + dbName + " - Title: " + dbTitle + " - Status: " + dbTicketStatus + ".");
		   }
		   rs.close();
		   
		   
		  //close connection
		    try {
		        connection.close();
		      } catch (Exception e1) {
		        e1.printStackTrace();
		      }
		 
		    
		} catch (SQLException e1) {
		    throw new IllegalStateException("Cannot connect the database!", e1);
		}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// submit button with action event on clicked to display a name selected and also check if a name is selected.
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					
				  	String leftRemovedName = nameTxtBx.getText().replaceAll("^\\s+", "");
				    String leftRemovedTitle = titleTxtBx.getText().replaceAll("^\\s+", "");
				    String commentTrimmed = commentTxtArea.getText().trim();
				
				 // Conditional statement to check if a valid name is entered after pressing the enter key and if it is adds it to the list box below.
				    
			    if (leftRemovedName == null || leftRemovedName.length() == 0) {
			    	JOptionPane.showMessageDialog(null,"Please enter a valid name" , "Enter Name", JOptionPane.ERROR_MESSAGE);
			    }
			    
			    else if (leftRemovedTitle == null || leftRemovedTitle.length() == 0) {
			    	JOptionPane.showMessageDialog(null,"Please enter a valid title" , "Enter Title", JOptionPane.ERROR_MESSAGE);
			    }
			    
			    else if (commentTrimmed == null || commentTrimmed.length() == 0) {
			    	JOptionPane.showMessageDialog(null,"Please enter a valid comment" , "Enter Comment", JOptionPane.ERROR_MESSAGE);
			    }
			    else {
			    	
			    	
			    	
			    	// database connection
			    	
			    	try (Connection connection = DriverManager.getConnection(url, username, password)) {
					    PreparedStatement stmt=connection.prepareStatement(insertTicket);  
					    stmt.setString(1, date);
					    stmt.setString(2, leftRemovedName); 
					    stmt.setInt(3, ticketNum);
					    stmt.setString(4, leftRemovedTitle);
					    stmt.setString(5, commentTrimmed);
					    stmt.setString(6, status);
					    list.addElement(date + " - " + leftRemovedName + " - Ticket Number: " + ticketNum++ + " - Title: " + leftRemovedTitle + " - Status: " + status + ".");
					    incrementTicketLbl.setText(String.valueOf(ticketNum));
					    
					    
					    stmt.executeUpdate();  
				
					    try {
					        connection.close();
					      } catch (Exception e1) {
					        e1.printStackTrace();
					      }
					 
					    
					} catch (SQLException e1) {
					    throw new IllegalStateException("Cannot connect the database!", e1);
					}
			    
			    	
			    	nameTxtBx.setText("");
			    	titleTxtBx.setText("");
			    	commentTxtArea.setText("");
			    
			    	listBxScrollPane.revalidate();
					listBxScrollPane.repaint();
					
			    
			    	
			    	
			    }
			    
			
			    
		}

		
			
			
				
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
	
		// scroll pane object that takes in the listBx data to display.
		
		
		
	
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedName = listBx.getSelectedValue();
				int selectedIndex = listBx.getSelectedIndex();
				int selectedIndexDB = selectedIndex + 1;
				String fillEditForm = "SELECT * FROM tickets WHERE id=" + selectedIndexDB;
			
				 
				
				
				
;				
				if (selectedName == null || selectedName.length() == 0) {
					JOptionPane.showMessageDialog(null,"You have not selected a ticket please try again." , "Select Ticket", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try (Connection connection = DriverManager.getConnection(url, username, password)) {
					    
					   
					    
					    Statement stmt = connection.createStatement();
					    ResultSet rs = stmt.executeQuery(fillEditForm);
					 
					    while(rs.next()) {
						  String dbDate = rs.getString(2);
					      int ticketNumFill = rs.getInt(3);
					      String dbName = rs.getString(4);
					      String dbTitle = rs.getString(5);
					      String dbComment = rs.getString(6);
					      String dbStatus = rs.getString(7);
					      
					      
					      editTicketFrame editFrame = new editTicketFrame(url, username, password, dbStatus, list, listBx, selectedIndexDB, selectedIndex);
					      editFrame.editNameTxtBx.setText(dbName);
					      editFrame.editTitleTxtBx.setText(dbTitle);
					      editFrame.editCurrentTicketLbl.setText(String.valueOf(ticketNumFill));
					      editFrame.editDateLbl.setText(dbDate);
					      editFrame.editCommentTxtArea.setText(dbComment);
					    if (dbStatus.equals("Resolved")) {
					    	  editFrame.viewOnlyLbl.setVisible(true);
					    	  editFrame.editSubmitBtn.setVisible(false);
					    	  editFrame.resolveChkBx.setVisible(false);
					    	  editFrame.editNameTxtBx.setEditable(false);
					    	  editFrame.editTitleTxtBx.setEditable(false);
					    	  editFrame.editCommentTxtArea.setEditable(false);
					    	  editFrame.editTicketHeaderLbl.setText("View Resolved Ticket Number: " + String.valueOf(ticketNumFill));
					      }
					       editFrame.setVisible(true);
					    }
					      rs.close();
					   
					      
					   
					  
					    try {
					        connection.close();
					      } catch (Exception e1) {
					        e1.printStackTrace();
					      }
					 
					    
					} catch (SQLException e1) {
					    throw new IllegalStateException("Cannot connect the database!", e1);
					}

				}	
			
		
			}
			
		});
		
	
		
	}
}



