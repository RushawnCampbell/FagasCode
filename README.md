#Fagas Implementation Overview.

This repository contains four main packages with classes handling the functional requirements as set out by the Client - Fagas Enterprises Company Ltd. All packages together constitute a working solution. The package descriptions follow.

Logic: 
This package contains classes used for establishing database connection and facilitating the UIElements classes with updated data. It caters for customer record, service request and change request operations.

Popup Displays:
This package provides the means for which the appropriate users enter data for the creation and modification of customer records, service requests, change request entries, change request management and service list generation. Classes from this package communicate with the Logic package classes to handle database queries where appropriate.

Security:
This package handles the authentication and least privilege assurance of users of this system- a non-functional requirement of the client herein.

UIElements:
contains classes that instantiates and configure the display components for all views in the system. This group of classes provides the means for which its instantiated objects communicate with appropriate objects from the Logic pacakage. It receives data and represents said data on view for the users viewing and interaction where appropriate.

