COMPILE, INSTALL, and EXECUTE:

mvn -DskipTests clean install
mvn exec:java -Dexec.mainClass="programs.KMeansCluster" -Dexec.args="src/main/java/programs/CombinedLogs2.csv Dataset1.csv"


TURN ON HADOOP DAEMONS:

ssh localhost
start-all.sh


DUMP THE CLUSTERS:

(add to hadoop file system para mabasa sya sa mahout clusterdumper)
hadoop fs -put clustering/output/clusteredPoints
hadoop fs -put clustering/output/clusters-52-final (depende sa last na file na mabuhat)

(execute)
mahout clusterdump --input /user/hduser/clusters-43-final -of TEXT --pointsDir /user/hduser/clusteredPoints --output datasetresult.txt (machange ang format: TEXT,CSV,GRAPH_ML)
