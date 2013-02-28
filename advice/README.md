# Advice Library

This is a annotation-based advising mechanism.  It allows the programmers to
annotate methods as being advised.  When such advised method is called, the
associated advice is executed.  By default, the marker is the annotation
@pt.ist.esw.advice.Advised, but the programmer may choose to use any other
annotation.

The programmer provides the advice associated with the annotation, by
providing an implementation of pt.ist.esw.advice.Advice.
