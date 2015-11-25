<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: red; font-weight: bold;
    }
</style>

<div class="bpCreation">
    <div class="headline">Business Partner Creation</div>

    <form:form method="POST" commandName="customerForm">
        <form:errors path="*" cssClass="error" element="div" />
        <c:if test="${not empty serviceError}">
			<div class="error">
				<p>${serviceError}</p>
			</div>
        </c:if>
        <table>
            <tr>
                <td>Email :</td>
                <td><form:input path="email" /></td>
                <td><form:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Name :</td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Age :</td>
                <td><form:input path="age" /></td>
                <td><form:errors path="age" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="create" />
                </td>
            </tr>
        </table>
    </form:form>


</div>
