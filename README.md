# BookPublishing

Requirements:

Docker:

```https://www.docker.com/```

PostgreSQL via Docker:
```
docker run  --name bookpublishing -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -d postgres
```

if PostgreSQL is already running:
```
sudo service postgresql stop

```



