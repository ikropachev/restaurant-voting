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
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("restaurantId")),
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
            case "delete":
                int id = getId(request);
                 adminDishController.delete(id);
                response.sendRedirect("dishes");
                break;
            case "create":
            case "update":
                final Dish dish = "create".equals(action) ?
                        new Dish() :
                        adminDishController.get(getId(request));
                request.setAttribute("dish", dish);
                request.getRequestDispatcher("/dishForm.jsp").forward(request, response);
                break;
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
