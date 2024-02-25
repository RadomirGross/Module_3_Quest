package com.example.quest.servlets;

import com.example.quest.enums.Level;
import com.example.quest.model.Player;
import com.example.quest.model.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        Player player = (Player) currentSession.getAttribute("player");
        if (player.getName() == null) {
            player.setName(req.getParameter("userName"));
            currentSession.setAttribute("player", player);
        }

        if (player.getLevel().equals(Level.START_PAGE))
            player.setLevel(Level.LOST_MEMORY);


        Map<Level, Question> questionMap = (Map<Level, Question>) currentSession.getAttribute("questions");


        Question currentQuestion = new Question();
        for (Map.Entry<Level, Question> entrySet : questionMap.entrySet()) {
            if (entrySet.getKey().equals(player.getLevel()))
                currentQuestion = entrySet.getValue();
        }

        currentSession.setAttribute("player", player);
        currentSession.setAttribute("currentQuestion", currentQuestion);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession currentSession = req.getSession();
        Player player = (Player) currentSession.getAttribute("player");
        Level nextLevel = Level.valueOf(req.getParameter("selectedAnswer"));
        player.setLevel(nextLevel);

        resp.sendRedirect("/logic");
    }

}
