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

        when(request.getSession(true)).thenReturn(session); // Модифицировано для создания новой сессии
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        when(config.getServletContext()).thenReturn(context);

        try {
            logicServlet.init(config); // Инициализация сервлета перед тестом
        } catch (ServletException e) {
            e.printStackTrace(); // Обработка исключения или выброс вверх
        }
    }

    @Test
    public void testDoGet() throws Exception {
        // Подготовка тестовых данных
        Player player = new Player();
        player.setLevel(Level.START_PAGE);

        Map<Level, Question> questions = new HashMap<>();
        questions.put(Level.START_PAGE, new Question());

        // Установка состояния сессии
        when(session.getAttribute("player")).thenReturn(player);
        when(session.getAttribute("questions")).thenReturn(questions);

        // Вызов метода сервлета
        logicServlet.doGet(request, response);

        // Проверка корректности установленных атрибутов
        verify(session, times(2)).setAttribute(eq("player"), any(Player.class));
        verify(session, times(1)).setAttribute(eq("currentQuestion"), any(Question.class));

        // Проверка диспетчеризации
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // Создаем макеты для HttpServletRequest, HttpServletResponse и HttpSession
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Создаем экземпляр LogicServlet
        LogicServlet logicServlet = new LogicServlet();

        // Устанавливаем макет HttpSession для запроса
        when(request.getSession()).thenReturn(session);

        // Устанавливаем атрибут "player" в сессию
        Player player = new Player(); // Создайте экземпляр Player по вашим требованиям
        when(session.getAttribute("player")).thenReturn(player);

        // Устанавливаем поведение для метода getParameter
        when(request.getParameter("selectedAnswer")).thenReturn("LOST_MEMORY");

        // Вызываем метод doPost
        logicServlet.doPost(request, response);

        // Проверяем, что метод sendRedirect был вызван
        verify(response).sendRedirect("/logic");
    }
}
