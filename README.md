
Please install maven if not installed.

Update the configuration in application-conf.json file

```bash
$ cd vertxmongo
$ mvn clean package
$ java -jar target/my-first-app-1.0-SNAPSHOT-fat.jar -conf src/main/conf/application-conf.json
```

## APIs

### Insert bugs
POST `/api/bugs` 

### Get Bug
GET `/api/bugs/:id`

### Get ALL Bugs
GET `/api/bugs`

### Insert A bug
POST `/api/bugs`

### Update A bug
PUT `/api/bugs`

### DELETE A bug
DELETE `/api/bugs/:id`