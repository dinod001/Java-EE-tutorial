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
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String age=req.getParameter("age");
        String address=req.getParameter("address");

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
