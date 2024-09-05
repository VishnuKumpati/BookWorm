<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        .form-container {
            max-width: 400px;
            margin: 30px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #007BFF;
        }

        .form-group {
            margin-bottom: 15px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input:focus,
        select:focus {
            border-color: #007BFF;
            outline: none;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 14px;
        }

        .mt-3 {
            margin-top: 15px;
            text-align: center;
        }

        a {
            color: #007BFF;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .alert {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
            text-align: center;
        }
    </style>

    <script type="text/javascript">
        function toggleAdminPassword() {
            var userType = document.getElementById("userType").value;
            var adminPasswordField = document.getElementById("adminPassword");
            adminPasswordField.style.display = (userType === "Admin") ? "block" : "none";
        }

        function validatePassword() {
            var password = document.getElementById("password").value;
            var errorMessage = "";

            if (!/(?=.*\d)/.test(password)) {
                errorMessage += "Password must contain at least one number.\n";
            }

            if (!/(?=.*[a-zA-Z])/.test(password)) {
                errorMessage += "Password must contain at least one letter.\n";
            }

            if (!/(?=.*[@#$%^&+=])/.test(password)) {
                errorMessage += "Password must contain at least one special character.\n";
            }

            if (password.length < 8) {
                errorMessage += "Password must be at least 8 characters long.\n";
            }

            document.getElementById("passwordError").innerText = errorMessage;
            return errorMessage === "";
        }

        function validateForm() {
            return validatePassword() && validateContactNo();
        }

        function validateContactNo() {
            var contactNo = document.getElementById("contactno").value;
            var contactNoError = "";

            if (contactNo.length !== 10 || isNaN(contactNo)) {
                contactNoError = "Contact number must be exactly 10 digits.";
            }

            document.getElementById("contactNoError").innerText = contactNoError;
            return contactNoError === "";
        }
    </script>
</head>

<body>
    <div class="form-container">
        <h2>Registration Form</h2>

        <!-- Display error message here -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="alert"><%= errorMessage %></div>
        <% } %>

        <form action="register" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
                <div id="passwordError" class="error"></div>
            </div>
            <div class="form-group">
                <input type="number" id="age" name="age" placeholder="Enter your age" required>
            </div>
            <div class="form-group">
                <input type="text" id="contactno" name="contactno" placeholder="Enter your contact number" required>
                <div id="contactNoError" class="error"></div>
            </div>
            <div class="form-group">
                <input type="text" id="city" name="city" placeholder="Enter your city" required>
            </div>
            <div class="form-group">
                <select id="userType" name="userType" onchange="toggleAdminPassword()" required>
                    <option value="">Select User Type</option>
                    <option value="Admin">Admin</option>
                    <option value="Buyer">Buyer</option>
                    <option value="Retailer">Retailer</option>
                </select>
            </div>
            <div class="form-group" id="adminPassword" style="display: none;">
                <input type="password" id="adminPasswordField" name="adminPassword" placeholder="Enter admin password">
            </div>
            <div class="form-group text-center">
                <button type="submit">Register</button>
                <div id="link" class="mt-3">
                    <a href="login">Already Have an Account? Login</a>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
