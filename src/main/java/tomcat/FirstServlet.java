package tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FirstServlet extends HttpServlet {
    private final List<RequestInfo> requestsInfos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        RequestInfo requestInfo = new RequestInfo(req.getRemoteAddr(), req.getHeader("User-Agent"),dateTimeFormatter.format(LocalDateTime.now()));
        requestsInfos.add(requestInfo);
        req.setAttribute("requestInfo", requestInfo);
        req.getSession().setAttribute("requests", new ArrayList<>(requestsInfos));
        req.getRequestDispatcher("data.jsp").forward(req, resp);
    }
}
