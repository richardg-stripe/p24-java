install:
	mvn install

run_ideal:
	mvn compile && mvn exec:java -Dexec.mainClass="com.stripe.sample.Ideal"

run_upi:
	mvn compile && mvn exec:java -Dexec.mainClass="com.stripe.sample.UPI"

run_boleto:
	mvn compile && mvn exec:java -Dexec.mainClass="com.stripe.sample.Boleto"
