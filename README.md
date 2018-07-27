#App-Review-Classification
Classification of reviews from mobile applications.

## Use instructions:
1. Navigate to parent folder of ARC directory.
2. Start MySQL. 
3. In MySQL: `CREATE DATABASE app_reviews` and `USE app_reviews`
4. sql file containing the data collected is in ARC/input/612project.sql.
   `SOURCE  filepath`. Drag and drop the sql file for file path.
5. Set your MYSQL credentials (username and password) in Data.java
6. Compile & run the package. Main method is in `Parser.java`
