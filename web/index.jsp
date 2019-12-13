<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="app.rassel.Point" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.String" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Вторая лабораторная </title>
    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/canvas.css">
    <script src="scripts/jquery-3.4.1.js"></script>
    <script src="scripts/canvas.js" defer></script>
    <script src="scripts/script.js" defer></script>
</head>
<body>
    <header>
        <div class="header">
            <h1 class="center">Лабораторная работа №2</h1>
            <h2 class="center">Программирование интернет-приложений</h2>
            <h3 class="center"> Вариант: 75457 </h3>
            <div style="float: right">
                <p style="text-align: left">
                    Создатель: Бекоев Андрей <br>
                    Преподаватель: Киреев Валерий Юрьевич<br>
                </p>
            </div>
        </div>
    </header>
    <br>
    <br>
    <br>
    <p class="center">
        <!--<img src="images/area.png" alt="Area" width="270px" height="270px"> --> <!--todo create interactive image-->
        <canvas id="areaCanvas" width="400px" height="400px">Ваш браузер не поддерживает данное изображение. Земля пухом!</canvas>
    </p>

    <form action="controller" id="main-form" method="post">
        <p class="center" style="font-weight: 500" id="group">
            Изменения по X: <br>
            <input type="radio" name="x" value="-5"> -5
            <input type="radio" name="x" value="-4"> -4
            <input type="radio" name="x" value="-3"> -3
            <input type="radio" name="x" value="-2"> -2
            <input type="radio" name="x" value="-1"> -1
            <input type="radio" name="x" value="0"> 0
            <input type="radio" name="x" value="1"> 1
            <input type="radio" name="x" value="2"> 2
            <input type="radio" name="x" value="3"> 3

            <br> Изменения по Y: <br>
            <input type="text" id="textfield" name="y" placeholder="(-5;5)">


            <br> Изменения радиуса R: <br>
                <input type="checkbox" name="radius" class="R" value="1"> 1
                <input type="checkbox" name="radius" class="R" value="1.5"> 1.5
                <input type="checkbox" name="radius" class="R" value="2"> 2
                <input type="checkbox" name="radius" class="R" value="2.5"> 2.5
                <input type="checkbox" name="radius" class="R" value="3"> 3

            <br>

            <!-- todo choose radius -->
            <input type="submit" value="Проверить">

        <p id="time" class="center"></p>
        </p>
    </form>
    <form id="hiddenForm" action="controller" method="post">
        <input type="hidden" id="hiddenX" name="x" value="">
        <input type="hidden" id="hiddenY" name="y" value="">
        <input type="hidden" id="hiddenR" name="radius" value="">
    </form>
    <br>
    <script>
        $('#group input:checkbox').click(function(){
            if ($(this).is(':checked')) {
                $('#group input:checkbox').not(this).prop('checked', false);
            }
        });
    </script>
    <%
        //todo create table with past points
        int pointNumber = 0;
        ServletContext servletContext = request.getServletContext();

    %>

    <p style="text-align: center">
        <table id="table" border=1px width="100%" cellpadding="5">
            <tr>
                <td>Предыдущие точки</td>
            </tr>
            <%
                while (servletContext.getAttribute("point" + pointNumber) != null) {
                    Float x = ((Point)servletContext.getAttribute("point" + pointNumber)).getX();
                    Float y = ((Point)servletContext.getAttribute("point" + pointNumber)).getY();
                    Float r = ((Point)servletContext.getAttribute("point" + pointNumber)).getRadius();
                    out.write("<tr>");

                    out.write("<td>");
                    out.write(x.toString());
                    out.write("</td>");

                    out.write("<td>");
                    out.write(y.toString());
                    out.write("</td>");

                    out.write("<td>");
                    out.write(r.toString());
                    out.write("</td>");

                    out.write("<td>");
                    out.write(((((Point)servletContext.getAttribute("point" + pointNumber++)).isInside())? "попала" : "не попала"));
                    out.write("</td>");

                    out.write("</tr>");

                }
            %>
        </table>
    </p>

    <script type="text/javascript">

        function updateTime() {
            let now = new Date();
            timeField.innerHTML = "Текущее время: " + now.toString();
        }
        let timeField = document.getElementById("time");
        updateTime();
        setInterval(updateTime, 1000);
    </script>
</body>
</html>