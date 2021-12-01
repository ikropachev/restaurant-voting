package org.ivan_kropachev.restaurant_voting.web;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.web.dish.AdminDishController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AdminDishServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private AdminDishController adminDishController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        adminDishController = springContext.getBean(AdminDishController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Dish dish = new Dish(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("restaurant_id")),
                Integer.parseInt(request.getParameter("price")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            adminDishController.update(dish, getId(request));
        } else {
            adminDishController.create(dish);
        }
        response.sendRedirect("dishes");
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
                request.setAttribute("dishes", adminDishController.getAll());
                request.getRequestDispatcher("/dishes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
