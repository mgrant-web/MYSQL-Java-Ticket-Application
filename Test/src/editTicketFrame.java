import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class editTicketFrame extends JFrame {

	private JPanel contentPane;
	JTextField editNameTxtBx;
	JTextField editTitleTxtBx;
	JLabel DateLbl;
	JLabel editDateLbl;
	JLabel editNameLbl;
	JLabel editTitleLbl;
	JLabel ticketNumberLbl;
	JLabel editCurrentTicketLbl;
	JLabel editTicketHeaderLbl;
	JLabel editCommentsLbl;
	JLabel viewOnlyLbl;
	JButton editSubmitBtn;
	JTextArea editCommentTxtArea;
	JScrollPane editCommentScrollPane;
	JCheckBox resolveChkBx;
	int selectedIndex;
	int selectedIndexDB;
	String status;
	Object[] optionsYesNoReset = {"Yes", "No", "Reset"};
	Object[] optionsYesNo = {"Yes", "No"};
	



	
	public editTicketFrame(String url, String username, String password, String status, DefaultListModel<String> list, JList<String> listBx, int selectedIndexDB, int selectedIndex) {
		setTitle("Edit Ticket Information");
		this.selectedIndexDB = selectedIndexDB;
		this.selectedIndex = selectedIndex;
		this.status = status;

		
		// queries for mysql database used below
		String updateTicket = "UPDATE tickets SET name=?, title=?, comment=? WHERE id=" + selectedIndexDB;
		String resetTicketInfo = "SELECT name,title,comment FROM tickets WHERE id=" + selectedIndexDB;
		String resolveTicket = "UPDATE tickets SET name=?, title=?, comment=?, status=? WHERE id=" + selectedIndexDB;
		String getStatus = "SELECT status FROM tickets WHERE id=" + selectedIndexDB;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 150, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		editNameLbl = new JLabel("Name:");
		editNameLbl.setForeground(Color.WHITE);
		editNameLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editNameLbl.setBounds(18, 90, 83, 20);
		contentPane.add(editNameLbl);
		
		
		editTitleTxtBx = new JTextField();
		editTitleTxtBx.setToolTipText("Enter Name");
		editTitleTxtBx.setForeground(Color.WHITE);
		editTitleTxtBx.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editTitleTxtBx.setColumns(10);
		editTitleTxtBx.setBackground(Color.GRAY);
		editTitleTxtBx.setBounds(70, 189, 648, 22);
		contentPane.add(editTitleTxtBx);
		
		
		editNameTxtBx = new JTextField();
		editNameTxtBx.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editNameTxtBx.setToolTipText("Enter Name");
		editNameTxtBx.setForeground(Color.WHITE);
		editNameTxtBx.setBackground(Color.GRAY);
		editNameTxtBx.setBounds(70, 89, 285, 22);
		contentPane.add(editNameTxtBx);
		editNameTxtBx.setColumns(10);
		
		
		editCommentTxtArea = new JTextArea("");
		editCommentTxtArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editCommentTxtArea.setForeground(Color.WHITE);
		editCommentTxtArea.setLineWrap(true);
		editCommentTxtArea.setBackground(Color.GRAY);
		editCommentTxtArea.setBounds(18, 255, 1163, 127);
		
		
		DateLbl = new JLabel("Date:");
		DateLbl.setForeground(Color.WHITE);
		DateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		DateLbl.setBounds(18, 125, 83, 20);
		contentPane.add(DateLbl);
		
		editDateLbl = new JLabel("");
		editDateLbl.setForeground(Color.WHITE);
		editDateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editDateLbl.setBounds(70, 125, 260, 20);
		contentPane.add(editDateLbl);
		
		ticketNumberLbl = new JLabel("Ticket Number:");
		ticketNumberLbl.setForeground(Color.WHITE);
		ticketNumberLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ticketNumberLbl.setBounds(18, 157, 123, 20);
		contentPane.add(ticketNumberLbl);
		
		editCurrentTicketLbl = new JLabel("");
		editCurrentTicketLbl.setText(String.valueOf(""));
		editCurrentTicketLbl.setForeground(Color.WHITE);
		editCurrentTicketLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editCurrentTicketLbl.setBounds(146, 157, 123, 20);
		contentPane.add(editCurrentTicketLbl);
		
		editCommentScrollPane = new JScrollPane(editCommentTxtArea);
		editCommentScrollPane.setBounds(18, 255, 1403, 471);
		contentPane.add(editCommentScrollPane);
		
		
		
		editTitleLbl = new JLabel("Title:");
		editTitleLbl.setForeground(Color.WHITE);
		editTitleLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editTitleLbl.setBounds(18, 189, 83, 20);
		contentPane.add(editTitleLbl);
		
		
		
		editCommentsLbl = new JLabel("Comments:");
		editCommentsLbl.setForeground(Color.WHITE);
		editCommentsLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editCommentsLbl.setBounds(18, 223, 105, 20);
		contentPane.add(editCommentsLbl);
		
		editTicketHeaderLbl = new JLabel("Edit Ticket Information");
		editTicketHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		editTicketHeaderLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		editTicketHeaderLbl.setBackground(Color.WHITE);
		editTicketHeaderLbl.setForeground(Color.WHITE);
		editTicketHeaderLbl.setBounds(389, 6, 700, 37);
		contentPane.add(editTicketHeaderLbl);
		
		
		
		editSubmitBtn = new JButton("Submit");
		editSubmitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		editSubmitBtn.setBackground(Color.GRAY);
		editSubmitBtn.setForeground(Color.BLACK);
		editSubmitBtn.setBounds(6, 800, 147, 37);
		contentPane.add(editSubmitBtn);
		
		resolveChkBx = new JCheckBox("Resolve");
		resolveChkBx.setBackground(Color.WHITE);
		resolveChkBx.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		resolveChkBx.setForeground(Color.WHITE);
		resolveChkBx.setBounds(169, 806, 128, 23);
		contentPane.add(resolveChkBx);
		
		viewOnlyLbl = new JLabel("RESOLVED(VIEW ONLY)");
		viewOnlyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		viewOnlyLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		viewOnlyLbl.setForeground(Color.RED);
		viewOnlyLbl.setBounds(589, 55, 300, 30);
		viewOnlyLbl.setVisible(false);
		contentPane.add(viewOnlyLbl);
		
		
		
		
		
		
		
		//action listener waiting for event to fire in thise case upon clicking the submit button in the edit ticket frame
		editSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//this trims the text so it doesn't fill with white space within the database.
				String leftRemovedName = editNameTxtBx.getText().replaceAll("^\\s+", "");
			    String leftRemovedTitle = editTitleTxtBx.getText().replaceAll("^\\s+", "");
			    String commentTrimmed = editCommentTxtArea.getText().trim();
			    
			    
				
			    // resolve ticket check box conditional statements to make sure input is validated before submitting to the database
				
			    if(resolveChkBx.isSelected()) {
			    	int dialogResolve = JOptionPane.showOptionDialog (null,"You are about to resolve this ticket permanently and it will not be able to be edited in the future. Would you like to continue?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,optionsYesNo,optionsYesNo[0]);
			    	if (dialogResolve == JOptionPane.YES_OPTION) {
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
					    	
					    	//connection to add the edited ticket to the database and mark as resolved.
							try (Connection connection = DriverManager.getConnection(url, username, password)) {
								final String status = "Resolved";
							    PreparedStatement stmt=connection.prepareStatement(resolveTicket); 
							    list.remove(selectedIndex);				    
							    stmt.setString(1, leftRemovedName); 
							    stmt.setString(2, leftRemovedTitle);
							    stmt.setString(3, commentTrimmed);
							    stmt.setString(4, status);
							    list.add(selectedIndex, editDateLbl.getText() + " - " + leftRemovedName + " - Ticket Number: " + editCurrentTicketLbl.getText() + " - Title: " + leftRemovedTitle + " - Status: " + status + ".");
							    stmt.executeUpdate();
							    dispose();
						    
						    try {
						        connection.close();
						      } 
						    catch (Exception e1) {
						        e1.printStackTrace();
						      }
						    
							}	 
							catch (SQLException e1) {
								throw new IllegalStateException("Cannot connect the database!", e1);
							}
						
					    }
				    	
			    	}
			    	
			    }
			    
			    
			    // removes the submit button and resolve checkbox when a ticket has been resolved already. Also this will make the boxes uneditable.
			    else if (status.equals("Resolved")) {
				    JOptionPane.showMessageDialog(null,"This ticket has been resolved and cannot be edited. Please open a new ticket." , "Ticket is Resolved", JOptionPane.ERROR_MESSAGE);

					try (Connection connection = DriverManager.getConnection(url, username, password)) {
						Statement stmt = connection.createStatement(); 		
						ResultSet rs = stmt.executeQuery(resetTicketInfo);
						
					while(rs.next() ) {
					    String nameDB = rs.getString(1);
					    String titleDB = rs.getString(2); 
					    String commentDB = rs.getString(3); 
					    editNameTxtBx.setText(nameDB);
					    editTitleTxtBx.setText(titleDB);
					    editCommentTxtArea.setText(commentDB);
					}
					
					rs.close();
				    
				    try {
				        connection.close();
				      } 
				    catch (Exception e1) {
				        e1.printStackTrace();
				      }
				    
					}	 
					
					
					
					catch (SQLException e1) {
						throw new IllegalStateException("Cannot connect the database!", e1);
						}
					
				
			    }
			    
			    
			    // used to save a edited ticket that has a status of "in progress" still.
			    	
			    else {
			    		
			    		int dialogResult = JOptionPane.showOptionDialog (null,"You are about to save changes to the current ticket. Would you like to continue?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,optionsYesNoReset,optionsYesNoReset[0]);
						if(dialogResult == JOptionPane.YES_OPTION){
							
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
								try (Connection connection = DriverManager.getConnection(url, username, password)) {
								    PreparedStatement stmt=connection.prepareStatement(updateTicket); 
								    list.remove(selectedIndex);				    
								    stmt.setString(1, leftRemovedName); 
								    stmt.setString(2, leftRemovedTitle);
								    stmt.setString(3, commentTrimmed);
								    list.add(selectedIndex, editDateLbl.getText() + " - " + leftRemovedName + " - Ticket Number: " + editCurrentTicketLbl.getText() + " - Title: " + leftRemovedTitle + " - Status: In progress.");
								    stmt.executeUpdate();
								    dispose();
							    
							    try {
							        connection.close();
							      } 
							    catch (Exception e1) {
							        e1.printStackTrace();
							      }
							    
								}	 
								catch (SQLException e1) {
									throw new IllegalStateException("Cannot connect the database!", e1);
								}
							
						    }
						}
						if(dialogResult == JOptionPane.CANCEL_OPTION) {
							
							
							try (Connection connection = DriverManager.getConnection(url, username, password)) {
								Statement stmt = connection.createStatement(); 		
								ResultSet rs = stmt.executeQuery(resetTicketInfo);
								
							while(rs.next() ) {
							    String nameDB = rs.getString(1);
							    String titleDB = rs.getString(2); 
							    String commentDB = rs.getString(3); 
							    editNameTxtBx.setText(nameDB);
							    editTitleTxtBx.setText(titleDB);
							    editCommentTxtArea.setText(commentDB);
							}
							
							rs.close();
						    
						    try {
						        connection.close();
						      } 
						    catch (Exception e1) {
						        e1.printStackTrace();
						      }
						    
							}	 
							
							
							
							catch (SQLException e1) {
								throw new IllegalStateException("Cannot connect the database!", e1);
								}
							
						}
						else {
							
						}
						
						
					
			    	}
			    		
			    
			    	
			  }	
				
				
		});
		
	
	}
}


















