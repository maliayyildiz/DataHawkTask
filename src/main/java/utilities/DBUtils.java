package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    public static void Database_write(int id, String title, String price, String review, LocalDateTime Date) {
        String jdbcUrl = "jdbc:sqlite:/Users/ayyildiz/IdeaProjects/DataHawkTask/amazonData.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            //System.out.println("SQLITE DB connected");
            String data = "INSERT INTO products VALUES ('" + id + "','" + title + "','" + review + "','" + price + "','" + Date + "')";
            Statement statement = connection.createStatement();
            statement.executeQuery(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<Map<String, Object>> Database_getSpecificData() {
        List<Map<String, Object>> expectedProductInfo = new ArrayList<>();
        String jdbcUrl = "jdbc:sqlite:/Users/ayyildiz/IdeaProjects/DataHawkTask/amazonData.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            //System.out.println("SQLITE DB connected");

            Statement statement = connection.createStatement();
            String expectedData = "SELECT * FROM products WHERE title LIKE '%pro%' and price>0 ORDER BY review DESC LIMIT 1";
            ResultSet result = statement.executeQuery(expectedData);
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String review = result.getString("review");
                String price = result.getString("price");
                String date = result.getString("date");
                Map<String, Object> allProducts = new HashMap<>();
                allProducts.put("id", id);
                allProducts.put("title", title);
                allProducts.put("review", review);
                allProducts.put("price", price);
                allProducts.put("date", date);
                expectedProductInfo.add(allProducts);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return expectedProductInfo;
    }


}
