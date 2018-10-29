package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PendingDocsResponce {

	@SerializedName("Table")
	private List<PendingDocsTableItem> table;

	@SerializedName("Table1")
	private List<PendingDocsTable1Item> table1;

	public void setTable(List<PendingDocsTableItem> table){
		this.table = table;
	}

	public List<PendingDocsTableItem> getTable(){
		return table;
	}

	public void setTable1(List<PendingDocsTable1Item> table1){
		this.table1 = table1;
	}

	public List<PendingDocsTable1Item> getTable1(){
		return table1;
	}

	@Override
 	public String toString(){
		return 
			"PendingDocsResponce{" +
			"table = '" + table + '\'' + 
			",table1 = '" + table1 + '\'' + 
			"}";
		}
}