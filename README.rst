
KBase NetUtils
==============

Some basic Java utilities for finding free ports and available network interface IP addresses for your local machine.

Installation
------------

Using Gradle, specify the NetUtils as a dependency inside your ``build.gradle``:

.. code::

    repositories {
        mavenCentral()
        maven {
            url "https://maven.kbase.us"
        }
    }

    dependencies {
        api 'us.kbase.netutils:netutils:0.0.1'
    }

API
---

[View the javadocs API]

Examples
--------

``NetUtils.findNetworkAddresses``

.. code:: java

    String[] networkNames = {"docker0", "vboxnet0", "vboxnet1", "en0", "en1", "en2", "en3", "lo"};
    List<String> addresses = NetUtils.findNetworkAddresses(networkNames); 
    // addresses -> ['127.0.0.1', '127.17.0.1']


``NetUtils.findFreePort``

.. code:: java

    int port = NetUtils.findFreePort();
    ServerSocket sock = new ServerSocket(port);


Development
-----------

Using `gradle`_:

* `./gradlew build` to build and run tests
* `./gradlew test -i` to run verbose tests for debugging

.. External links
.. _gradle: https://docs.gradle.org
