package lv.etaxi.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Genady Zalesky on 29.03.2016
 */

@WebServlet(name = "ServletAdminEditTaxiProfile", urlPatterns = {"/admin/editProfileTaxi"})
public class ServletAdminEditTaxiProfile extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
