package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VehicleDetailsResponse {

	@SerializedName("Table")
	private List<VehicleDetailsTableItem> table;

	@SerializedName("Table2")
	private List<VehicleDetailsTable2Item> table2;

	@SerializedName("Table3")
	private List<VehicleDetailsTable3Item> table3;

	@SerializedName("Table1")
	private List<VehicleDetailsTable1Item> table1;

	public void setTable(List<VehicleDetailsTableItem> table){
		this.table = table;
	}

	public List<VehicleDetailsTableItem> getTable(){
		return table;
	}

	public void setTable2(List<VehicleDetailsTable2Item> table2){
		this.table2 = table2;
	}

	public List<VehicleDetailsTable2Item> getTable2(){
		return table2;
	}

	public void setTable3(List<VehicleDetailsTable3Item> table3){
		this.table3 = table3;
	}

	public List<VehicleDetailsTable3Item> getTable3(){
		return table3;
	}

	public void setTable1(List<VehicleDetailsTable1Item> table1){
		this.table1 = table1;
	}

	public List<VehicleDetailsTable1Item> getTable1(){
		return table1;
	}

	@Override
 	public String toString(){
		return 
			"VehicleDetailsResponse{" +
			"table = '" + table + '\'' + 
			",table2 = '" + table2 + '\'' + 
			",table3 = '" + table3 + '\'' + 
			",table1 = '" + table1 + '\'' + 
			"}";
		}
}