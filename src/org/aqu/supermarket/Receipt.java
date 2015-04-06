package org.aqu.supermarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representation of the receipt.
 * 
 * @author aqu
 *
 */
public class Receipt {
	
	private Date date;
	private List<String> lines;
	private int id; 
	
	public Receipt(int id) {
		date = new Date();
		lines = new ArrayList<String>();
		this.id = id;
	}
	
	public void addLine(String line) {
		lines.add(line);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Receipt id: ")
		.append(id).append("\n")
		.append("Date: " + date).append("\n");
		
		for (String line: lines) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
}
