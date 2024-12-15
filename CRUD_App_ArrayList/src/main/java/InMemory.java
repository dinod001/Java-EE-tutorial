import java.util.ArrayList;
import java.util.List;

public class InMemory {
   static List<StudentDTO> students=new ArrayList<StudentDTO>();

   static {
        StudentDTO studentDTO=new StudentDTO(1,"dinod",18);
        students.add(studentDTO);
    }

   public static boolean findStudent(StudentDTO student) {
       if (students.contains(student)) {
           return true;
       }
       return false;
   }
    public static boolean addStudent(StudentDTO student) {
       if (findStudent(student)) {
           return false;
       }
        students.add(student);
        return true;
    }
    public static boolean removeStudent(StudentDTO student) {
       if (findStudent(student)) {
           students.remove(student);
       }
       return false;
    }
    public static boolean updateStudent(StudentDTO student) {
        if (findStudent(student)) {
            student.setName(student.getName());
            student.setAge(student.getAge());
            return true;
        }
        return false;
    }
    public static StudentDTO getStudent(int index) {
        for (StudentDTO student : students) {
            if (student.getId() == index) {
                return student;
            }
        }
        return null;
    }
}
