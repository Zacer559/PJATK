import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(
        urlPatterns = {
                "/add"
        },
        initParams = {
                @WebInitParam(name = "add", value = "", description = "Adding two values")
        })
public class Add extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private static boolean isParsable(String str) {

        return str.matches("-?(0|[1-9]\\d*)");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        String parameter1 = request.getParameter("x");
        String parameter2 = request.getParameter("y");
        if (isParsable(parameter1) && isParsable(parameter2)) {
            int x = Integer.parseInt(parameter1);
            int y = Integer.parseInt(parameter2);
            int sum = x + y;
            writer.write("<b><font color='green'>You have entered correct data! " +
                    "<br/> The result using GET is: " + sum +
                    "</font><br/></b>" +
                    "<a href=http://localhost:8080/TPO3/>\n" +
                    "   <button>Go back</button>\n" +
                    "</a>");
        } else {
            writer.write("<meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8080/TPO3\" />\n" +
                    "\n<b><font color='red'>This data is not a valid input. " +
                    "Please input two numbers. Try Again! <br/> " +
                    "Redicting..." +
                    "</font><br/></b>");

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        String parameter1 = request.getParameter("x");
        String parameter2 = request.getParameter("y");
        if (isParsable(parameter1) && isParsable(parameter2)) {
            int x = Integer.parseInt(parameter1);
            int y = Integer.parseInt(parameter2);
            int sum = x + y;
            writer.write("<b><font color='green'>You have entered correct data! " +
                    "<br/> The result using POST is: " + sum +
                    "</font><br/></b>" +
                    "<a href=/TPO3/>\n" +
                    "   <button>Go back</button>\n" +
                    "</a>");

        } else {

            writer.write("<meta http-equiv=\"refresh\" content=\"3;url=TPO3\" />\n" +
                    "\n<b><font color='red'>This data is not a valid input. " +
                    "Please input two numbers. Try Again! <br/> " +
                    "Redicting..." +
                    "</font><br/></b>");

        }

    }
}
