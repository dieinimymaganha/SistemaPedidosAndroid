from rest_framework.viewsets import ModelViewSet
from clientes.models import Cliente
from .serializers import AtracaoSerializer


class ClienteViewSet(ModelViewSet):
    queryset = Cliente.objects.all()
    serializer_class = AtracaoSerializer
