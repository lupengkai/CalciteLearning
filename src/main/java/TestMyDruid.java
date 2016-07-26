import com.tage.calcite.adapter.druid.DruidSchema;
import com.tage.calcite.adapter.druid.DruidSchemaFactory;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by tage on 7/25/16.
 */
public class TestMyDruid {


    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        System.out.println(calciteConnection);
        String broker = "http://10.214.208.59:8082";
        String coordinator = "http://10.214.208.59:8081";
        Map<String, Object> operand = new HashMap<String, Object>();
        operand.put("url", broker);
        operand.put("coordinatorUrl", coordinator);
        SchemaFactory schemaFactory = new DruidSchemaFactory();
        /*Schema schema = schemaFactory.create(calciteConnection.getRootSchema(),
                "default", operand);*/

        Schema schema = new DruidSchema(broker, coordinator, true);

        calciteConnection.getRootSchema().add("default", schema);
        calciteConnection.setSchema("default");

        DatabaseMetaData metaData = calciteConnection.getMetaData();

        ResultSet rs = metaData.getTables(null, null, null, null);
        while (rs.next()) {
            System.out.println(rs.getString("table_name") + " " + rs.getString("table_type"));
        }


        Statement statement = calciteConnection.createStatement();


       rs = statement.executeQuery("select channel from wikiticker limit 20");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        statement.close();
        connection.close();


    }
}
