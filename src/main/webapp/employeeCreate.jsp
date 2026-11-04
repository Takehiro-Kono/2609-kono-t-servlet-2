
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員登録</title>
	
	<style>
	    fieldset {
	        margin-bottom: 30px;
	        padding: 10px 20px 20px;
	    }

	    legend {
	        font-size: 24px;
	        font-weight: normal;
	    }

	    .form-row {
	        display: flex;
	        align-items: flex-start;
	        margin-bottom: 8px;
	    }

	    .form-label {
	        width: 150px;
	        font-size: 16px;
	        padding-top: 5px;
			margin-right: 15px;
			width: max-content;
	    }

	    .form-input {
	        width: 350px;
	        padding: 3px;
	        font-size: 16px;
	        box-sizing: border-box;
	    }

	    .profile-input {
	        width: 350px;
	        height: 120px;
	        resize: vertical;
	    }

	    .required {
	        color: red;
	    }

	    .error {
	        color: red;
	        margin: 5px 0 0 150px;
	    }
	</style>
</head>
<body>
    <h1>従業員登録</h1>
	<p style="color: red;">${errorMessage}</p>
    <form action="${pageContext.request.contextPath}/employees/confirm"
          method="post">
		  <fieldset>
		      <legend>従業員情報</legend>

		      <div class="form-row">
		          <label class="form-label" for="name">
		              名前 <span class="required">*</span>
		          </label>
		          <div>
		              <input class="form-input" type="text" id="name" name="name" value="${name}">
		              <p class="error">${nameError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="mail">
		              メールアドレス <span class="required">*</span>
		          </label>
		          <div>
		              <input class="form-input" type="email" id="mail" name="mail" value="${mail}">
		              <p class="error">${mailError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="password">
		              パスワード <span class="required">*</span>
		          </label>
		          <div>
		              <input class="form-input" type="password" id="password" name="password">
		              <p class="error">${passwordError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="passwordConfirm">
		              パスワード確認 <span class="required">*</span>
		          </label>
		          <div>
		              <input class="form-input" type="password" id="passwordConfirm" name="passwordConfirm">
		              <p class="error">${passwordConfirmError}</p>
		          </div>
		      </div>
		  </fieldset>

		  <fieldset>
		      <legend>従業員詳細情報</legend>

		      <div class="form-row">
		          <label class="form-label" for="age">
		              年齢
		          </label>
		          <div>
		              <input class="form-input" type="number" id="age" name="age" value="${age}">
		              <p class="error">${ageError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="profile">
		              プロフィール
		          </label>
		          <div>
		              <textarea
		                  class="form-input profile-input"
		                  id="profile"
		                  name="profile"
		                  rows="3">${profile}</textarea>
		              <p class="error">${profileError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="birthplace">
		              出身地
		          </label>
		          <div>
		              <input class="form-input" type="text" id="birthplace" name="birthplace" value="${birthplace}">
		              <p class="error">${birthplaceError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="height">
		              身長
		          </label>
		          <div>
		              <input class="form-input" type="number" id="height" name="height" value="${height}" step="0.1">
		              cm
		              <p class="error">${heightError}</p>
		          </div>
		      </div>

		      <div class="form-row">
		          <label class="form-label" for="weight">
		              体重
		          </label>
		          <div>
		              <input class="form-input" type="number" id="weight" name="weight" value="${weight}" step="0.1">
		              kg
		              <p class="error">${weightError}</p>
		          </div>
		      </div>
		  </fieldset>
        <button type="submit">確認画面へ</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/employees">
            社員一覧に戻る
        </a>
    </p>
</body>
</html>
