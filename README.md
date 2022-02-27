- To deploy all containers in docker-compose, run these commands in terminal (route may vary):

cd C:\Users\user\Documents\eclipse-workspace\school
<br />
docker-compose up -d

<br />

- MySql backUp, just run the SQL file in any client, E.g: Workbench (route may vary):

C:\Users\user\Documents\eclipse-workspace\school\environment\backUp.sql

<br />

- Create API for students to register to courses

curl --location --request POST 'http://localhost:8080/company/api/students/2/courses' \
--header 'Accept: application/com.company.app-v1+json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "3"
}'

response: 201 Created
{
    "name": "math",
    "id": 3
}

<br />

- Create abilities for user to view all relationships between students and courses

curl --location --request GET 'http://localhost:8080/company/api/students' \
--header 'Accept: application/com.company.app-v1+json'

response: 200 OK
[
    {
        "document": 95573928,
        "name": "victor",
        "lastName": "martin",
        "courses": [
            {
                "name": "geology",
                "id": 1
            },
            {
                "name": "biology",
                "id": 2
            }
        ],
        "id": 1
    },
    {
        "document": 95243987,
        "name": "dubraska",
        "lastName": "primi",
        "courses": [
            {
                "name": "math",
                "id": 3
            }
        ],
        "id": 2
    },
    {
        "document": 95678432,
        "name": "pepe",
        "lastName": "olivares",
        "courses": [],
        "id": 3
    }
]

<br />

+ Filter all students with a specific course

curl --location --request GET 'http://localhost:8080/company/api/courses/1/students'

response: 200 OK
[
    {
        "document": 95573928,
        "name": "victor",
        "lastName": "martin",
        "courses": [
            {
                "name": "biology",
                "id": 2
            },
            {
                "name": "geology",
                "id": 1
            }
        ],
        "id": 1
    }
]

<br />

+ Filter all courses for a specific student

curl --location --request GET 'http://localhost:8080/company/api/students/2/courses'

response: 200 OK
[
    {
        "name": "math",
        "id": 3
    }
]

<br />

+ Filter all courses without any students

curl --location --request GET 'http://localhost:8080/company/api/courses/without-students'

response: 200 OK
[
    {
        "name": "programming",
        "id": 4
    }
]

<br />

+ Filter all students without any courses

curl --location --request GET 'http://localhost:8080/company/api/students/without-courses'

response: 200 OK
[
    {
        "document": 95678432,
        "name": "pepe",
        "lastName": "olivares",
        "courses": [],
        "id": 3
    }
]
