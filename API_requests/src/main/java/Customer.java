import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Customer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        List<CustomerDTO> customerList = gson.fromJson(req.getReader(), new TypeToken<List<CustomerDTO>>(){}.getType());

        // Loop through the list and print each customer's details
        for (CustomerDTO customer : customerList) {
            System.out.println(customer.getName());
            System.out.println(customer.getAge());
            System.out.println(customer.getTown());
        }

        // Convert the list back to JSON and send it as a response
        String details = gson.toJson(customerList);
        resp.getWriter().println(details);
    }

}
