<html>
<head>
<meta name='layout' content='main' />
<title>ДокАТ : Авторизация</title>
</head>

<body>
	<div id='login' class="container">


		<g:if test='${flash.message}'>
			<div class='login_message'>
				${flash.message}
			</div>
		</g:if>

		<form action='${postUrl}' method='POST' id='loginForm'
			class='form-signin' autocomplete='off'>
			<h2>Авторизация</h2>

			<input type='text' class='input-block-level' placeholder="Логин" name='j_username' id='username' /> 
			<input type='password' class='input-block-level' placeholder="Пароль" name='j_password' id='password' />
			<label class='checkbox'>
			<input type='checkbox' class='chk' name='${rememberMeParameter}'
					id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if> />
			Запомнить пароль
				</label>

				<button class="btn btn-large btn-primary" type='submit' id="submit">Залогиниться</button>
		</form>
	</div>
	<script type='text/javascript'>
	<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
	// -->
	</script>
</body>
</html>
