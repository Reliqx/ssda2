<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="value" rtexprvalue="true" %>
<%@attribute name="options" type="String[]" required="true" rtexprvalue="true" %>
<%@attribute name="labels" type="String[]" required="false" rtexprvalue="true" %>
<%@attribute name="id" %>
<%@attribute name="name" required="true" %>
<c:if test="${empty labels}">
    <c:set var="labels" value="${options}"/>
</c:if>
<select id="${id}" name="${name}">
    <c:set var="i" value="0"/>
    <c:forEach items="${options}" var="option">
        <option value="${option}" ${(value == option)?"selected":""}>${labels[i]}</option>
        <c:set var="i" value="${i+1}"/>
    </c:forEach>
</select>