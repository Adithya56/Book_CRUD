<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    
    <!-- Bootstrap CSS -->
    <!-- Bootstrap JavaScript -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
    <div class="container">
        <form method="post" action="auth" class="mt-5">
            <table align="center">
                <tr>
                    <th>User-name: </th>
                    <td><input type="text" id="uname" name="uname" class="form-control"></td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td><input type="password" name="pass" class="form-control"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" id="submit" value="Login" class="btn btn-primary mt-3">
                    </td>
                </tr>
            </table>
        </form>
    </div>  
</body>
</html>
