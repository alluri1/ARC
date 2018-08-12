package ARC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @author Mounika Alluri
 * @author Brienna Herold
 */
public class Data {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/app_reviews?useUnicode=true&useJDB..." +
            "CCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"; //replace this with your db

    //replace with your corresponding credentials
    static final String USER = "root";
    static final String PASS = "neirage";

    public static ArrayList<Row> dataRows = new ArrayList<Row>();
    public String content="";
    
    public String matrixString;

    public void init() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM review";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String appName = rs.getString("app_name");
                String versionNum = rs.getString("version");
                String userName = rs.getString("userid");
                String date = rs.getString("date");
                int rating = rs.getInt("rating");
                String title = rs.getString("title");
                String text = rs.getString("text");
                String infoGiv = rs.getString("has_information_giving");
                String infoSeek = rs.getString("has_information_seeking");
                String featureReq = rs.getString("has_feature_Request");
                String bugReport = rs.getString("has_bug_report");
                String sentScore = rs.getString("sentiment_score");
                Row r = new Row(id, appName, versionNum, userName, date, rating, title, text, infoGiv, infoSeek, featureReq, bugReport, sentScore);
                content+=text;
                dataRows.add(r);
            }

            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        } //end try

    }

    /**
     * Ignore this method. You can delete it. It was for data exploration and is very disorganized.
     *
     */
    public void showSomeData() {
    	matrixString = new String();
    	
    	
     // Show number of reviews
     int numOfReviews = 0;
       for (Row dataRow : dataRows) {
         numOfReviews++;
         
       }
       matrixString += "\nNumber of reviews: " + numOfReviews + "\n";

       // Show info distinct app names reviewed,
       // num of apps,
       // and how many reviews each app has
       HashMap<String, Integer> appReviewCount = new HashMap<String, Integer>();
       ArrayList<String> reviews = new ArrayList<String>();
       // Gather info for Sheep-O-block
       int infoGivs = 0;
       int infoSeeks = 0;
       int featRequests = 0;
       int hasBugs = 0;
       int empties = 0;
       int sheepDupes = 0;
       int nonInformative = 0;
       
       for (Row dataRow : dataRows) {
         String name = dataRow.getAppName();
         if (dataRow.getInfoGiv() != null) {
             if (!appReviewCount.containsKey(name)) {
            	 appReviewCount.put(name, 1);
             } else {
               appReviewCount.put(name, appReviewCount.get(name) + 1);
             }
         }

         
        	 if (dataRow.getInfoGiv() == null) {
        		 nonInformative++;
        	 } else {
            	switch (dataRow.getInfoGiv()) {
         	 		case "0":
         	 			infoGivs++;
         	 			break;
         	 		case "1":
         	 			infoSeeks++;
         	 			break;
         	 		case "2":
         	 			featRequests++;
         	 			break;
         	 		case "3":
         	 			hasBugs++;
         	 			break;
            	}
        	 }
        	 
         String text = dataRow.getText().trim();
         if (!reviews.contains(text)) {
           reviews.add(text);
         } else {
           sheepDupes++;
         }
           if (text.equals("")) {
               empties++;
             }
       }
       
       matrixString += "\nNumber of documents in info_giving class: " + infoGivs + "\n";
       matrixString += "Number of documents in info_seeking class: " + infoSeeks + "\n";
       matrixString += "Number of documents in feature_requests class: " + featRequests + "\n";
       matrixString += "Number of documents in bug_reports class: " + hasBugs + "\n";
       matrixString += "Number of non-informative documents: " + nonInformative + "\n";
       matrixString += "Maximum number of training documents: " + (infoGivs + infoSeeks + featRequests + hasBugs) + "\n";
      
       matrixString += "\nNumber of empty documents: " + empties + "\n";
       matrixString += "Number of duplicate documents: " + sheepDupes + "\n";
       
       matrixString += "\nNum of apps reviewed with informative reviews: " + appReviewCount.size() + "\n";
       for (String appName : appReviewCount.keySet()) {
        	 matrixString += appName + ": " + appReviewCount.get(appName) + "\n";
           System.out.println(appName + ": " + appReviewCount.get(appName));
         }
    }
    
    public ArrayList<String> getDocs() {
    	int nulls = 0;
    	ArrayList<String> docs = new ArrayList<String>();
    	for (int i = 0; i < dataRows.size(); i++) {
    		if (dataRows.get(i).getInfoGiv() == null) {
    			nulls++;
    		} else {
    			docs.add(dataRows.get(i).getText());
    		}
    	}
    	System.out.println("Total documents: " + docs.size());
    	System.out.println("Unlabeled documents not added to myDocs: " + nulls);
    	return docs;
    }
    
    public ArrayList<Integer> getLabels() {
    	int nulls = 0;
    	ArrayList<Integer> labels = new ArrayList<Integer>();
    	for (int i = 0; i < dataRows.size(); i++) {
    		if (dataRows.get(i).getInfoGiv() == null) {
    			nulls++;
    		} else {
    			labels.add(Integer.parseInt(dataRows.get(i).getInfoGiv()));
    		}
    	}
    	System.out.println("Total labels: " + labels.size());
    	System.out.println("Unlabeled documents not added to myDocs: " + nulls);
    	return labels;
    }

}
