package com.example.quest.servlets;

import com.example.quest.enums.Level;
import com.example.quest.model.Player;
import com.example.quest.model.Question;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class LogicServletTest {

    private LogicServlet logicServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletConfig config;
    private ServletContext context;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        logicServlet = new LogicServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        config = Mockito.mock(ServletConfig.class);
        context = Mockito.mock(ServletContext.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);

        when(request.getSession(true)).thenReturn(session);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        when(config.getServletContext()).thenReturn(context);

        try {
            logicServlet.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoGet() throws Exception {

        Player player = new Player();
        player.setLevel(Level.START_PAGE);

        Map<Level, Question> questions = new HashMap<>();
        questions.put(Level.START_PAGE, new Question());


        when(session.getAttribute("player")).thenReturn(player);
        when(session.getAttribute("questions")).thenReturn(questions);


        logicServlet.doGet(request, response);


        verify(session, times(2)).setAttribute(eq("player"), any(Player.class));
        verify(session, times(1)).setAttribute(eq("currentQuestion"), any(Question.class));


        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);


        LogicServlet logicServlet = new LogicServlet();


        when(request.getSession()).thenReturn(session);


        Player player = new Player(); // Создайте экземпляр Player по вашим требованиям
        when(session.getAttribute("player")).thenReturn(player);


        when(request.getParameter("selectedAnswer")).thenReturn("LOST_MEMORY");


        logicServlet.doPost(request, response);


        verify(response).sendRedirect("/logic");
    }
}
