package utilities;

import pages.CommonPageElements;

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
        String jdbcUrl = "jdbc:sqlite:datahawk.db";
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
        String jdbcUrl = "jdbc:sqlite:datahawk.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            //System.out.println("SQLITE DB connected");

            Statement statement = connection.createStatement();
            String expectedData = "SELECT * FROM products WHERE title LIKE '%pro%' and price>0 ORDER BY review DESC LIMIT 1";
            ResultSet result = statement.executeQuery(expectedData);
            while (result.next()) {
                Map<String, Object> allProducts = new HashMap<>();
                allProducts.put("id", result.getInt("id"));
                allProducts.put("title",result.getString("title"));
                allProducts.put("review", result.getString("review"));
                allProducts.put("price", result.getString("price"));
                allProducts.put("date", result.getString("date"));
                expectedProductInfo.add(allProducts);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return expectedProductInfo;
    }


}
