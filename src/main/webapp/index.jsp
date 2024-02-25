<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.quest.enums.Level" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>

    <style>
        body {
            background-color: lightgrey; /* Цвет фона для всей страницы */
        }
        h1 {
            font-family: Arial, sans-serif;
            font-size: 24px;
            color: #333;
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            margin: 100px 0;}
            & h2 {
                font-size: 20px; /* Размер шрифта */
                color: #333; /* Цвет текста */
                margin-bottom: 15px; /* Отступ снизу */
                text-align: center; /* Выравнивание по центру */
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* Тень текста */
            }
            & label {
                font-size: 16px; /* размер шрифта */
                color: #555; /* цвет текста */
                margin-bottom: 5px; /* отступ снизу */
                display: block; /* делаем элемент блочным, чтобы можно было применить отступы */
            }
            & h3 {
                font-family: Arial, sans-serif;
                font-size: 36px;
                color: firebrick;
                text-align: center;
                text-shadow: 3px 3px 3px rgba(0, 0, 0, 1);
                margin: 50px 0;
        }
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="static/main.css" rel="stylesheet">

    <title>Module 3 Quest</title>
</head>
<body>
<h1>Добро пожаловать в текстовый квест!</h1>

<c:if test="${player.getLevel() == Level.START_PAGE}">

<form action="/logic" method="get">
   <h1> <label for="start">Введите ваше имя:</label>
    <input type="text" id="start" name="userName" required>
    <button type="submit">Начать квест</button></h1>
</form>
</c:if>

<c:if test="${player.getLevel() == Level.LOSE_PAGE}">

    <form action="/restart" method="get">
        <h3>${player.getName()}, инопланетяне вас погубили.</h3>
        <h3>Нам очень жаль.</h3>
        <h3><button type="submit">RESTART GAME</button></h3>
    </form>
</c:if>

<c:if test="${player.getLevel() == Level.WIN_PAGE}">

    <form action="/restart" method="get">
        <h3>${player.getName()}, вы добрались домой.</h3>
        <h3>Мы за вас рады.</h3>
        <div style="display: flex; justify-content: center;">
            <img src="/resources/img.png" alt="альтернативный_текст" style="max-width: 80%; height: auto;">
        </div>
           <h3><button type="submit">RESTART GAME</button></h3>
    </form>
</c:if>

<c:if test="${player.getLevel() == Level.EGG_PAGE}">

    <form action="/restart" method="get">
        <h3>${player.getName()}, вы дрались на грани своих возможностей и смогли победить инопланетянина.</h3>
        <h3>Вы добрались домой. Но получили значительный урон и стали стендапером!!!</h3>
        <h3>Мы за вас всё ещё рады.</h3>
        <h3> <button type="submit">RESTART GAME</button></h3>
    </form>
</c:if>

<c:if test="${player.getLevel() != Level.START_PAGE}">

    <h1>${currentQuestion.getQuestionText()}</h1>
    <form action="/logic" method="post">
        <div class="button-container">
            <c:forEach var="answer" items="${currentQuestion.getAnswers()}">
                <button type="submit" name="selectedAnswer" value="${answer.getNextLevel()}">${answer.getAnswerText()}</button><br>
            </c:forEach>
        </div>
    </form>
</c:if>


</body>
</html>
