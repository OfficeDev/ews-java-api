## Contributing to Exchange Web Services Java API
*ews-java-api* is released under the [MIT License](license.txt) and contributors are welcome.

There are several ways to contribute to the project:

* Report bugs and features in the [issue tracker](https://github.com/officedev/ews-java-api/issues).
* Submit and review pull requests
* Help with documentation
* Help with testing

GitHub supports [markdown](http://github.github.com/github-flavored-markdown/), so when filing bugs make sure you check the formatting before clicking submit.

### Contributing code and content
Before submitting a feature or substantial code contribution please discuss it with the team and ensure it follows the product roadmap.  You might also read these two blogs posts on contributing code:

* [Open Source Contribution Etiquette](http://tirania.org/blog/archive/2010/Dec-31.html) by Miguel de Icaza
* [Don't "Push" Your Pull Requests](http://www.igvita.com/2011/12/19/dont-push-your-pull-requests/) by Ilya Grigorik.

### Coding Conventions
The project is using the _google-styleguide for Java_. Documentation of this style can be found here: [Google Java Style](https://google-styleguide.googlecode.com/svn-history/r130/trunk/javaguide.html)

#### Using IntelliJ
`Settings` -> `Code Style` -> `Scheme` -> _Choose_ `Project`
#### Suggested plugins
* [CheckStyle-IDEA-Plugin](https://plugins.jetbrains.com/plugin/1065) -- [HowTo](https://github.com/jshiell/checkstyle-idea/blob/master/README)

#### Using Eclipse
* Open *google-styleguide for Java* by clicking on: [google-styleguide](https://google-styleguide.googlecode.com/svn-history/r122/trunk/eclipse-java-google-style.xml)
* Download the file with: “Right click and save as”
* Import the new formatter:
    `Window` -> `Preferences` -> `Java` -> `Code Style` -> `Formatter` -> _Choose_ `Import` and `select` the _eclipse-java-google-style.xml_

### Pull Requests
If you don't know what a pull request is read the "[Using pull requests](https://help.github.com/articles/using-pull-requests)" article.

Some guidelines for pull requests:

* Use a descriptive title and description.
* Include a single logical change.
* Base on master branch - once accepted, can be ported to stable branches.
* Should cleanly merge with target branch.

### Sign the Contributor License Agreement (CLA)
Before your pull request can be accepted and merged to the main repository you need to sign the [Contributor License Agreement (CLA)](https://cla.azure.com).

### Commit Messages
1. Separate subject from body with a blank line
2. Limit the subject line to 50 characters
3. Capitalize the subject line
4. Do not end the subject line with a period
5. Use the imperative mood in the subject line (e.g. Fix #123: Make pigs fly).
6. Wrap the body at 72 characters
7. Use the body to explain what and why.  The how should be mostly covered by the diff.

References:

* [How to Write a Git Commit Message](http://chris.beams.io/posts/git-commit/)
* [A Note About Git Commit Messages](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html)
* [Guidelines for Commit Messages](https://wiki.gnome.org/Git/CommitMessages)
* [On commit messages](http://who-t.blogspot.de/2009/12/on-commit-messages.html)
