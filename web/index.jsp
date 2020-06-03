<%--
  Created by IntelliJ IDEA.
  User: Rafik
  Date: 02.06.2020
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<style>
    body {
        background-color: #3c3f41;
    }
    form {
        background-color: #4b1f33;
        width: 300px;
        height: 100px;
    }
    #content {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: auto;
    }
</style>
<body>
    <div id="content">
        <form action="Servlet" method="post">
            <label for="number"></label><input id="number" type="text"/>
            <input id="send" value="Calculate" type="button"/>
        </form>
    </div>
<script>
    function decodeUrlParameter(str) {
        return decodeURIComponent((str+'').replace(/\+/g, '%20'));
    }
    document.getElementById("send").onclick = () => {
        const number = document.getElementById("number").value;
        const expression = decodeUrlParameter(number);
        getData('/ServletONP_war_exploded/Servlet?number=' + expression).then(data => {
            document.getElementById("number").value = data;
        });
    }

    async function getData(url = '') {
        const response = await fetch(url, {
            method: 'GET', // *GET, POST, PUT, DELETE, etc
        });
        return response.json(); // parses JSON response into native JavaScript objects
    }


</script>
</body>
</html>
