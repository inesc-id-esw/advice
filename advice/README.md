# Advice Library

This is a annotation-based advice-like mechanism.  It allows the programmers
to annotate methods as being advised.  Then, when such advised method is
called, the associated advice is executed, **instead** of the method.  It is
up to the advice to decide whether to actually call the method.  For this to
work as intended the programmer needs to select the annotation to use as an
advice and:

  1. run the `pt.ist.esw.advice.GenerateAnnotationInstance` to generate the
  AnnotationInstance class that represents the selected annotation instance.
  This class will be used internally.
  
  2. Define the `pt.ist.esw.advice.clientimpl.DefaultAdviceFactory` which
  takes the selected annotation instance and should return the
  `pt.ist.esw.advice.Advice` that will be called when an advised method is
  called.
  
  3. Run `pt.ist.esw.advice.ProcessAtomicAnnotations` to post-process the
  compiled classes.  This will search the presence of the advised annotation
  and replace the original method with another method that runs the advice.
  It also creates a callable to the original advised method that is given to
  the execution of the Advice (in the `perform` method).
  
