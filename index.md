---
layout: page
---

# Advice Library

## Maven Repository

Maven repository top-level directory is [here](maven-repo/).

To use software from this repository in your Maven projects, add the following
configuration to the appropriate section in either your project's `pom.xml`
file or your Maven global `settings.xml` file.


    <repositories>
        <repository>
            <id>advice-repository</id>
            <url>http://inesc-id-esw.github.com/advice/maven-repo/</url>
        </repository>
    </repositories>

## Description and Usage

This is a annotation-based advice-like mechanism.  It allows the programmers
to annotate methods as being advised.  Then, when such advised method is
called, the associated advice is executed, **instead** of the method.  It is
up to the advice to decide whether to actually call the method.  For this to
work as intended the programmer needs to select the annotation to use as an
advice and:

  1. run the `pt.ist.esw.advice.GenerateAnnotationInstance` to generate the
  AnnotationInstance class that represents the selected annotation instance.
  This class will be used internally.
  
  2. Define a `pt.ist.esw.advice.AdviceFactory`, which takes the selected
  annotation instance and should return the `pt.ist.esw.advice.Advice` that
  will be called when an advised method is called.
  
  3. Run `pt.ist.esw.advice.ProcessAnnotations` to post-process the
  compiled classes.  This will search the presence of the advised annotation
  and replace the original method with another method that runs the advice.
  It also creates a callable to the original advised method that is given to
  the execution of the Advice (in the `perform` method).
  
To use this library, put this in your maven project.

    <dependencies>
        <dependency>
            <groupId>pt.ist.esw</groupId>
            <artifactId>advice-program</artifactId>
            <version>1.7</version>
        </dependency>
    </dependencies>
    
Alternatively, you can browse the files in the repository and directly
download the latest JAR file available.

In Maven just add the following, or equivalent, to the `<plugins>` section in
your POM.

### To generate the annotation instance

        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>${version.maven.exec-plugin}</version>
            <executions>
                <execution>
                    <id>generate-annotation-instance</id>
                    <phase>process-classes</phase>
                    <goals>
                        <goal>java</goal>
                    </goals>
                    <configuration>
                        <mainClass>pt.ist.esw.advice.GenerateAnnotationInstance</mainClass>
                        <arguments>
                            <argument>${annotation.name}</argument>
                            <argument>${project.build.outputDirectory}</argument>
                        </arguments>
                    </configuration>
                </execution>
            </executions>
        </plugin>


### To process the uses of the annotation

    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>${version.maven.exec-plugin}</version>
            <execution>
                <id>process-annotations</id>
                <phase>process-classes</phase>
                <goals>
                    <goal>java</goal>
                </goals>
                <configuration>
                    <mainClass>pt.ist.esw.advice.ProcessAnnotations</mainClass>
                    <arguments>
                        <argument>-a</argument>
                        <argument>${annotation.name}</argument>
                        <argument>-f</argument>
                        <argument>${advice.factory.name}</argument>
                        <argument>${project.build.outputDirectory}</argument>
                    </arguments>
                </configuration>
            </execution>
        </executions>
    </plugin>

Replace `${annotation.name}` the name fully-qualified class name of your
annotation.

Replace `${advice.factory.name}` the fully-qualified class name of your
`AdviceFactory`.  This is an optional parameter.  It should be absent when
either:

  * The default factory is provided: `pt.ist.esw.advice.impl.ClientAdviceFactory`

  * The annotation itself defines the following property: `Class<? extends
  AdviceFactory<T>> adviceFactory()` where `T` is the annotation's type.


The annotation processor will lookup the required factory in the following
order:

  1. The `adviseFactory()` property
  2. The class provided in the argument `-f`
  3. The default `ClientAdviceFactory`

At runtime the code that had the processed annotation will depend on it the
Advice lib.  To depend only on the minimum set of classes you can change your
runtime dependencies down to `advice-runtime`, which includes less
dependencies than `advice-program`. E.g.:

    <dependencies>
        <dependency>
            <groupId>pt.ist.esw</groupId>
            <artifactId>advice-runtime</artifactId>
            <version>1.7</version>
        </dependency>
    </dependencies>


# Contact

You can contact us via email at: esw_AT_inesc-id_DOT_ist_DOT_utl_DOT_pt.

