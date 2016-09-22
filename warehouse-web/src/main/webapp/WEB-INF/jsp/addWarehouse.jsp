<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h2>Add Warehouse informationn</h2>
<form:form method="POST" action="addwarehouse" modelAttribute="warehouse">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description" /></td>
        </tr>
        <tr>
            <td><form:label path="longitude">Longitude</form:label></td>
            <td><form:input path="longitude" /></td>
        </tr>
        <tr>
            <td><form:label path="latitude">Latitude</form:label></td>
            <td><form:input path="latitude" /></td>
        </tr>
        <tr>
            <td><form:label path="capacity">Capacity</form:label></td>
            <td><form:input path="capacity" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
