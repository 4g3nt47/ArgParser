package com.umarabdul.argparser;

import java.util.HashMap;


/**
* A simple class for parsing command line arguments. It support both positional and keyword arguments.
* A keyword argument can be obtained using up to two (2) names by creating an alias.
*
* @author Umar Abdul
* @version 1.0
* @since 2020
*/

public class ArgParser{

  String[] args = null; // orginal arguments passed.
  HashMap<String, String> kwargs = null; // keyword arguments.
  HashMap<String, String> alias = null; // aliases for keyword arguments.
  HashMap<String, String> defaults = null; // default values for keyword arguments.

  /**
  * Constructor.
  * @param args Array of command line arguments to parse.
  */
  public ArgParser(String[] args){

    this.args = args;
    kwargs = new HashMap<String, String>();
    alias = new HashMap<String, String>();
    defaults = new HashMap<String, String>();
    // Extract keyword arguments.
    boolean skipNext = false;
    String name = null;
    String value = null;
    for (int i = 0; i < args.length; i++){
      if (skipNext){
        skipNext = false;
        continue;
      }
      name = args[i];
      if (name.startsWith("--") || name.startsWith("-")){
        if (name.startsWith("--"))
          name = name.substring(2);
        else
          name = name.substring(1);
        if (name.contains("=")){
          int pos = name.indexOf("=");
          value = name.substring(pos+1);
          name = name.substring(0, pos);
          kwargs.put(name, value);
        }else{
          if ((i+1) < args.length){
            kwargs.put(name, args[i+1]);
            skipNext = true;
          }
        }
      }
    }
  }
  
  /**
  * Obtain total number of arguments.
  * @return Number of arguments available.
  */
  public int length(){
    return args.length;
  }

  /**
  * Obtain number of keyword arguments parsed.
  * @return Number of keyword arguments.
  */
  public int kwargsLength(){
    return kwargs.size();
  }

  /**
  * Set an alias for a keyword argument.
  * @param kwarg Name of keyword argument.
  * @param alname Alias to add.
  */
  public void setAlias(String kwarg, String alname){
    alias.put(kwarg, alname);
  }

  /**
  * Set default value to use for a keyword argument if not found.
  * @param name Name of keyword argument.
  * @param value Default value to assign.
  */
  public void setDefault(String name, String value){
    defaults.put(name, value);
  }

  /**
  * Test if the given argument exists.
  * @param arg Argument to check.
  * @return {@code true} on success.  
  */
  public boolean hasArg(String arg){

    for (String s : args){
      if (s.equals(arg))
        return true;
    }
    for (String s : kwargs.keySet()){
      if (s.equals(arg))
        return true;
    }
    return false;
  }

  /**
  * Test if there is a keyword argument with the given name.
  * @param name Name of the keyword argument.
  * @return {@code true} on success.
  */
  public boolean hasKWarg(String name){
    return (getString(name) != null ? true : false);
  }

  /**
  * Obtain the original arguments array passed to the constructor.
  * @return Array of arguments.
  */
  public String[] getArgs(){
    return args;
  }

  /**
  * Obtain a HashMap of keyword arguments.
  * @return HasMap of command line keyword arguments.
  */
  public HashMap<String, String> getKWargs(){
    return kwargs;
  }

  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public String getString(int index){
    return ((index >= 0 && index < length()) ? args[index] : null);
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public String getString(String name){

    // Check given arguments.
    String value = kwargs.get(name);
    if (value != null)
      return value;
    value = kwargs.get(alias.get(name));
    if (value != null)
      return value;
    // Check defaults.
    value = defaults.get(name);
    if (value != null)
      return value;
    value = defaults.get(alias.get(name));
    return value;
  }

  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public boolean getBoolean(int index){
    return Boolean.valueOf(getString(index));
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public boolean getBoolean(String name){
    return Boolean.valueOf(getString(name));
  }
  
  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public int getInt(int index){
    return Integer.valueOf(getString(index));
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public int getInt(String name){
    return Integer.valueOf(getString(name));
  }

  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public long getLong(int index){
    return Long.valueOf(getString(index));
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public long getLong(String name){
    return Long.valueOf(getString(name));
  }

  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public float getFloat(int index){
    return Float.valueOf(getString(index));
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public float getFloat(String name){
    return Float.valueOf(getString(name));
  }

  /**
  * Obtain argument by index.
  * @param index Index of argument.
  * @return Argument at the given index.
  */
  public double getDouble(int index){
    return Double.valueOf(getString(index));
  }

  /**
  * Obtain value of keyword argument.
  * @param name Name of keyword argument.
  * @return Value of the keyword argument.
  */
  public double getDouble(String name){
    return Double.valueOf(getString(name));
  }

}
