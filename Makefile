install:
	mvn install

run_ideal:
	mvn compile && mvn exec:java -Dexec.mainClass="com.stripe.sample.Ideal"
