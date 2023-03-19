<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ユーザ新規登録</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
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

	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger mt-3" role="alert">
		  ${errMsg}
		</div>
	</c:if>

		<div class="row">
			<div class="mx-auto mt-5 mb-3">
				<h1>ユーザ新規登録</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-3 mx-auto">
				<form action="SignUp" method="post">
					<div class="form-group">
						<label for="login">ログインID</label> <input type="text" name="login"
							id="rogin" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">パスワード</label> <input type="password"
							name="password" id="password" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">パスワード(確認)</label> <input type="password"
							name="password2" id="password" class="form-control" />
					</div>
					<div class="form-group">
						<label for="user">ユーザ名</label> <input type="text" name="user"
							id="user" class="form-control" />
					</div>
					<div class="form-group">
						<label for="date">生年月日</label> <input type="date" name="date"
							id="date" class="form-control" />
					</div>
					<div class="text-center my-5">
						<button class="btn btn-info w-50">登録</button>
					</div>
				</form>
			</div>
		</div>
		<a href="UserList">戻る</a>
	</div>
</body>
</html>
