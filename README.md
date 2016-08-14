[![Build Status](https://travis-ci.org/CodingInfinity/Benchmark-Management-Server.svg?branch=master)](https://travis-ci.org/CodingInfinity/Benchmark-Management-Server)
[![Coverage Status](https://coveralls.io/repos/github/CodingInfinity/Benchmark-Management-Server/badge.svg?branch=master)](https://coveralls.io/github/CodingInfinity/Benchmark-Management-Server?branch=master)

# Version Control
This repository has adopted the [git flow branching model](http://nvie.com/posts/a-successful-git-branching-model/). The creators of git flow released a [short intro video](http://vimeo.com/16018419) to explain the model.

The `master` branch is production-ready and contains the latest tagged releases. Before a release is made, it is stagged in `release/x` branches before being pushed and tagged in the `master` branch. Small patches from `hotfix/x` branches are also pushed to `master`, and will always have a release version. The `develop` branch is pre-production, and is where we push `feature/x` branches for testing.

Most modern git clients support git flow. If you are however using the git CLI on *nix, please install the git flow plugin on your distribution.

# General Description
This module serves as the major backbone for the project as a whole, anything that can be interacted with on the front end is handled by a call in this module. This module can be used by pulling the repository onto your local machine. The backend can be run by running the command "mvn spring-boot: run" from terminal within the project directory. 

To do further development on this module, directions will be given assuming the necessary dependancies are installed (Maven, Lombok etc.) and the user is making use of IntelliJ IDEA development environment. Open the repository in IntelliJ and allow Maven to install dependancies. After doing any development, the app can be run within the IDE.

In terms of assistance in setting up the your IDE or the app in general, one can refer to Installation Manual. In depth documentation for the project as a whole including this module can be found in the service-documentation. 

For any additional information not covered here see pom.xml file or CONTRIBUTORS.md file.  
##For more information please read the wiki
https://github.com/CodingInfinity/Benchmark-Service-Documentation/wiki

# Sponsorships
## ZenHub 

<a href="https://zenhub.com"><img src="https://raw.githubusercontent.com/ZenHubIO/support/master/zenhub-badge.png"></a>

**ZenHub is how the world's best development teams work together.** ? 

As the only project management tool integrated natively in GitHub's UI, ZenHub helps you stay in your workflow without distractions. There are no new queues to check, entries to update, or priorities to manage outside GitHub. 

Because ZenHub's features are powered by native GitHub issues, it doesn't create more process - it gets out of your way so you can focus on shipping awesome products.

Download the ZenHub extension for Firefox or Chrome [here](http://www.zenhub.com). It's free for public, academic, and personal repositories. 