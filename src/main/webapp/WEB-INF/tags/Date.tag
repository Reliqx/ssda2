<%@tag import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:formatDate value="<%= new Date()%>" type="date" dateStyle="medium"/>