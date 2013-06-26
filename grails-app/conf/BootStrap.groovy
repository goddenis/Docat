import java.util.concurrent.ForkJoinPool;

import docat.DocCategory
import docat.Document
import docat.Role;
import docat.User
import docat.UserRole

class BootStrap {

	def init = { servletContext ->

		def category1 = new DocCategory(name: 'Категория 1'  )
		if (!category1.save()){
			category1.errors.allErrors.each{ it -> println "Error : ${it} " }
		}
		def category2 = new DocCategory(name : 'Каттегория 2')
		if (!category2.save()){
			category2.errors.allErrors.each{ it -> println "Error : ${it} " }
		}


		def document1 = new Document(name : 'doc 1', category : category1 )

		if (!document1.save()){
			document1.errors.allErrors.each {it -> println "error :${it}"}
		}

		def document2 = new Document(name: 'doc 2', category: category2 )

		if (!document2.save()){
			document2.errors.allErrors.each {it -> println "error :${it}"  }
		}
		for (int i = 3; i< 15 ; i++){
			category1 = new DocCategory(name: "cat: ${i}" )
			if (!category1.save()){
				category1.errors.allErrors.each{ it -> println "Error : ${it} " }
			}
		}
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'USER_ROLE' ).save(flush: true)

		def testUser = new User(username: "goddenis", enabled: true, password:'1234' )
		testUser.save(flush: true)
		
		UserRole.create testUser ,adminRole ,true
	}
	def destroy = {
	}
}
