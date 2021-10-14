import java.util.HashMap;
import java.util.Map.Entry;

class Environment {
    private HashMap<String,Double> variableValues = new HashMap<String,Double>();
    private HashMap<String,String> variableOrArray = new HashMap<String,String>();
    public Environment() { }	
    public void setVariable(String name, Double value) {
	variableValues.put(name, value);
    }

    public void variableAssign(String name){
    	variableOrArray.put(name, "variable");
    }

    public void arrayAssign(String name){
    	variableOrArray.put(name, "array");
    }

    public String getType(String name){
    	String type = variableOrArray.get(name);
    	return type;
    }

    
    public Double getVariable(String name){
	Double value = variableValues.get(name); 
	if (value == null) { System.err.println("Variable not defined: "+name); System.exit(-1); }
	return value;
    }
    
    public String toString() {
	String table = "";
	for (Entry<String,Double> entry : variableValues.entrySet()) {
	    table += entry.getKey() + "\t-> " + entry.getValue() + "\n";
	}
	return table;
    }   
}

