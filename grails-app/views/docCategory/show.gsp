
<%@ page import="docat.DocCategory"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>"ДокАТ : Категория подробно"</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
					<li><g:link class="list" action="list">Список категорий</g:link></li>
					<li><g:link class="create" action="create">Создать новую категорию</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">
			<h1>Категория подробно</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<ul class="property-list docCategory">

				<g:if test="${docCategoryInstance?.name}">
					<li><span>Имя</span> <span><g:fieldValue
								bean="${docCategoryInstance}" field="name" /></span></li>
				</g:if>

			</ul>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${docCategoryInstance?.id}" />
					<g:link class="btn btn-primary" action="edit"
						id="${docCategoryInstance?.id}">
						Редактировать
					</g:link>
					<g:actionSubmit class="btn" action="delete" value="Удалить"
						onclick="return confirm('Вы уверены ?');" />
				</fieldset>
			</g:form>


		</div>
	</div>

</body>
</html>
