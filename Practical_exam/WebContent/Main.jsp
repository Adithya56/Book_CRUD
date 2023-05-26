<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>CRUD Operations</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
  $(document).ready(function() {
    // Function to handle AJAX call and display response
    function Request(url) {
      $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
          $('#content').html(response);
        },
        error: function(xhr, status, error) {
          console.log('AJAX Error: ' + error);
        }
      });
    }
    function Logout(url) {
      $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
        	window.location.href = 'login.jsp';
        },
        error: function(xhr, status, error) {
          console.log('AJAX Error: ' + error);
        }
      });
    }

    // Event handlers for CRUD buttons
    $('#createBtn').click(function() {
      Request('BookServlet?action=create');
    });

    $('#readBtn').click(function() {
      Request('BookServlet?action=read');
    });

    $('#updateBtn').click(function() {
      Request('BookServlet?action=update');
    });

    $('#deleteBtn').click(function() {
      Request('BookServlet?action=delete');
    });

    // Event handler for logout button
    $('#logoutBtn').click(function() {
     	Logout('BookServlet?action=logout');
    });
  });
</script>

</head>
<body>
  <div class="container mt-5">
    <h2>CRUD Operations</h2>
    <div class="text-center my-3">
      <button id="createBtn" class="btn btn-primary mr-2">Create</button>
      <button id="readBtn" class="btn btn-primary mr-2">Read</button>
      <button id="updateBtn" class="btn btn-primary mr-2">Update</button>
      <button id="deleteBtn" class="btn btn-danger">Delete</button>
    </div>
    <div class="text-right">
      <button id="logoutBtn" class="btn btn-danger">Logout</button>
    </div>
    <div id="content"></div>
  </div>
</body>
</html>