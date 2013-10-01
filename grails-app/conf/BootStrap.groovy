import docat.SolrIndexListener

import java.util.concurrent.ForkJoinPool;

import docat.DocCategory
import docat.Document
import docat.Role;
import docat.User
import docat.UserRole

class BootStrap {
    def init = { servletContext ->

        def category1 = new DocCategory(name: 'Категория 1')
        if (!category1.save()) {
            category1.errors.allErrors.each { it -> println "Error : ${it} " }
        }
        def category2 = new DocCategory(name: 'Категория 2')
        if (!category2.save()) {
            category2.errors.allErrors.each { it -> println "Error : ${it} " }
        }


        def documents = [new Document(name: 'Grails', category: category1, attachedFileName: "testdoc1"),
                new Document(name: 'solar 4', category: category2, attachedFileName: "testdoc2"),
                new Document(name: 'Apache Solr 4 (indexed)', category: category2, attachedFileName: "testdoc2"),
                new Document(name: 'Apache Solr 4 reference guide (indexed)', category: category2, attachedFileName: "1f160c1a-a38b-404f-a68c-d7992cce5da5"),
                new Document(name: 'Data Structures and Abstractions in Java', category: category2, attachedFileName: "4d4dc6de-298c-49c9-accb-c46bdbfbf468"),
                new Document(name: 'Apache Solr 4 reference guide (indexed)(duble)', category: category2, attachedFileName: "5fa8a344-9d52-4587-89d1-18c046453b54"),
                new Document(name: 'OSGi and Apache Felix', category: category2, attachedFileName: "6ce95e78-c90e-4278-b02c-62911e5614c3")]
        documents.each {
            if(!it.save()){
                it.errors.allErrors.each {v -> println "error :${v}" }
            }
        }
        for (int i = 3; i < 15; i++) {
            category1 = new DocCategory(name: "cat: ${i}")
            if (!category1.save()) {
                category1.errors.allErrors.each { it -> println "Error : ${it} " }
            }
        }
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(username: "goddenis", enabled: true, password: '1234')
        testUser.save(flush: true)

        UserRole.create testUser, adminRole, true

    }
    def destroy = {
    }
}
