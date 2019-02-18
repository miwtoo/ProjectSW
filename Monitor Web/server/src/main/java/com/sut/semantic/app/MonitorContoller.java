package com.sut.semantic.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import riotcmd.json;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MonitorContoller {
    public Set<String> ListType = new HashSet<String>();
    private String type;
    public Set<String> ListPort = new HashSet<String>();
    private String port;
    public Set<String> ListNames = new HashSet<String>();
    private String name;
    public Set<String> ListSize = new HashSet<String>();
    private String size;
    public Set<String> ListRatio = new HashSet<String>();
    private String ratio;
    public Set<String> ListPrice = new HashSet<String>();
    private String price;
    public Set<String> ListColoer = new HashSet<String>();
    private String color;
    public Set<String> ListPanal = new HashSet<String>();/// for name list
    private String panal;
    public Set<String> ListResolution = new HashSet<String>();
    private String reso;
    public Set<String> ListRespon = new HashSet<String>();
    private String respon;
    public Set<String> ListAspect = new HashSet<String>();
    private String aspect;
    public Set<String> ListRefresh = new HashSet<String>();
    private String refresh;
    public Set<String> ListFeature = new HashSet<String>();
    private String feature;

    @GetMapping("/{type}/{reso}/{panal}/{aspect}/{port}/{refresh}/{respon}/{size}/{feature}/{color}/{price}")
    public ResponseEntity<Map<String, Object>> findMonitor(@PathVariable String type, @PathVariable String reso, @PathVariable String panal, @PathVariable String aspect, @PathVariable String port, @PathVariable String refresh, @PathVariable String respon, @PathVariable String size, @PathVariable String feature, @PathVariable String color, @PathVariable String price) {

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();
        ArrayList<Map> monitors = new ArrayList<Map>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get All");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?monitor) as ?Monitor) (str(?img) as ?Img)"
                    + "where {{?monitor rdf:type ex:"+ type + ". "
                    + "?monitor rdfs:seeAlso ?img." 
                    + "?monitor ex:hasResolution ex:" + reso + "."
                    + "?monitor ex:hasPaneltype ex:" + panal + "."
                    + "?monitor ex:hasAspectratio ex:" + aspect + "."
                    + "?monitor ex:hasPort ex:"+port+" ."
                    + "?monitor ex:hasRefreshRate ex:"+refresh+"."
                    + "?monitor ex:hasRespontime ex:"+respon+"."
                    + "?monitor ex:hasSize ex:"+size+"."
                    //+ "?monitor ex:hasfeatures ex:"+feature+"."
                    //+ "?monitor ex:hasColor ex:"+color+"."
                    + "?monitor ex:hasPrice ex:"+price+".}"
                    + "}";
             System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Monitor").getString();
                String img = soln.getLiteral("Img").getString();

                System.out.println("name " + name.toString().split("#")[1]);

                Map<String, String> monitor = new HashMap<String, String>();

                monitor.put("name", name.toString().split("#")[1]);
                monitor.put("img", img.toString());

                monitors.add(monitor);

            }

            json.put("data", monitors);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/type")
    public ResponseEntity<Map<String, Object>> getType() {
        try {
            // OntModel model = OpenOWL.OpenConnectOWL();
            ListType.clear();
            System.out.println("Get Type");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" + "SELECT  (str(?x) as ?Component) "
                    + "where { ?x rdfs:subClassOf ex:PC_Monitor." + " }";

            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                String Component = soln.getLiteral("Component").getString();
                // test --
                System.out.println("Monitor " + Component.toString().split("#")[1]);
                ListType.add(Component.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }
            Map<String, Object> json = new HashMap<String, Object>();

            ListType.remove("PC_Monitor");
            json.put("data", ListType);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
            // ComponentList.removeAllItems(); // combobox nameList
            // for (int i = 0; i < ListComponent.size(); i++) {
            // // System.out.println(ListComponent.get(i));
            // }
        } catch (Exception ex) {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            // ex.printStackTrace();
        }
        // return "test1";
    }

    @GetMapping("/reso/{type}")
    public ResponseEntity<Map<String, Object>> findResolution(@PathVariable String type) {
        this.type = type;

        Map<String, Object> json = new HashMap<String, Object>();

        Set<String> ListMonitorImg = new HashSet<String>();
        Set<String> ListMonitorName = new HashSet<String>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Reso");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "

                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?monitor) as ?Monitor) (str(?resu) as ?Reso)" + "where {{?monitor rdf:type ex:"
                    + type + ".}" + "OPTIONAL {?monitor ex:hasResolution ?resu.}" + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Reso").getString();
                // test --
                System.out.println("Reso " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));

            // ComponentList.removeAllItems(); // combobox nameList
            // for (int i = 0; i < ListComponent.size(); i++) {
            // // System.out.println(ListComponent.get(i));
            // }
        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            // ex.printStackTrace();
        }
        // return "test1";
    }

    @GetMapping("/panal/{reso}")
    public ResponseEntity<Map<String, Object>> findPanal(@PathVariable String reso) {
        this.reso = reso;

        Map<String, Object> json = new HashMap<String, Object>();

        Set<String> ListMonitorImg = new HashSet<String>();
        Set<String> ListMonitorName = new HashSet<String>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Panal");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "

                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?monitor) as ?Monitor) (str(?panal) as ?Panal)" + "where {{?monitor rdf:type ex:"
                    + type + ".}" + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ?panal.}" + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
            // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Panal").getString();
                // test --
                System.out.println("Panal " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));

            // ComponentList.removeAllItems(); // combobox nameList
            // for (int i = 0; i < ListComponent.size(); i++) {
            // // System.out.println(ListComponent.get(i));
            // }
        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            // ex.printStackTrace();
        }
        // return "test1";
    }

    @GetMapping("/aspect/{panal}")
    public ResponseEntity<Map<String, Object>> findAspect(@PathVariable String panal) {
        this.panal = panal;

        Map<String, Object> json = new HashMap<String, Object>();

        Set<String> ListMonitorImg = new HashSet<String>();
        Set<String> ListMonitorName = new HashSet<String>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Aspect");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "

                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?monitor) as ?Monitor) (str(?aspect) as ?Aspect)" + "where {{?monitor rdf:type ex:"
                    + type + ".}" + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ?aspect.}" + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
            // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Aspect").getString();
                // test --
                System.out.println("Aspect " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));

            // ComponentList.removeAllItems(); // combobox nameList
            // for (int i = 0; i < ListComponent.size(); i++) {
            // // System.out.println(ListComponent.get(i));
            // }
        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            // ex.printStackTrace();
        }
        // return "test1";
    }

    @GetMapping("/port/{aspect}")
    public ResponseEntity<Map<String, Object>> findPort(@PathVariable String aspect) {
        this.aspect = aspect;

        Map<String, Object> json = new HashMap<String, Object>();

        Set<String> ListMonitorImg = new HashSet<String>();
        Set<String> ListMonitorName = new HashSet<String>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Aspect");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "

                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?monitor) as ?Monitor) (str(?port) as ?Port)" + "where {{?monitor rdf:type ex:"
                    + type + ".}" + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ?port .}" + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
            // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Port").getString();
                // test --
                System.out.println("Port " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));

            // ComponentList.removeAllItems(); // combobox nameList
            // for (int i = 0; i < ListComponent.size(); i++) {
            // // System.out.println(ListComponent.get(i));
            // }
        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            // ex.printStackTrace();
        }
        // return "test1";
    }

    @GetMapping("/refreshRate/{port}")
    public ResponseEntity<Map<String, Object>> findRefrest(@PathVariable String port) {
        this.port = port;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Aspect");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?refreshRate) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ex:"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ?refreshRate.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/respon/{refresh}")
    public ResponseEntity<Map<String, Object>> findRespon(@PathVariable String refresh) {
        this.refresh = refresh;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Aspect");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?respontime) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ex:"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ex:"+refresh+".}"
                    + "OPTIONAL {?monitor ex:hasRespontime ?respontime.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/size/{respon}")
    public ResponseEntity<Map<String, Object>> findSize(@PathVariable String respon) {
        this.respon = respon;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Aspect");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?size) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ?"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ex:"+refresh+".}"
                    + "OPTIONAL {?monitor ex:hasRespontime ex:"+respon+".}"
                    + "OPTIONAL {?monitor ex:hasSize ?size.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/future/{size}")
    public ResponseEntity<Map<String, Object>> findFuture(@PathVariable String size) {
        this.size = size;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Future");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?features) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ex:"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ex:"+refresh+".}"
                    + "OPTIONAL {?monitor ex:hasRespontime ex:"+respon+".}"
                    + "OPTIONAL {?monitor ex:hasSize ex:"+size+".}"
                    + "OPTIONAL {?monitor ex:hasfeatures ?features.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/color/{feature}")
    public ResponseEntity<Map<String, Object>> findColor(@PathVariable String feature) {
        this.feature = feature;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get color");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?color) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ex:"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ex:"+refresh+".}"
                    + "OPTIONAL {?monitor ex:hasRespontime ex:"+respon+".}"
                    + "OPTIONAL {?monitor ex:hasSize ex:"+size+".}"
                    + "OPTIONAL {?monitor ex:hasfeatures ex:"+feature+".}"
                    + "OPTIONAL {?monitor ex:hasColor ?color.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/price/{color}")
    public ResponseEntity<Map<String, Object>> findPrice(@PathVariable String color) {
        this.color = color;

        Map<String, Object> json = new HashMap<String, Object>();
        Set<String> ListMonitor = new HashSet<String>();

        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get price");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT  (str(?price) as ?Property)" 
                    + "where {{?monitor rdf:type ex:"+ type + ".}" 
                    + "OPTIONAL {?monitor ex:hasResolution ex:" + reso + ".}"
                    + "OPTIONAL {?monitor ex:hasPaneltype ex:" + panal + ".}"
                    + "OPTIONAL {?monitor ex:hasAspectratio ex:" + aspect + ".}"
                    + "OPTIONAL {?monitor ex:hasPort ex:"+port+" .}"
                    + "OPTIONAL {?monitor ex:hasRefreshRate ex:"+refresh+".}"
                    + "OPTIONAL {?monitor ex:hasRespontime ex:"+respon+".}"
                    + "OPTIONAL {?monitor ex:hasSize ex:"+size+".}"
                    + "OPTIONAL {?monitor ex:hasfeatures ex:"+feature+".}"
                    + "OPTIONAL {?monitor ex:hasColor ex:"+color+".}"
                    + "OPTIONAL {?monitor ex:hasPrice ?price.}"
                    + "}";
            // System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();

                String name = soln.getLiteral("Property").getString();
                System.out.println("Property " + name.toString().split("#")[1]);
                ListMonitor.add(name.toString().split("#")[1]);

            }

            json.put("data", ListMonitor);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));


        } catch (Exception ex) {
            json.put("message", "error");
            json.put("error", ex);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
        }
    }

}