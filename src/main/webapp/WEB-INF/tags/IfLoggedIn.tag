<%@ tag description="shows the body, if the visitor logged in" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pageContext.request.remoteUser != null}">
  <jsp:doBody/>
</c:if>