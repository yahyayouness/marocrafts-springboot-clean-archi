# marocrafts-springboot-clean-archi # www.marocrafts.com www.yahyayouness.com

This github try to explain the purpose of clean architecture book from Uncle Bob. bellow the summary of the book and my opignon based on my three last project with clean architecture.

## book resume

### Introduction : le WHY de la Clean Archi
«The goal of software architecture is to minimize the human resources required to build and maintain the required system. » The measure of design quality is the measure of the effort required to meet the needs of the customer. If that effort is low, the design is good , if that effort grows up with each new release, the design is bad. « Just as the Hare was overconfident in its speed, so the developers are overconfident in their ability to remain productive.» « The only way to go fast, is to go well. » To the developers who like redo legacy, pay attention « your overconfidence will drive the redesign into the same mess as the original project.»

### Starting with the bricks : The 3 big programming paradigms according to Uncle Bob
Each paradigm removes something from the developer and simplifies the development:
* Structured programming: pulls out "goto" and imposes a discipline on the transfer of direct control (function call).
* Object-Oriented Programming: removes function pointers and imposes a discipline on indirect control transfer (polymorphism).
* Functional programming: removes the assignment and imposes a discipline on the variable assignment (immutability, composition functions).

### Design Principles
Single responsibility principle: A class should have only a single responsibility, that is, only changes to one part of the software's specification should be able to affect the specification of the class.
* Open–closed principle: Software entities ... should be open for extension, but closed for modification.
* Liskov substitution principle: Objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.
* Interface segregation principle: Many client-specific interfaces are better than one general-purpose interface.
* Dependency inversion principle One should depend upon abstractions, [not] concretions.

### Components Principles
« Don’t depend on things you don’t need. » I of SOLID (Interface Segregation Principle) SOLID "D" (Dependency Inversion Principle) can be used to reverse the dependency between 2 components. And we will use it excessively to change our architecture. We introduce an instability metric I for components, which goes from 0 to 1. A stable component (I = 0) is a component that does not depend on anything, so that has no other reason to change than itself. even. An unstable component (I = 1) is a component that depends on other components and therefore may have several reasons to change. The idea is that "the metric of a component should be larger than the metrics of the components that it depends on". What is stable = the business concepts. What is unstable = infrastructure (framework, libraries, environment ...)

### Architecture
« A good architect maximizes the number of decisions not made. A Clean Archi must be able to easily change the infrastructure by making sure nothing depends on it. So to be able to decide later, and change your mind. It's about DDD: Entities, Use Cases, "A shopping cart app with a good architecture will look like a shopping cart app. " It's about Craft: "Resist the temptation to commit the sin of knee-jerk elimination of duplication. Make sure the duplication is real. " The heart of Clean Archi: we separate what is important (the business = "domain") from what is not (the infrastructure) and we make sure that the domain does not depend on the infrastructure. A Clean Archi must be:independent of the frameworks,testable,independent of the UI,independent of the database,independent of any external element, to be resilient regardless of the infrastructure on which it is deployed Hand is the dirtiest part of the application, and it's normal. "Think of the main thing as a plugin to the application [...] that sets the initial conditions and configurations, and then sets them apart from the other.

### Details
« the database is not the data model. The database is piece of software. The database is a utility that provides access to the data. » « The GUI is a detail. The web is a GUI. So the web is a detail. » « The relationship between you and the framework author is extraordinarily asymmetric. You must make a huge commitment to the framework, but the framework author makes no commitment to you whatsoever. » donc « Don’t marry the framework! » The architecture is about intent , it’s not about tools or frameworks, tools shoud be a details.The data, domain, usecase are significant, but the persistence, presentation (database,web,framewoks) are details , don’t marry the framework.
