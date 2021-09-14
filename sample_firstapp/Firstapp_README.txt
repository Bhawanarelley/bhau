
   StreamBase Documentation
   
First Application Sample

About This Sample

   This sample, firstapp.sbapp, demonstrates a StreamBase application
   with one notable characteristic: it is easy to create. You may have
   already created it yourself, if you performed the tutorial in the
   Getting Started Guide.
   
   The application is modest in functionality, but building it teaches
   you some important fundamentals including:
     * The general design process of defining input streams, schemas,
       operators, and output streams
     * Adding and connecting components in an EventFlow
     * Typechecking operators to ensure that streaming data can flow
       through the application without errors
     * Features of the SB Authoring and SB Test/Debug perspectives
     * Running the application in StreamBase Studio
     * Creating a feed simulator to enqueue test data automatically to
       the running application
       
   This topic describes how to run firstapp.sbapp. For detailed
   information about this and other samples, please see the Samples Guide
   in the StreamBase Studio Help.
   
Running This Sample in StreamBase Studio

   To start this sample:
    1. In the Project Explorer view, open the sample you just loaded.
    2. Open the src/main/eventflow/packageName folder.
    3. Open the firstapp.sbapp file and click the Run button. This opens
       the SB Test/Debug perspective and starts the module.
    4. When done, press F9 or click the Terminate EventFlow Fragment
       button.
       
   The server is ready to receive input. See the following sections to
   manually enqueue data into this sample. For additional enqueuing
   instructions, see the Getting Started Guide.
   
Enqueuing Data Manually

   To enqueue data manually in Studio:
    1. Open the Manual Input view.
    2. In the Output Streams view, make sure that the Output stream
       option is set to All streams.
    3. Enter IBM in the symbol field, and 10000 in the quantity field.
    4. Click Send Data to send this tuple to the application.
    5. Observe the output in the Output Streams view:
       Because the quantity satisfies the Filter predicate, your tuple is
       passed through on the BigTrades stream.
    6. Enter 100 in the quantity field and click Send Data again.
    7. Observe that this time, your output appears in the AllTheRest
       output stream.
       Why? Because the quantity is below the predicate's threshold of
       10000.
    8. Enter different values above and below the threshold, and see how
       your application responds. Try entering an invalid quantity such
       as alpha.
       
Enqueuing Data Using a Feed Simulation

   To enqueue test data using any of the three feed simulations:
    1. Open the Feed Simulations view.
    2. In the Feed Simulations view, select a feed simulation.
    3. Click Run.
       The Input Streams view displays generated tuples enqueued from
       your Feed Simulation. At the same time, the Output Streams view
       begins displaying tuples on the two output streams.
    4. If you chose firstapp-enum.sbfs, let it run for five or ten
       seconds, then click Stop (otherwise it runs continuously). The
       other two feed simulators run to completion, but feel free to stop
       them when you have enough data.
       Note that stopping the feed simulation does not stop the
       application.
    5. Observe the results in the Input Streams and Output Streams views.
       (If necessary, resize the views so that you can see their contents
       clearly.)
       
Tip

   Click a row to display its field summary below the list, as shown in
   the next figure.
       You should see trades values that are both above and below the
       threshold of 10000 that was set in your Filter operator.
    6. When done, press F9 or click the Terminate EventFlow Fragment
       button.
       
This Sample's Files

   In addition to the application, the installed First Sample includes
   two additional feed simulations. Thus, there are three simulations in
   total, each demonstrating a different way of modeling test data:
   
   firstapp-enum.sbfs
          Also featured in the tutorial, generates values from a list of
          symbols and quantities, in random order. The enumeration also
          specifies the weights of the quantities.
          
   firstapp-trace1.sbfs
          Uses an external comma-separated value data file,
          firstapp-trace1.csv, to explicitly list the order and values of
          tuples.
          
   firstapp-trace2.sbfs
          Uses values specified in another external comma-separated value
          data file, firstapp-trace2.csv, and also uses relative
          timestamps to control the rate of enqueuing.
          
Sample Location

   When you load the sample into StreamBase Studio, Studio copies the
   sample project's files to your Studio workspace, which is normally
   part of your home directory, with full access rights.
   
Important

   Load this sample in StreamBase Studio, and thereafter use the Studio
   workspace copy of the sample to run and test it, even when running
   from the command prompt.
   
   Using the workspace copy of the sample avoids permission problems. The
   default workspace location for this sample is:
studio-workspace/sample_firstapp

   See Default Installation Directories in the Installation Guide for the
   default location of studio-workspace on your system.
   
   Copyright © 2004-2020 TIBCO Software Inc. All rights reserved.
