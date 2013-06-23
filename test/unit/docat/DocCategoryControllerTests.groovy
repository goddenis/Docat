package docat



import org.junit.*
import grails.test.mixin.*

@TestFor(DocCategoryController)
@Mock(DocCategory)
class DocCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/docCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.docCategoryInstanceList.size() == 0
        assert model.docCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.docCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.docCategoryInstance != null
        assert view == '/docCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/docCategory/show/1'
        assert controller.flash.message != null
        assert DocCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/docCategory/list'

        populateValidParams(params)
        def docCategory = new DocCategory(params)

        assert docCategory.save() != null

        params.id = docCategory.id

        def model = controller.show()

        assert model.docCategoryInstance == docCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/docCategory/list'

        populateValidParams(params)
        def docCategory = new DocCategory(params)

        assert docCategory.save() != null

        params.id = docCategory.id

        def model = controller.edit()

        assert model.docCategoryInstance == docCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/docCategory/list'

        response.reset()

        populateValidParams(params)
        def docCategory = new DocCategory(params)

        assert docCategory.save() != null

        // test invalid parameters in update
        params.id = docCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/docCategory/edit"
        assert model.docCategoryInstance != null

        docCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/docCategory/show/$docCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        docCategory.clearErrors()

        populateValidParams(params)
        params.id = docCategory.id
        params.version = -1
        controller.update()

        assert view == "/docCategory/edit"
        assert model.docCategoryInstance != null
        assert model.docCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/docCategory/list'

        response.reset()

        populateValidParams(params)
        def docCategory = new DocCategory(params)

        assert docCategory.save() != null
        assert DocCategory.count() == 1

        params.id = docCategory.id

        controller.delete()

        assert DocCategory.count() == 0
        assert DocCategory.get(docCategory.id) == null
        assert response.redirectedUrl == '/docCategory/list'
    }
}
