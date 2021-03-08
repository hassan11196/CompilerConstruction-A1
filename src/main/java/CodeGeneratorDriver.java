import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.stream.Collectors;

public class CodeGeneratorDriver {
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        FileReader fileReader;
        try{
            fileReader = new FileReader("input_function/function-factorial.json");
            JSONObject jsonObject = (JSONObject)parser.parse(fileReader);
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("function"));
            System.out.println(jsonObject.get("conditions"));

            ArrayList<String> generated_code_arr = new ArrayList<String>();

            generated_code_arr.add((String) jsonObject.get("return-type"));

            generated_code_arr.add((String) jsonObject.get("function"));

            generated_code_arr.add("(");

            JSONArray parameters_array = (JSONArray) jsonObject.get("parameters");
            Iterator<Object> parameter_iter = parameters_array.iterator();
            JSONObject parameter = (JSONObject) parameter_iter.next();

            generated_code_arr.add((String) parameter.get("type"));
            generated_code_arr.add((String) parameter.get("parameter"));

            while (parameter_iter.hasNext()) {
                JSONObject parameter_local = (JSONObject) parameter_iter.next();
                generated_code_arr.add(",");
                generated_code_arr.add((String) parameter_local.get("type"));
                generated_code_arr.add((String) parameter_local.get("parameter"));

            }
            generated_code_arr.add(")");
            generated_code_arr.add("{");
            generated_code_arr.add("\n");
            generated_code_arr.add((String) jsonObject.get("return-type"));
            generated_code_arr.add("temp");
            generated_code_arr.add(";");
            generated_code_arr.add("\n");

            JSONArray conditions_array = (JSONArray) jsonObject.get("conditions");
            Iterator<Object> condition_iter = conditions_array.iterator();

            JSONObject condition = (JSONObject) condition_iter.next();

            generated_code_arr.add("if");
            generated_code_arr.add("(");
            generated_code_arr.add((String) condition.get("condition"));
            generated_code_arr.add(")");
            generated_code_arr.add("{");
            generated_code_arr.add("\n");
            generated_code_arr.add("\t");
            generated_code_arr.add("temp");
            generated_code_arr.add("=");
            generated_code_arr.add((String) condition.get("action"));
            generated_code_arr.add(";");
            generated_code_arr.add("\n");
            generated_code_arr.add("}");
            generated_code_arr.add("\n");


            while (condition_iter.hasNext()) {
                JSONObject condition_local = (JSONObject) condition_iter.next();

                if(((String) condition_local.get("condition")).length() == 0){
                    generated_code_arr.add("else");
                    generated_code_arr.add("{");
                    generated_code_arr.add("\n");
                    generated_code_arr.add("\t");
                    generated_code_arr.add("temp");
                    generated_code_arr.add("=");
                    generated_code_arr.add((String) condition_local.get("action"));
                    generated_code_arr.add(";");
                    generated_code_arr.add("\n");
                    generated_code_arr.add("}");
                    generated_code_arr.add("\n");
                }else{
                    generated_code_arr.add("else");
                    generated_code_arr.add("if");
                    generated_code_arr.add("(");
                    generated_code_arr.add((String) condition_local.get("condition"));
                    generated_code_arr.add(")");
                    generated_code_arr.add("{");
                    generated_code_arr.add("\n");
                    generated_code_arr.add("\t");
                    generated_code_arr.add("temp");
                    generated_code_arr.add("=");
                    generated_code_arr.add((String) condition_local.get("action"));
                    generated_code_arr.add(";");
                    generated_code_arr.add("\n");
                    generated_code_arr.add("}");
                    generated_code_arr.add("\n");
                }

            }
            generated_code_arr.add("\t");
            generated_code_arr.add("return");
            generated_code_arr.add("temp");
            generated_code_arr.add(";");
            generated_code_arr.add("\n");
            generated_code_arr.add("}");


            System.out.println("Generated Code : ");
            System.out.println("***************");
            String generated_code_string = generated_code_arr.stream().collect(Collectors.joining(" "));
            System.out.println(generated_code_string);
            FileWriter myWriter = new FileWriter("output_code/factorial_Code.c");
            myWriter.write(generated_code_string);
            myWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Failed to load file." + e);
            fileReader = null;
        }
        catch (Exception e){
            System.out.println("any Exception." + e + " " + e.getStackTrace()[0]);
            fileReader = null;
        }

    }
}
