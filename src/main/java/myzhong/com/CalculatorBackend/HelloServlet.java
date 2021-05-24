package myzhong.com.CalculatorBackend;

import com.google.gson.Gson;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "calculate", value = "/calculate")
public class HelloServlet extends HttpServlet {
    private String result;
    private Calculator calculator;
    private Gson gson = new Gson();

    public void init() {
        result = "{\"display\":\"0\",\"plus\":false,\"subtract\":false,\"multiply\":false,\"divide\":false}";
        calculator = new Calculator();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Process key-press from frontend
        String inputs = request.getParameter("input");
        if(inputs != null && !inputs.isEmpty()) {
            CalculatorItems output = calculator.processInput(inputs);
            result = gson.toJson(output);
        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println(result);
        out.close();
    }

    public void destroy() {
    }
}