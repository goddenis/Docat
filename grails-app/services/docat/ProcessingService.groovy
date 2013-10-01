package docat

import grails.converters.JSON
import grails.web.JSONBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method
import org.apache.http.HttpEntity
import org.apache.http.entity.InputStreamEntity
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer
import org.apache.solr.client.solrj.request.UpdateRequest
import org.apache.solr.common.SolrInputDocument
import org.codehaus.groovy.grails.commons.GrailsClassUtils
import org.codehaus.groovy.grails.web.json.JSONArray
import org.apache.http.entity.StringEntity

class ProcessingService {
    boolean transactional = false
    def grailsApplication
    private def servers = [:]

    def private getServer() {
        def url = grailsApplication.config.devProperties.searchUrl

        if (!servers[url]) {
            servers[url] = new CommonsHttpSolrServer(url)
        }
        return servers[url]
    }


    def indexDocument(Document document) {
        def server = getServer()

        def doc = new SolrInputDocument()
        document.getClass().fields

        updateUrl = (grailsApplication.config.devProperties?.extractPath) ? (grailsApplication.config.devProperties?.extractPath) : "/update/extract"
        def up = new UpdateRequest(updateUrl)
        up.setAction(UpdateRequest.ACTION.COMMIT, true)
        up.add(doc)

    }


    def reindexEntity(def p) {

        if (p["solarable"]){
            println( "solrable")
        }

    }


    String process(def file) {

        def conf = grailsApplication.config.devProperties
        def newFileName = UUID.randomUUID().toString()

        if (!storeAveable()) {
            throw new Exception("Store not aveible")
        }

        file.transferTo(new File("${conf.storeFolder}\\${newFileName}"))

        indexFile(new File("${conf.storeFolder}\\${newFileName}").getAbsolutePath())
        return newFileName
    }

    byte[] readFile(name) {
        def conf = grailsApplication.config.devProperties
        return new File("${conf.storeFolder}\\${name}").bytes
    }

    private def storeAveable() {
        def conf = grailsApplication.config.devProperties
        def store = new File(conf.storeFolder)
        if (!store.exists()) {
            store.mkdirs()
            if (!store.exists()) return false
        }
        return true
    }

    public def search(queryString) {
        def devPrps = grailsApplication?.config?.devProperties
        if (!queryString || !devPrps) {
            return null
        }

        try {
            def ret = null
            def http = new HTTPBuilder(devPrps.searchUrl)

            http.request(Method.GET, ContentType.JSON) {
                uri.path = devPrps.searchPath
                uri.query = [q: queryString, rows: 100, wt: "json", fl: "id,content_type", indent: "true"]
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

    public def updateIndexedFile(args) {
        def devPrps = grailsApplication.config.devProperties
        if (!args["attachedFileName"]) {
            return
        }
        def jsn = new JSONArray()
        jsn.add(["id": args["attachedFileName"]])
        def props = args.getProperties()

        //       def root = ["id": args["attachedFileName"], "entity_prop_name_s" : ["set":"${args["name"]}"]]
        def root = ["id": args["attachedFileName"]]


        props.each {
            if (!it.key.equals("attachedFileName"))
                root.put("${it.key}_et", it.value)
        }
        def i = root as JSON

        def d = new StringEntity("[${i.toString()}]", org.apache.http.entity.ContentType.APPLICATION_JSON)
        try {
            def ret = null
//            def http = new HTTPBuilder(devPrps.reindexPath, ContentType.JSON)
//            http.post(root , { req ->
//                println(req)
//            })
            def http = new HTTPBuilder(devPrps.searchUrl)
            //+'/'+devPrps.reindexPath)
            http.request(Method.POST, ContentType.JSON) { req ->
                uri.path = devPrps.reindexPath
                uri.query = [commit: true]
                req.setEntity(d)

                response.success = { resp, reader ->
                    println "response status: ${resp.statusLine}"
                    println 'Headers: -----------'
                    resp.headers.each { h ->
                        println " ${h.name} : ${h.value}"
                    }
                    ret = reader
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

    public def indexFile(String filePath) {
        def devPrps = grailsApplication.config.devProperties
        def file = new File(filePath)

        def reqEntity = new InputStreamEntity(new FileInputStream(new File(filePath)), -1)
        reqEntity.setContentType("binary/octet-stream")
        reqEntity.setChunked(false)

        def http = new HTTPBuilder(devPrps.searchUrl)
        http.request(Method.POST) { req ->
            uri.path = devPrps.extractPath
            uri.query = ["literal.id": file.getName(), commit: true]
            requestContentType: "binary/octet-stream"


            req.setEntity(reqEntity)

            response.success = { resp ->
                if (resp.statusLine.statusCode == 200) {
                    // response handling
                }
            }
        }


    }

    def indexEntity(def p) {
        println p
    }


    def deleteEntity(def p) {
        println p
    }
}
