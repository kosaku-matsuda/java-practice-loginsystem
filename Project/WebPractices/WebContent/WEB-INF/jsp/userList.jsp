<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ユーザ一覧</title>
    <link rel="stylesheet" href="css/style.css" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <header>
      <div class="head-c">
        <p>${userInfo.name}さん</p>
      </div>
      <div class="head-r">
        <a href="Logout">ログアウト</a>
      </div>
    </header>
    <div class="container">
      <div class="row">
        <div class="col-2 mx-auto mt-5">
          <h1>ユーザ一覧</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-1 offset-11 mt-3">
          <a href="SignUp" class="sign-up">新規登録</a>
        </div>
      </div>
      <div class="row">
        <div class="col-3 mx-auto">
          <form action="UserList" method="post">
            <div class="form-group">
              <label for="rogin">ログインID</label>
              <input type="text" name="rogin_id" id="rogin" class="form-control" />
            </div>
            <div class="form-group">
              <label for="user">ユーザ名</label>
              <input type="text" name="user_name" id="user" class="form-control" />
            </div>
            <div class="form-group">
              <label for="date">生年月日</label>
              <input type="date" name="birthStart" id="date" class="form-control" />
            </div>
            <div class="form-group">
              <input type="date" name="birthEnd" id="date" class="form-control" />
            </div>
            <div class="text-center my-5">
              <button class="btn btn-success w-50">検索</button>
            </div>
          </form>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ログインID</th>
              <th>ユーザ名</th>
              <th>生年月日</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
             <c:forEach var="user" items="${userList}" >
                   <tr>
                     <td>${user.login_id}</td>
                     <td>${user.name}</td>
                     <td>${user.birth_date}</td>
                     <td>
                       <a class="btn btn-primary" href="UserDt?id=${user.id}">詳細</a>
                       <a class="btn btn-success" href="Update?id=${user.id}">更新</a>
                       <a class="btn btn-danger" href ="Delete?id=${user.id}">削除</a>
                      <!--  hrefのあとのサーブレットのゲットにかならずとぶ -->
                      <!-- ？以降はゲットパラメーターの引数の値を渡す -->

                     </td>
                   </tr>
				</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
