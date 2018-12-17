# yak-async

One day along the year 2012, a friend rang me up, very worried. As a trainee at a great company in the country, he had been given the task to complete and set up an existing java website application. He did everything well until he starts running the app. There was a page in the website where the users would be granted to launch a lengthy task by clicking on a button, and the software specifications wanted the developer to launch the task, return a message to the user saying it has been done and he would be given more details by mail at the end of the process. He was not writing a Java ee app and had to make do with what he had at his disposal.


I tried to find a lightweight tool that could help, but in vain. Libraries I found were all messaging tools. I decided to write something more lightweight. In my searches, I fell on some interesting books on amazon that I decided to buy. One of them, SERVICE DESIGN PATTERN by Robert DAIGNEAU, presented some patterns that made me think of the case my friend dealt with. The said patterns are the ‘Asynchronous Response Handler’ and ‘Request/Acknowledge’ patterns. I followed the underlining ideas and decided to let something come out of my mind. As I finally did something that worked, I decided to formalize it and share. I therefore thought of building a class library and called it yak-async.


Yak-async is a lightweight java class library designed to add asynchronous capabilities to a java application. It is an independant library to integrate into the classpath. It lets developer add asynchronous tasks to his application without writing threads, just extending a class and optionally implement a callback class in case the results of the task should be used afterward.
The library puts at the disposal of the developers two major classes, two abstract classes, that they should extend for each asynchronous task they want to implement.  The two major classes are the asynchronous processor and the callback classes. All these are parameterized classes. 


The asynchronous processor class has two parameters, the return type of the background process first, and secondly, the type of the background process’ input. Once the extending class written, precising these different parameters, and the doInBackground method implemented, the remaining effort is just to call the doWork method of the extended processor when the asynchronous task should be executed.


In case the result of the background process is relevant and needs to be handled, the asyncrhonous callback class should be extended, precising the return type of the doInBackground method implemented in the processor as the parameter of the class. The doBefore and handleResult methods of the callback class being written should be implemented. 
In case the callback is available, it is set to the processor just before calling its doWork method.


As an example of how to use the library, let's consider the following case. In our program, each time an object of type Message is built, the program should start a background process and pass it (the message) to the process that will achieve a certain goal while the app is continuing its work.


Let's write a callback that will just print the name of the method that is going to be launched, and print the result at end.



import org.yakoliv.asyncinvoke.pojo.AbstractCallbackAdapter;

public class AsyncMessageProcCallBack extends AbstractCallbackAdapter<String>{


    @Override
    public void doBefore() {
    
        System.out.println("The method to exec: "+getInvokedMethodName());
        
        System.out.println("Starting background process...");
        
    }
    
    @Override
    
    public void handleResult(String t) {
        result = t;
        System.out.println("Operation ended with result: " + t);
    }
    
}


Let's now write the background processor class. Message is supposed to be a POJO.


import org.yakoliv.asyncinvoke.tasks.AsyncProcessor;

import some.package.pojos.Message;

public class MessageProcessor extends AsyncProcessor<String, Message> {
 
      @Override
      protected String doInBackground(Message msg) {
        return process(msg);
      }
      
      private String process(Message m){
        return "done.";
      } 
  
}

The job is finished. Let's now add the asynchronous process to our app.
  
  public static void main(String[] t){
  
  
  ...
  
  //building the message to process in background
  
  Message msg = buildTheMessage();
  
  //preparing the background process
  
  MessageProcessor<String, Message> processor = new MessageProcessor<>();
  
  processor.setCallback(new AsyncMessageProcCallBack());
  
  //launching the background process
  
  processor.doWork(msg);
  
  ...
  
  }
  
  The output will be something like:
  
  The method to exec: String MessageProcessor.doInBackground(Message param);
  
  Starting background process...
  
  Operation ended with result: done.
  

