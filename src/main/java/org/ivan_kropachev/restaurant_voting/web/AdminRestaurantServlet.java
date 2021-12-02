package org.ivan_kropachev.restaurant_voting.web;

import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.web.restaurant.AdminRestaurantController;
import org.ivan_kropachev.restaurant_voting.web.user.AdminUserController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class AdminRestaurantServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private AdminRestaurantController adminRestaurantController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        adminRestaurantController = springContext.getBean(AdminRestaurantController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Restaurant restaurant = new Restaurant(
                //Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            adminRestaurantController.update(restaurant, getId(request));
        } else {
            adminRestaurantController.create(restaurant);
        }
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                adminRestaurantController.delete(id);
                response.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant() :
                        adminRestaurantController.get(getId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("restaurants", adminRestaurantController.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
