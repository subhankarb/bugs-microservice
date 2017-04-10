
Please install maven if not installed.

Update the configuration in application-conf.json file

```bash
$ cd vertxmongo
$ mvn clean package
$ java -jar target/my-first-app-1.0-SNAPSHOT-fat.jar -conf src/main/resources/application-conf.json
```

## APIs

### Insert bugs
POST `/save` 

### Get Bug
GET `/:bugId`

### DELETE A bug
DELETE `/:bugId`