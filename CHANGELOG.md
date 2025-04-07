# 0.0.20

* Added handler for `robots.txt` file.

# 0.0.19

* Fixed bug in the build pipeline - the version wasn't propagated from `get-version` to deployment. 

# 0.0.18

* Updated GHA workflow to automatically deploy on push to main branch.

# 0.0.17

* Fixed padding between fields in the header block props. 
* Pages now have background color. 
* `l`-page has its own style now. 
* Added a banner block to the end of the page. 

# 0.0.16

* Split components and their props into separate files. 
* Fixed a bug when the user is not redirected after deleting a block.
* Fixed padding between fields in the registration form. 
* Avatar block now has background image. 

# 0.0.15

* Add basic backoffice. 

# 0.0.14

* When unknown `l` page is requested, the user is asked to register. 

# 0.0.13

* Added limit to the file types and sizes that can be uploaded.
* Fixed jdbc url in `application-production.yml`.
* Added PR template.

# 0.0.12

* Extracted version to `gradle.properties` file.
* Version is now displayed in the footer. 
