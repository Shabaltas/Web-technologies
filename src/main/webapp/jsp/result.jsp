<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2019
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <table>
        <caption>Banks by ${parserType}</caption>
        <tr>
            <th rowspan="2">Whom</th>
            <th rowspan="2">AccountID</th>
            <th rowspan="2">Type</th>
            <th rowspan="2">Bank</th>
            <th rowspan="2">Profitability</th>
            <th rowspan="2">Country</th>
            <th rowspan="2">Date</th>
            <th rowspan="2">Duration</th>
            <th rowspan="2">Amount</th>
            <th colspan="3">Depositor/Contact person</th>
            <th rowspan="2">Company</th>
            <th rowspan="2">Company location</th>
        </tr>
        <tr>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
        </tr>
        <c:forEach var="elem" items="${banks}" varStatus="status">
            <tr>
                <c:if test="${elem['class'].simpleName eq 'DepositForJuridicalPerson'}">
                    <td>J</td>
                    <td><c:out value="${elem.accountID}" /></td>
                    <td><c:out value="${elem.type}" /></td>
                    <td><c:out value="${elem.name}" /></td>
                    <td><c:out value="${elem.profitability}" />%</td>
                    <td><c:out value="${elem.country}" /></td>
                    <td><c:out value="${elem.dateOfDeposit}" /></td>
                    <td><c:out value="${elem.duration}" /></td>
                    <td><c:out value="${elem.amount}" /></td>
                    <td><c:out value="${elem.contactPerson.surname}" /></td>
                    <td><c:out value="${elem.contactPerson.name}" /></td>
                    <td><c:out value="${elem.contactPerson.patronymic}" /></td>
                    <td><c:out value="${elem.companyName}" /></td>
                    <td><c:out value="${elem.companyLocation}" /></td>
                </c:if>
                <c:if test="${elem['class'].simpleName eq 'DepositForNaturalPerson'}">
                    <td>N</td>
                    <td><c:out value="${elem.accountID}" /></td>
                    <td><c:out value="${elem.type}" /></td>
                    <td><c:out value="${elem.name}" /></td>
                    <td><c:out value="${elem.profitability}" />%</td>
                    <td><c:out value="${elem.country}" /></td>
                    <td><c:out value="${elem.dateOfDeposit}" /></td>
                    <td><c:out value="${elem.duration}" /></td>
                    <td><c:out value="${elem.amount}" /></td>
                    <td><c:out value="${elem.depositorName.surname}" /></td>
                    <td><c:out value="${elem.depositorName.name}" /></td>
                    <td><c:out value="${elem.depositorName.patronymic}" /></td>
                    <td> - </td>
                    <td> - </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <h4>*"J" for deposits for juridical persons, "N" for deposits for natural persons</h4>
</section>
</body>
</html>