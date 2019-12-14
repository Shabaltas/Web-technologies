<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2019
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>LANGUAGE</title>
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <section>
        <h3>Language/Язык/Sprache</h3>
        <section>

            <form action="parsing" method="get">
                <input type="text" name="language" hidden="true" value="ENGLISH">
                <input type="submit" value="English">
            </form>
            <form action="parsing" method="get">
                <input type="text" name="language" hidden="true" value="RUSSIAN">
                <input type="submit" value="Русский ">
            </form>
            <form action="parsing" method="get">
                <input type="text" name="language" hidden="true" value="DEUTSCHE">
                <input type="submit" value="Deutsche">
            </form>
        </section>
    </section>
  </body>
</html>
