# projects_enrichment_freme
Enrich akstem resources with projects from freme services


Installation
0 download the java code from git
1 run : “mvn package” in the directory of downloaded code where the *pom.xml* lives
2 then you can find in target folder the jar
3 run : java -jar <java_name.jar> <projects path without TRAILING slash> <CSV PATH with TRAILING slash> <DATASET>

Run
 Example: java -jar gr.agroknow.projects.jar /projects /CSVPATH/ ParamManager.java
 Note: The input folder is also the output that means that are no new files generated.
