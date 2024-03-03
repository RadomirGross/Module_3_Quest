package com.example.quest.servlets;

import com.example.quest.enums.Level;
import com.example.quest.mapper.QuestionMapper;
import com.example.quest.model.Player;
import com.example.quest.model.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession(true);


        Player player = new Player();

        player.setLevel(Level.START_PAGE);

        currentSession.setAttribute("player", player);

        QuestionMapper questionMapper = new QuestionMapper();
        List<Question> questions = questionMapper.JsonToQuestionMap("package.json");

        Map<Level, Question> questionMap = new HashMap<>();
        for (Question question : questions) {
            questionMap.put(question.getCurrentLevel(), question);
        }
        currentSession.setAttribute("questions", questionMap);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}