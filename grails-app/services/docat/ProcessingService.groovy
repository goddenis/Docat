package docat

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method
import org.apache.http.entity.InputStreamEntity

class ProcessingService {
    def grailsApplication
    String process(def file) {

        def conf = grailsApplication.config.devProperties
        def newFileName = UUID.randomUUID().toString()

        if (!storeAveable()){
            throw new Exception("Store not aveible")
        }

        file.transferTo(new File("${conf.storeFolder}\\${newFileName}.pdf"))

        indexFile(new File("${conf.storeFolder}\\${newFileName}.pdf").getAbsolutePath())
        return newFileName
    }

     byte[] readFile (name){
        def conf = grailsApplication.config.devProperties
        return new File("${conf.storeFolder}\\${name}.pdf").bytes
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

    public def search() {
        def devPrps= grailsApplication.config.devProperties
        try {
            def ret = null
            def http = new HTTPBuilder(devPrps.searchUrl)

            http.request(Method.GET, ContentType.JSON) {
                uri.path = devPrps.searchPath
                uri.query = [q: "*", wt: "json", indent: "true"]
                response.success = { resp, reader ->
                    println "response status: ${resp.statusLine}"
                    println 'Headers: -----------'
                    resp.headers.each { h ->
                        println " ${h.name} : ${h.value}"
                    }
                    ret = reader?.get("response")?.get("docs")
                    println 'Response data: -----'
                    println ret
                    println '--------------------'
                }
            }
            return ret

        } catch (HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (ConnectException ex) {
            ex.printStackTrace()
            return null
        }
    }

    public def indexFile(String filePath){
        def devPrps= grailsApplication.config.devProperties
        def file = new File(filePath)

        def reqEntity = new InputStreamEntity(new FileInputStream(new File (filePath)),-1)
        reqEntity.setContentType("binary/octet-stream")
        reqEntity.setChunked(false)

        def http = new HTTPBuilder(devPrps.searchUrl)
        http.request(Method.POST) { req ->
            uri.path = devPrps.extractPath
            uri.query=["literal.id":file.getName(),commit:true]
            requestContentType: "binary/octet-stream"


            req.setEntity(reqEntity)

            response.success = { resp ->
                if (resp.statusLine.statusCode == 200) {
                    // response handling
                }
            }
        }


    }

}
