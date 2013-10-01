package docat



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProcessingService)
class ProcessingServiceTests {

    void testSomething() {
        def doc = new Document(category: new DocCategory(name: "Категория 1 ") , name: "Документ 1", attachedFileName: "asdfwerter", id: 1,version: 1 )



        assertFalse(!doc)
    }
}
