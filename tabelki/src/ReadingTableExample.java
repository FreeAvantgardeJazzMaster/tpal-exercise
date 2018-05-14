import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadingTableExample {
    public static void main(String[] args){
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

         try{
             Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:db/eav.db");
             statement = connection.createStatement();

             resultSet = statement.executeQuery("select * from Entities");
             while(resultSet.next()){
                 students.add(new Student(Integer.parseInt(resultSet.getString("EntityId")), resultSet.getString("EntityName")));
             }
             resultSet = statement.executeQuery("select * from Attributes");
             while(resultSet.next()){
                 courses.add(new Course(Integer.parseInt(resultSet.getString("AttributeId")), resultSet.getString("AttributeName")));
             }
             resultSet = statement.executeQuery("select * from Attributes");
             while(resultSet.next()){
                 courses.add(new Course(Integer.parseInt(resultSet.getString("AttributeId")), resultSet.getString("AttributeName")));
             }
         }catch(Exception e){
            e.printStackTrace();
         }
    }
}
