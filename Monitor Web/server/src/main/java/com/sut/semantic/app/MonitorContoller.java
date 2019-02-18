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
    public Set<String> ListFeature= new HashSet<String>();
    private String feature;


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
        this.type=type;

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

                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" + "SELECT  (str(?monitor) as ?Monitor) (str(?resu) as ?Reso)"
                    + "where {{?monitor rdf:type ex:"+type+".}"
                    + "OPTIONAL {?monitor ex:hasResolution ?resu.}"
                    + "}";
            //System.out.println(queryString);
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

}