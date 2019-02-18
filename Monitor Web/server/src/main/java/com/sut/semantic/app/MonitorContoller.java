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
    public Set<String> ListPort = new HashSet<String>();
    public Set<String> ListNames = new HashSet<String>();
    public Set<String> ListSize = new HashSet<String>();
    public Set<String> ListRatio = new HashSet<String>();
    public Set<String> ListPrice = new HashSet<String>();
    public Set<String> ListColoer = new HashSet<String>();
    public Set<String> ListPanal = new HashSet<String>();/// for name list

    @GetMapping("/type")
    public ResponseEntity<Map<String, Object>> getType() {
        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

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

    @GetMapping("/panel/{type}")
    public ResponseEntity<Map<String, Object>> getPanel(@PathVariable String type) {
        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Type");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                +"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                +"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT DISTINCT (str(?x) as ?Component) "
                + "where {?y rdf:type ex:"+type +""
                +";ex:hasPort ?x."
                + " }";

                System.out.println(queryString);

            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                String Component = soln.getLiteral("Component").getString();
                // test --
                System.out.println("Monitor " + Component.toString().split("#")[1]);
                ListPanal.add(Component.toString().split("#")[1]);

                RDFNode x = soln.get("Propertyval");

                String xx = String.valueOf(x);

                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);

                String xs = xxx.toString();

            }
            Map<String, Object> json = new HashMap<String, Object>();

            //ListType.remove("General");
            json.put("data", ListPanal);
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

    @GetMapping("/{type}")
    public ResponseEntity<Map<String, Object>> findType(@PathVariable String type) {
        Map<String, Object> json = new HashMap<String, Object>();

        Set<String> ListMonitorImg = new HashSet<String>();
        Set<String> ListMonitorName = new HashSet<String>();
        Set<Map> ListMonitor = new HashSet<Map>();

        
        try {
            // OntModel model = OpenOWL.OpenConnectOWL();

            System.out.println("Get Monitor");
            String queryString;
            queryString = "PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#> "
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" 
                    + "SELECT  (str(?name) as ?Name) (str(?img) as ?Img) (str(?price) as ?Price)"
                    + "where {{?name rdf:type ex:"+type+"."
                    + "?name rdfs:seeAlso ?img."
                    + "?name ex:hasPrice ?price}"
                    + "}";
            //System.out.println(queryString);
            com.hp.hpl.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString); // all method ExecSparQl from
                                                                                       // OpenOWL class

            
            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                String img = soln.getLiteral("Img").getString();
                String name = soln.getLiteral("Name").getString();
                String price = soln.getLiteral("Price").getString();
                // test --
                System.out.println("Name " + name.toString());

                Map<String, String> arrayList = new HashMap<String, String>();
                arrayList.put("img", img.toString());
                arrayList.put("name", name.toString().split("#")[1]);
                arrayList.put("price", price.toString().split("#")[1]);
                
                
                ListMonitor.add(arrayList);

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