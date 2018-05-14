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
        int max = 0;


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
             for (Course course : courses)
                 if (course.getName().length() > max)
                     max = course.getName().length();
             System.out.print("          |");
             for(Course course : courses) {
                 for (int i = 0; i < max - course.getName().length(); i++)
                     System.out.print(" ");
                 System.out.print(course.getName() + "|");
             }
             System.out.println("");
             for(Student student : students){
                 System.out.print(student.getName() + "|");
                 for(Course course : courses){
                     resultSet = statement.executeQuery("select Value from 'Values' where EntityId = " + student.getId() + " and AttributeId = " + course.getId());
                     for (int i=0; i < max - 3; i++)
                        System.out.print(" ");
                     String s;
                     if (!resultSet.isBeforeFirst())
                         System.out.print("   ");
                     while(resultSet.next()) {
                         System.out.print(resultSet.getString("Value"));
                     }
                     System.out.print("|");
                 }
                 System.out.println("");
             }
         }catch(Exception e){
            e.printStackTrace();
         }
    }
}
