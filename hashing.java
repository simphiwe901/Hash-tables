import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.Math.*;
import java.text.DecimalFormat;

/** Hashing class consists of four hashing functions
  * Each function returns unique hash values of a String
  * @author Simphiwe Mchunu
*/


public class hashing{
  public static final int tablesize = 20011;
  /**hash1 method of type hashes a string stored in an array
    *@param key takes a string to be hashed
    *@return i return 0 if no string found, 1 if it exists
    *
  */
  public static int hash1(String key){
    int i =0;
    if(key.equals("")){
      return i;
    }
    else{
      i = 1;
      return i;
    }
}
/**hash2 method of type hashes a string by its unicode values
  *stores hashvalue, restrict size of the hashtable by tablesize
  * @param key takes a string to be hashed
  *@return hashVal returns hashvalue of a string
*/
public static int hash2(String key){
  int hashVal = 0;
  for(int i=0;i<key.length();i++){
    hashVal += key.charAt(i);}
  hashVal %= tablesize;
  if(hashVal < 0){
    hashVal+=tablesize;
  }
  return hashVal;
}

/** hash3 method of type int hashes a string by its unicode values
  *modify hashVal by some factor, stores unicode values
  *@param key takes in string to be hashed
  *@return hashVal return hashvalue of a string
*/
public static int hash3(String key){
  int hashVal = 0;
  for(int i=0;i<key.length();i++){
    hashVal = (37*hashVal) + key.charAt(i);
  }
  hashVal%=tablesize;
  if(hashVal<0){
    hashVal+=tablesize;
  }
  return hashVal;
}
/** hash4 method of type int hashes a string and gets hashCode of
  * of that string
  *modify hashVal by some factor, add hashCode
  *@param key takes in string to be hashed
  *@return hashVal return absolute hashvalue of a string
*/
public static int hash4(String key){
  int prime = 31;
  int hashVal = 1;
  hashVal = prime * hashVal + ((key == null) ? 0 : key.hashCode());
  return Math.abs(hashVal);
}
/** Main method of type void prints the output of
  * all hashvalues, calculate entropy(expected values)
  * for all hash functions
  *@param args Unused
  *@exception IOException throws exception
*/
 public static void main(String[] args) throws IOException {
  Hashtable<String,Integer> table = new Hashtable<String,Integer>();
  ArrayList<String> mlist = new ArrayList<String>();
  ArrayList<Integer> mlist2 = new ArrayList<Integer>();
  ArrayList<Integer> hash_values1 = new ArrayList<Integer>();
  ArrayList<Integer> entropy_values2 = new ArrayList<Integer>();
  int index = 0;
  Scanner file = new Scanner(new FileInputStream("testdata"));
  while(file.hasNextLine()){
    String line = file.nextLine();
      String fullname = line.substring((line.lastIndexOf("|"))+1);
      table.put(fullname,index);
      mlist.add(fullname);
      index++;

    }
    /*Enumeration e = table.keys();
    while (e.hasMoreElements()) {
    String key = (String) e.nextElement();
  }**/

for(int i =0;i<mlist.size();i++){
  hash_values1.add(hash4(mlist.get(i)));
  System.out.println(hash4(mlist.get(i)));
  //System.out.println(hash3(mlist.get(i)));
  //entropy_values1.add(hash1(mlist.get(i)));
  //entropy_values2.add(hash1(mlist.get(i)));
  //System.out.println(entropy_values.get(i));
}

//insert number of occurences of each duplicate
Map<Integer,Integer> count = new HashMap<Integer,Integer>();
  for (int ind : hash_values1) {
    if (count.containsKey(ind)) {
        count.put(ind, count.get(ind) + 1);
    } else {
        count.put(ind, 1);
    }
}

for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
    //int s  = entry.getValue();
    mlist2.add(entry.getValue());
  //for(int s=0;s<mlist2.size();s++){
      //System.out.println(mlist2.get(s));
    //}
  //System.out.println(entry.getKey() + " = " + entry.getValue());
}
  //entropy calculation
  double partial_entropy = 0;
  for(int j=0;j<mlist2.size();j++){
    double value = (double)mlist2.get(j);
    //System.out.println("Value: "+value);
    double probability = value/10000;
    //System.out.println("probability: "+probability);
    BigDecimal dec_val = BigDecimal.valueOf(probability);
    //System.out.println("Decimal Value: "+dec_val.toPlainString());
    double formula = -probability*(Math.log(probability));
    //BigDecimal final_value = BigDecimal.valueOf(formula);
    BigDecimal final_value2 = BigDecimal.valueOf(formula);
    partial_entropy =Math.abs(partial_entropy+formula);

    //System.out.println("partial_entropy: "+BigDecimal.valueOf(partial_entropy).toPlainString());
    //System.out.println(probability);
    //System.out.println(mlist2.get(j));
  }
  BigDecimal final_entropy = BigDecimal.valueOf(partial_entropy);
  if(partial_entropy==0){
      System.out.println("Expected value: "+final_entropy.toPlainString()+" => Certainty");
  }
  else{
    DecimalFormat formater = new DecimalFormat("0.00000");
    //System.out.println("Expected value: "+formater.format(final_entropy)+" => Uncertainty");
  }
  }
}
