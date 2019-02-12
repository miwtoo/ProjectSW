# 1 . Find All Type Monitor
```xml
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX ex: <http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#>

SELECT ?subject
WHERE { ?subject rdfs:subClassOf ex:PC_Monitor }
```

----------------------------------------------------

# 2. Find Spec Monitor
```xml
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX ex: <http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#>

SELECT ?monitor
WHERE { ?monitor rdf:type ex:Gamming;
		ex:hasAspectratio ex:16:9;
		ex:hasPaneltype ex:VA;
		ex:hasPort ex:HDMI;
		ex:hasPrice ex:14000;
		ex:hasRefreshRate ex:144;
		ex:hasResolution ex:1920x1080;
		ex:hashasRespontime ex:4ms;
}
```

```xml
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX ex:<http://www.semanticweb.org/user/ontologies/2019/1/untitled-ontology-25#>
SELECT ?name ?price ?port ?aspectratio ?color ?paneltype ?refreshRate ?resolution ?respontime ?size ?features
WHERE { 
	{?name rdf:type ex:Gamming.} 
		OPTIONAL {?name ex:hasPort ?port.}
		OPTIONAL {?name ex:hasPrice ?price.}
		OPTIONAL {?name ex:hasAspectratio ?aspectratio.}
		OPTIONAL {?name ex:hasColor ?color.}
		OPTIONAL {?name ex:hasPaneltype ?paneltype.}
		OPTIONAL {?name ex:hasRefreshRate ?refreshRate.}
		OPTIONAL {?name ex:hasResolution ?resolution.}
		OPTIONAL {?name ex:hasRespontime ?respontime.}
		OPTIONAL {?name ex:hasSize ?size.}
		OPTIONAL {?name ex:hasfeatures ?features.}
		
}
```


