<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ユーザ削除</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
</head>
<body>
	<header>
		<div class="head-c">
			<p>ユーザー名さん</p>
		</div>
		<div class="head-r">
			<a href="Logout">ログアウト</a>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="mx-auto mt-5 mb-3">
				<h1>ユーザー削除確認</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-3 mx-auto">
				<p>ログインID:${userC.id}を本当に削除してもよろしいでしょうか。</p>
				<div class="text-center my-5">
					<button class="btn btn-warning w-50 text-white">
						<a class="text-white" href="UserList">キャンセル</a>
					</button>
				</div>
				<form action="Delete" method="post">
					<div class="text-center my-5">
						<input type="hidden" name="id" id="id" class="form-control"
							value="${userC.id}" />
						<button class="btn btn-success w-50" type="submit">ＯK</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
