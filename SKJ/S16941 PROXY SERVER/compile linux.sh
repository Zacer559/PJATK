#!/bin bash

cd Source
javac Main.java
javac Proxy.java
javac Request2.java

mv Main.class ../Binary/Main.class
mv Proxy.class ../Binary/Proxy.class
mv Request2.class ../Binary/Request2.class
