package startup;

import org.apache.calcite.jdbc.CalciteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by tage on 7/16/16.
 */
public class Startup {
    public static class HrSchema {
        public final Employee[] emps = null;
        public final Department[] depts = null;
    }


    public static void main(String[] args) throws Exception{



        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        Connection connection = DriverManager.getConnection("jdbc:calcite:schemaType=JDBC;schema.jdbcUser=root;schema.jdbcPassword=0715;schema.jdbcUrl=jdbc:mysql://localhost/hr", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        Class.forName("com.mysql.jdbc.Driver");
      /*  BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost");
        dataSource.setUsername("root");
        dataSource.setPassword("0715");
        JdbcSchema.create(calciteConnection.getRootSchema(), "name", dataSource,
                null, "hr");*/
        Statement statement = calciteConnection.createStatement();
      /*  ResultSet resultSet = statement.executeQuery(
                "select d.deptno, min(e.empid)\n"
                        + "from hr.emps as e\n"
                        + "join hr.depts as d\n"
                        + "  on e.deptno = d.deptno\n"
                        + "group by d.deptno\n"
                        + "having count(*) > 1");*/
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM emps");
        resultSet.next();
        System.out.println(resultSet.getInt(1));
        System.out.println(resultSet.getInt(2));
        resultSet.close();
        statement.close();
        connection.close();
    }




}
