import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class student extends HttpServlet {

    List<studentDTO>list=new ArrayList();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentDetails=" ";
        int id=Integer.parseInt(req.getParameter("id"));
        for (studentDTO dto : list) {
            if (dto.getId() == id) {
                studentDetails = dto.toString();
                break;
            }
        }
            studentDetails=studentDetails.isEmpty() ? "No student found" :studentDetails;
            resp.getWriter().write(studentDetails);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean studentFound=false;
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String age=req.getParameter("age");
        String address=req.getParameter("address");

        for (studentDTO dto : list) {
            if(dto.getId()==Integer.parseInt(id)){
               studentFound=true;
               break;
            }
        }

        if(!studentFound){
            int student_id=Integer.parseInt(id);
            int student_age=Integer.parseInt(age);

            studentDTO studentDTO=new studentDTO();
            studentDTO.setId(student_id);
            studentDTO.setName(name);
            studentDTO.setAddress(address);
            studentDTO.setAge(student_age);

            boolean isAdded =list.add(studentDTO);

            resp.getWriter().println("student added "+isAdded);
        }
        else{
            resp.getWriter().println("student found with same ID,can't add");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean deleted=false;
        int id=Integer.parseInt(req.getParameter("id"));
        for (studentDTO dto : list) {
            if(dto.getId()==id){
                deleted=true;
                list.remove(dto);
                break;
            }
        }
        if(deleted){
            resp.getWriter().println("student deleted");
        }
        else{
            resp.getWriter().println("student not found");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean studentFound=false;
        int id=Integer.parseInt(req.getParameter("id"));

        for (studentDTO dto : list) {
            if(dto.getId()==id){
                studentFound=true;

                String name=req.getParameter("name");
                int age=Integer.parseInt(req.getParameter("age"));
                String address=req.getParameter("address");

                dto.setName(name);
                dto.setAge(age);
                dto.setAddress(address);
                break;
            }
        }
        String result=studentFound?"Student details updated successfully":"Student not found, update failed";
        resp.getWriter().write(result);
    }
}
