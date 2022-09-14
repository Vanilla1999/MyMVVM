package com.example.mymvvm;



public class test {
    public static void main(String[] args) {
      for(int i = 1 ; i<10000; i*=2){
          if(i/3>13){
              break;
          }else{
              System.out.println(" " + i);
          }
      }
    }
}