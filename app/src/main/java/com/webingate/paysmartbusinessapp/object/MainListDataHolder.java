package com.webingate.paysmartbusinessapp.object;

import java.util.List;

/**
 * Created by Panacea-Soft on 5/9/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MainListDataHolder {
    public String[] mainId;
    public List<String[]> id;
    public List<String[][]> name;


    public MainListDataHolder(String[] mainId, List<String[]> id, List<String[][]> list) {
        this.mainId = mainId;
        this.id = id;
        this.name = list;
    }
}
