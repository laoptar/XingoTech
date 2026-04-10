<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>welcome</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style type="text/css">
      html, body {
        margin: 0;
        padding: 0;
        border: 0;
        line-height: 1;
        -webkit-text-size-adjust: 100%;
      }

      main {
        display: block;
      }
      
      h1, h2, h3, h4, h5, h6, hr, p, iframe, dl, dt, dd, ul, ol, li, pre, form, button, input, textarea, th, td {
        margin: 0;
        padding: 0;
      }
      
      ul, ol, dl {
        list-style: none;
      }
      
      #app {
        width: 100%;
        height: 100%;
        display: block;
        margin: 0;
        padding: 0;
        border: 0;
      }
      
      #app .context {
        display: block;
        width: 100%;
      }
      
      .form-wrap {
        display: block;
      }
      
      .form-wrap .form-item {
        display: block;
      }
      
      .form-wrap .form-item label {
        display: inline-block;
        width: 100px;
        text-align: right;
      }
      
      #app .context .result-text {
        display: inline-block;
        max-width: 800px;
      }
    </style>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
    <script type="text/javascript">
      function viewModel() {
        var formElemt = $("#app").find(".form-wrap .form-item");
        
        return {
          id: $.trim(formElemt.find("input[name='id']").val()),
          username: $.trim(formElemt.find("input[name='username']").val()),
          email: $.trim(formElemt.find("input[name='email']").val()),
          roles: $.trim(formElemt.find("input[name='roles']").val()),
          mobile: $.trim(formElemt.find("input[name='mobile']").val()),
          control: $.trim(formElemt.find("input[name='control']").val()),
          apiUrl: $.trim(formElemt.find("input[name='apiUrl']").val()),
          method: $.trim(formElemt.find("input[name='method']").val()),
          formField: $.trim(formElemt.find("textarea[name='formField']").val())
        }
      }

      function operate() {
        console.log("Hello World!");
        var dataField = viewModel();

        var ajaxUrl = "";
        if (dataField.control === "add") {
          ajaxUrl = "http://localhost:8080/XingoTech/user/addUser";
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: { id: dataField.id, username: dataField.username, email: dataField.email, roles: dataField.roles, mobile: dataField.mobile },
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        } else if (dataField.control === "edit") {
          ajaxUrl = "http://localhost:8080/XingoTech/user/editUser";
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: { id: dataField.id, username: dataField.username, email: dataField.email, roles: dataField.roles, mobile: dataField.mobile },
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        } else if (dataField.control === "delete") {
          ajaxUrl = "http://localhost:8080/XingoTech/user/delUser";
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: { id: dataField.id },
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        } else if (dataField.control === "list") {
          ajaxUrl = "http://localhost:8080/XingoTech/user/list";
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: {},
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        } else if (dataField.control === "find") {
          ajaxUrl = "http://localhost:8080/XingoTech/user/select";
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: { id: dataField.id },
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        } else {
          ajaxUrl = dataField.apiUrl;
          $.ajax({
              url: ajaxUrl,
              type: dataField.method,
              data: JSON.parse(dataField.formField),
              contentType: 'applicaiton/json;UTF-8',
              dataType: 'json',
              async: true,
              timeout: 5000,
              success: function (res) {
                console.log(res);
                $("#app").find(".context .result-text").text(JSON.stringify(res));
              }
            });
        }
      }
    </script>
  </head>

  <body>
    <div id="app">
      <h1>Hello World!</h1>
      <div class="context">
        <form action="#" class="form-wrap">
          <div class="form-item">
            <label for="id">id: </label>
            <input type="text" name="id" />
          </div>
          
          <div class="form-item">
            <label for="username">userName: </label>
            <input type="text" name="username" />
          </div>
          
          <div class="form-item">
            <label for="email">email: </label>
            <input type="text" name="email" />
          </div>
          
          <div class="form-item">
            <label for="roles">roles: </label>
            <input type="text" name="roles" />
          </div>
          
          <div class="form-item">
            <label for="mobile">mobile: </label>
            <input type="text" name="mobile" />
          </div>
          
          <div class="form-item">
            <label for="control">control: </label>
            <input type="text" name="control" required />
          </div>
          
          <div class="form-item">
            <label for="apiUrl">apiUrl: </label>
            <input type="text" name="apiUrl" />
          </div>
          
          <div class="form-item">
            <label for="method">method: </label>
            <input type="text" name="method" />
          </div>
          
          <div class="form-item">
            <label for="formField">formField: </label>
            <textarea name="formField" rows="5" cols="21"></textarea>
          </div>
          
          <div class="form-item">
            <button onclick="operate()">Confirm</button>
          </div>
        </form>
        
        <pre class="result-text"></pre>
      </div>
    </div>
  </body>
</html>
