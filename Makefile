install:
	mvn install

run_p24:
	mvn compile && mvn exec:java -Dexec.mainClass="com.stripe.sample.P24"
