import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class Student extends HttpServlet {
    Gson gson=new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Adding function
        String header=req.getHeader("content-type");
        if(header.contains("application/json")){
            StudentDTO studentDTO1=gson.fromJson(req.getReader(),StudentDTO.class);
            boolean result=InMemory.addStudent(studentDTO1);
            String message=result?"Successfully added student":"Failed to add student";
            resp.getWriter().println(message);
        }
        //searching function
        int id=Integer.parseInt(req.getParameter("id"));
        StudentDTO studentDTO=InMemory.getStudent(id);
        if(studentDTO==null){
            resp.getWriter().println("Student not found");
        }
        else{
            String message=gson.toJson(studentDTO);
            resp.getWriter().println(message);
        }
    }
}
