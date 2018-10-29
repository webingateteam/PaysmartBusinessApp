package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DriverDetailsResponse {

	@SerializedName("Table")
	private List<DriverDetailsTableItem> table;

	@SerializedName("Table2")
	private List<DriverDetailsTable2Item> table2;

	@SerializedName("Table3")
	private List<Object> table3;

	@SerializedName("Table4")
	private List<DriverDetailsTable4Item> table4;

	@SerializedName("Table5")
	private List<Object> table5;

	@SerializedName("Table1")
	private List<DriverDetailsTable1Item> table1;

	public void setTable(List<DriverDetailsTableItem> table){
		this.table = table;
	}

	public List<DriverDetailsTableItem> getTable(){
		return table;
	}

	public void setTable2(List<DriverDetailsTable2Item> table2){
		this.table2 = table2;
	}

	public List<DriverDetailsTable2Item> getTable2(){
		return table2;
	}

	public void setTable3(List<Object> table3){
		this.table3 = table3;
	}

	public List<Object> getTable3(){
		return table3;
	}

	public void setTable4(List<DriverDetailsTable4Item> table4){
		this.table4 = table4;
	}

	public List<DriverDetailsTable4Item> getTable4(){
		return table4;
	}

	public void setTable5(List<Object> table5){
		this.table5 = table5;
	}

	public List<Object> getTable5(){
		return table5;
	}

	public void setTable1(List<DriverDetailsTable1Item> table1){
		this.table1 = table1;
	}

	public List<DriverDetailsTable1Item> getTable1(){
		return table1;
	}

	@Override
 	public String toString(){
		return 
			"DriverDetailsResponse{" +
			"table = '" + table + '\'' + 
			",table2 = '" + table2 + '\'' + 
			",table3 = '" + table3 + '\'' + 
			",table4 = '" + table4 + '\'' + 
			",table5 = '" + table5 + '\'' + 
			",table1 = '" + table1 + '\'' + 
			"}";
		}
}