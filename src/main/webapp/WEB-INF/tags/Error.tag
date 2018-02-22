<%@tag pageEncoding="UTF-8"%>
<%@tag import="java.util.*, javax.validation.*" %>
<%@attribute name="property" required="true"%>
<%
    Set<ConstraintViolation> errors = (Set) request.getAttribute("errors");
    if (errors != null) {
        for (ConstraintViolation cv : errors) {
            if (cv.getPropertyPath().toString().equals(property)) {
                out.println("<span class='error'>&larr;&nbsp;" + cv.getMessage() + "</span>");
                break;
            }
        }
    }
%>
