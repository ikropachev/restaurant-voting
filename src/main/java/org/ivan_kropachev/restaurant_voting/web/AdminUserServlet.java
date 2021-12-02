package org.ivan_kropachev.restaurant_voting.web;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.web.user.AdminUserController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class AdminUserServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private AdminUserController adminUserController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        adminUserController = springContext.getBean(AdminUserController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("password"),
                Boolean.parseBoolean(request.getParameter("enabled")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            adminUserController.update(user, getId(request));
        } else {
            adminUserController.create(user);
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            //case "delete":
            //    int id = getId(request);
           //     mealController.delete(id);
            //    response.sendRedirect("meals");
            //    break;
            //case "create":
            //case "update":
            //    final Meal meal = "create".equals(action) ?
            //            new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
            //            mealController.get(getId(request));
            //    request.setAttribute("meal", meal);
            //    request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
            //    break;
            //case "filter":
            //    LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            //    LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            //    LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            //    LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            //    request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
            //    request.getRequestDispatcher("/meals.jsp").forward(request, response);
            //    break;
            case "all":
            default:
                request.setAttribute("users", adminUserController.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
