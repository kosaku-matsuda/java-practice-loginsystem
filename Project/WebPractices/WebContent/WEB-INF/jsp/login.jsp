<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ログイン画面</title>
    <link rel="stylesheet" href="css/style.css" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div class="container">
	    <c:if test="${errMsg != null}" >
		    <div class="alert alert-danger mt-5" role="alert">
			  ${errMsg}
			</div>
		</c:if>
      <div class="row">
        <div class="card col-5 offset-4 mx-auto">
          <div class="card-body">
            <h1 class="rog-t mb-5">ログイン画面</h1>
            <form action="LogServlet" method="post">
              <div class="form-group">
                <label for="rogin">ログインID</label>
                <input
                  type="text"
                  name="login"
                  id="login"
                  class="form-control"
                />
              </div>
              <div class="form-group">
                <label for="password">パスワード</label>
                <input
                  type="password"
                  name="password"
                  id="password"
                  class="form-control"
                />
              </div>
              <div class="text-center my-5">
                <button class="btn btn-primary w-50" type="submit">
                  <a class="text-white">ログイン</a>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
