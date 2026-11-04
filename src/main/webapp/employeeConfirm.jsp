<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員登録確認</title>
	<style>
	    fieldset {
	        margin-bottom: 30px;
	        padding: 15px 25px 25px;
	    }

	    legend {
	        font-size: 24px;
	    }

	    .confirm-row {
	        display: flex;
	        align-items: flex-start;
	        margin-bottom: 18px;
	        font-size: 20px;
	    }

	    .confirm-label {
	        width: 150px;
	        flex-shrink: 0;
	    }

	    .confirm-value {
	        white-space: pre-wrap;
	        overflow-wrap: anywhere;
	    }
	</style>
</head>
<body>

	<h1>従業員登録確認</h1>

	<fieldset>
	    <legend>従業員情報</legend>

	    <div class="confirm-row">
	        <div class="confirm-label">名前</div>
	        <div class="confirm-value">${name}</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">メールアドレス</div>
	        <div class="confirm-value">${mail}</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">パスワード</div>
	        <div class="confirm-value">****</div>
	    </div>
	</fieldset>

	<fieldset>
	    <legend>従業員詳細情報</legend>

	    <div class="confirm-row">
	        <div class="confirm-label">年齢</div>
	        <div class="confirm-value">${age}</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">プロフィール</div>
	        <div class="confirm-value">${profile}</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">出身地</div>
	        <div class="confirm-value">${birthplace}</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">身長</div>
	        <div class="confirm-value">${height} cm</div>
	    </div>

	    <div class="confirm-row">
	        <div class="confirm-label">体重</div>
	        <div class="confirm-value">${weight} kg</div>
	    </div>
	</fieldset>

    <p>
        入力内容に間違いがなければ登録してください。
    </p>
	
	<div style="display: flex; gap: 10px;">
	<form action="${pageContext.request.contextPath}/employees/confirm" method="post">
	    <input type="hidden" name="mail" value="${mail}">
	    <input type="hidden" name="name" value="${name}">
	    <input type="hidden" name="age" value="${age}">
	    <input type="hidden" name="profile" value="${profile}">
	    <input type="hidden" name="birthplace" value="${birthplace}">
	    <input type="hidden" name="height" value="${height}">
	    <input type="hidden" name="weight" value="${weight}">

	    <button type="submit" name="edit" value="true">修正する</button>
	</form>

	<form action="${pageContext.request.contextPath}/employees/register"
	      method="post">

	    <input type="hidden" name="mail" value="${mail}">
		
	    <input type="hidden" name="name" value="${name}">
		
	    <input type="hidden" name="age" value="${age}">
	    <input type="hidden" name="profile" value="${profile}">
	    <input type="hidden" name="birthplace" value="${birthplace}">
	    <input type="hidden" name="height" value="${height}">
	    <input type="hidden" name="weight" value="${weight}">

	    <button type="submit">登録する</button>

	</form>
	</div>

</body>
</html>