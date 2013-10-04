package docat



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SearchController)
class SearchControllerTests {
    def grailsApplication
    void testUrlGetTest(){
        def url=  grailsApplication.config.devProperties.searchurl
        println url
        assertTrue("http://192.168.33.52:8080/solr".equals(url))
    }

    void testIndexSimpleDocTest(){

    }

}
