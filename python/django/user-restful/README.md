# This is a simple restful example for auth users, reference from web and change as http://www.django-rest-framework.org/#example

You may need to creat root user first by run:
python manage.py migrate
python manage.py createsuperuser
admin/password123

# run server
python manage.py runserver

# firefox
http://127.0.0.1:8000/users

# curl
$ curl -H 'Accept: application/json; indent=4' -u admin:password123 http://127.0.0.1:8000/users/
[
    {
        "url": "http://127.0.0.1:8000/users/1/",
        "username": "admin",
        "email": "admin@admin.com",
        "is_staff": true
    }
]

http http://localhost:8000/snip/snippets/
http http://localhost:8000/snip/snippets/1/

