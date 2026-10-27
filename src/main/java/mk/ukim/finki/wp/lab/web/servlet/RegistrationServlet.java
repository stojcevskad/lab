package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private final RegistrationService registrationService;
    private final SpringTemplateEngine templateEngine;

    public RegistrationServlet(RegistrationService registrationService, SpringTemplateEngine templateEngine) {
        this.registrationService = registrationService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String workshopTitle = req.getParameter("workshopTitle");
        String name = req.getParameter("participantName");
        String email = req.getParameter("participantEmail");

        try {
            int numSeats = Integer.parseInt(req.getParameter("numSeats"));
            Registration registration = registrationService.register(workshopTitle, name, email, numSeats);

            IWebExchange exchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);
            WebContext context = new WebContext(exchange);
            context.setVariable("registration", registration);
            context.setVariable("ipAddress", req.getRemoteAddr());
            context.setVariable("userAgent", req.getHeader("User-Agent"));

            resp.setContentType("text/html;charset=UTF-8");
            templateEngine.process("registrationConfirmation.html", context, resp.getWriter());
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/?errorMessage="
                    + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        }
    }
}