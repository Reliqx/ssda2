<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="value" rtexprvalue="true" %>
<%@attribute name="options" type="String[]" required="true" rtexprvalue="true" %>
<%@attribute name="labels" type="String[]" required="false" rtexprvalue="true" %>
<%@attribute name="name" required="true" %>
<c:if test="${empty labels}">
    <c:set var="labels" value="${options}"/>
</c:if>
<c:set var="i" value="0"/>
<c:forEach var="option" items="${options}">
    <input type="radio" name="${name}"  value="${option}" ${(option == value)?"checked":""}>${labels[i]}&nbsp;
    <c:set var="i" value="${i+1}"/>
</c:forEach>
