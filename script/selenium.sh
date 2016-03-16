#!/bin/bash

#export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_05;

cd ../; 
/home/alci/Java/IDE/NetBeans/netbeans-8.0/java/maven/bin/mvn install -Dmaven.test.skip=true
/home/alci/Java/IDE/NetBeans/netbeans-8.0/java/maven/bin/mvn -Dtest=br.com.modulo.ModuloAdmistracaoTests surefire:test
