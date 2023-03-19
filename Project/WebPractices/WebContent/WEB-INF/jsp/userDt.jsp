<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ユーザ情報詳細参照</title>
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
        <div class="col-4 mt-5 mx-auto">
          <h1 class="tweak">ユーザ情報詳細参照</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-4 mx-auto my-5">
          <ul>
            <li>ログインID<span>${userD.login_id}</span></li>
            <li>ユーザ名<span>${userD.name}</span></li>
            <li>生年月日<span>${userD.birth_date}</span></li>
            <li>登録日時<span>${userD.create_date}</span></li>
            <li>更新日時<span>${userD.update_date}</span></li>
          </ul>
        </div>
      </div>
      <a href="UserList">戻る</a>
    </div>
  </body>
</html>
