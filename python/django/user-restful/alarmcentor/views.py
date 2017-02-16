from django.contrib.auth.models import User
from rest_framework import viewsets
from alarmcentor.serializers import UserSerializer

class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint alows users.
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer
