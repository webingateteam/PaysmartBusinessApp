package com.webingate.paysmartbusinessapp.repository.finance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webingate.paysmartbusinessapp.object.FinanceSummary;

import java.util.List;

/**
 * Created by Panacea-Soft on 7/17/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class TransactionSummaryRepository {
    public static List<FinanceSummary> getSummaryList() {
        return new Gson().fromJson(json, new TypeToken<List<FinanceSummary>>() {}.getType());
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"title\": \"Savings\",\n" +
            "    \"amount\": \"$5300.00\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Budget\",\n" +
            "    \"amount\": \"$6000.00\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Expense\",\n" +
            "    \"amount\": \"$4300.00\"\n" +
            "  }\n" +
            "]";
}
