package docat

class ProcessingService {
    def grailsApplication
    String process(def file) {

        def conf = grailsApplication.config.devProperties
        def newFileName = UUID.randomUUID().toString()

        if (!storeAveable()){
            throw new Exception("Store not aveible")
        }

        file.transferTo(new File("${conf.storeFolder}\\${newFileName}.pdf"))

        SearchUtil.indexFile(new File("${conf.storeFolder}\\${newFileName}.pdf").getAbsolutePath())
        return newFileName
    }

    private def storeAveable(){

        def conf = grailsApplication.config.devProperties
        def store = new File(conf.storeFolder)
        if (!store.exists()){
            store.mkdirs()
            if(!store.exists()) return false
        }
        return true
    }
}
