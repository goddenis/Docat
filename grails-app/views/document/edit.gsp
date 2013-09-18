<%@ page import="docat.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Редактирование документа</title>
</head>
<body>

	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
					<li><g:link class="list" action="list">Список документов</g:link></li>
					<li><g:link class="create" action="create">Создать документ</g:link></li>
				</ul>
			</div>
		</div>
		<div class="span9">
			<h1>Редактирование документа</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${documentInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${documentInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form method="post">
				<g:hiddenField name="id" value="${documentInstance?.id}" />
				<g:hiddenField name="version" value="${documentInstance?.version}" />
				<fieldset class="form">
					<g:render template="form" />
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-primary" action="update" value="Сохранить" />
					<g:actionSubmit class="btn" action="delete" value="Удалить"
						formnovalidate=""
						onclick="return confirm('Вы уверены');" />
				</fieldset>
			</g:form>
            <g:uploadForm action="upload">
                <input type="file" name="myFile" />
                <input type="submit" />
            </g:uploadForm>
		</div>
</body>
</html>
