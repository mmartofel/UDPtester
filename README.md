Spring Boot program to test UDP connections behavior related to nf_conntrack.

How to run:

1. Clone this Git repository to your local machine:

git clone https://github.com/mmartofel/UDPtester.git

2. Build the source:

cd ./UDPtester
./mvnw package

3. Run your app:

java -jar ./target/UDPtester-0.0.1-SNAPSHOT.jar 20000 20010

Application will open UDP ports for range 20000 - 20010 (if you forget to provide command line arguments, default values would be taken). You can check open ports with:

netstat -p | grep 200

(master⚡)» netstat -n | grep 200                                                                       
udp46      0      0  *.20010                *.*
udp46      0      0  *.20009                *.*
udp46      0      0  *.20008                *.*
udp46      0      0  *.20007                *.*
udp46      0      0  *.20006                *.*
udp46      0      0  *.20005                *.*
udp46      0      0  *.20004                *.*
udp46      0      0  *.20003                *.*
udp46      0      0  *.20002                *.*
udp46      0      0  *.20001                *.*
udp46      0      0  *.20000                *.*

Type Ctrl+C to stop application. 

Check connection tracking behavior:

$ netstat -tn | awk '{n[$6]++} END { for(k in n) { print k, n[k]; }}'

$ conntrack -L | awk '{n[$4]++}; END {for(k in n) { print k, n[k]; }}'
