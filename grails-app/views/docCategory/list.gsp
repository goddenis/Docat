<%@ page import="docat.DocCategory"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Список категорий</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message
								code="default.home.label" /></a></li>
					<li><g:link class="create" action="create">
							Создать новую категорию
						</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">
			<h1>Список категорий</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>

			<table class="table">
				<thead>
					<tr>
						<g:sortableColumn property="name"
							title="${message(code: 'docCategory.name.label', default: 'Name')}" />
					</tr>
				</thead>
				<tbody>
					<g:each in="${docCategoryInstanceList}" status="i"
						var="docCategoryInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:link action="show" id="${docCategoryInstance.id}">
									${fieldValue(bean: docCategoryInstance, field: "name")}
								</g:link></td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${docCategoryInstanceTotal}" />
			</div>
		</div>
	</div>


</body>
</html>
