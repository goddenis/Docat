import docat.DocCategory
import docat.Document
import docat.User

/**
 * Created with IntelliJ IDEA.
 * User: Usov
 * Date: 04.10.13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
class ProcessingServiceTests extends GroovyTestCase {
    def grailsApplication
    def processingService
    void testConf(){
        def url = grailsApplication.config.devProperties.searchUrl
        assertTrue("http://192.168.33.52:8080/solr".equals(url))
    }

    void testNotSolarableEntityIndexing(){
        def user = new User( id: 1 , name :"test user")
        def res = processingService.indexEntity(user)

        assertFalse("Ошибка ненидексируемая сущьность проиндексирована",res)
    }

    void testSimpleSolrableEntityIndexing(){
        def docCat = new DocCategory(id: 1, name: "doc 1")
        processingService.indexEntity(docCat)
    }

    void testSolrableEntityWithExtractableContent(){
        def doc = new Document(id: 1, name: "new testDoc 1", attachedFileName: "testdoc2")
        processingService.indexEntity(doc)
    }

}
