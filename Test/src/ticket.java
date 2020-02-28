import javax.swing.JOptionPane;

public class ticket {
	private int ticketNum;
	private String status;
	private String date;
	private String name;
	private String title;
	private String comment;

	ticket(int ticketNum, String status, String date, String name, String title, String comment) {
		this.setTicketNum(ticketNum);
		this.status = "In Progress";
		this.setDate(date);
		this.setName(name);
		this.setTitle(title);
		this.setComment(comment);
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

}
